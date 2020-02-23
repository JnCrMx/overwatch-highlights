package de.jcm.overwatch.highlight;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.Base64.Decoder;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.jcodec.containers.mp4.MP4Util;
import org.jcodec.containers.mp4.boxes.DataBox;
import org.jcodec.containers.mp4.boxes.IListBox;
import org.jcodec.containers.mp4.boxes.MetaBox;
import org.jcodec.containers.mp4.boxes.MovieBox;
import org.jcodec.containers.mp4.boxes.NodeBox;
import org.jcodec.containers.mp4.boxes.UdtaBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OverwatchHighlight
{
	private static final Decoder decoder = Base64.getDecoder();
	private static final Logger logger = LoggerFactory.getLogger(OverwatchHighlight.class);

	private final File file;

	private String title;
	private Hero hero;
	private Map map;
	private HighlightType type;
	
	private Image thumbnail;

	public OverwatchHighlight(File file) throws IOException
	{
		this.file = file;
		readInfo();
	}

	private void readInfo() throws IOException
	{
		FileChannel channel = FileChannel.open(Path.of(file.toURI()), StandardOpenOption.READ);

		title = extractTitle();
		
		long fileLength = file.length();
		int length = 0x13B;
		long start = fileLength - length;

		ByteBuffer buffer = ByteBuffer.allocate(length);
		channel.read(buffer, start);

		byte[] base64Bytes = new byte[0x80];
		buffer.position(0x5F);
		buffer.get(base64Bytes);
		String base64 = new String(base64Bytes, StandardCharsets.UTF_16LE);

		byte[] infoBytes = decoder.decode(base64);
		ByteBuffer info = ByteBuffer.wrap(infoBytes);
		info.order(ByteOrder.LITTLE_ENDIAN);

		int heroId 	= Short.toUnsignedInt	(info.getShort	(0x08));
		int mapId 	= Short.toUnsignedInt	(info.getShort	(0x00));
		int typeNum	= Byte.toUnsignedInt	(info.get		(0x28));

		hero = Hero.getHeroById(heroId);
		if(hero==null)
		{
			logger.warn("Unknown hero with id "+heroId+" in file "+file.getAbsolutePath());
			logger.info("Please report this and the actual hero to the developer in order to improve the collected data.");
		}
		
		map = Map.getMapById(mapId);
		if(map==null)
		{
			logger.warn("Unknown map with id "+mapId+" in file "+file.getAbsolutePath());
			logger.info("Please report this and the actual map to the developer in order to improve the collected data.");
		}
		
		type = HighlightType.getTypeById(typeNum);
		if(type==null)
		{
			logger.warn("Unknown type with id "+typeNum+" in file "+file.getAbsolutePath());
			logger.info("Please report this and the actual type to the developer in order to improve the collected data.");
		}
		
		channel.close();
	}

	public File getFile()
	{
		return file;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public Image getThumbnail()
	{
		return thumbnail;
	}
	
	public boolean loadThumbnail(int width, int height)
	{
		if(thumbnail==null)
		{
			try
			{
				thumbnail = extractThumbnail(width, height);
				logger.debug("Loaded thumbnail for hightlight \""+getTitle()+"\" from file \""+getFile().getAbsolutePath()+"\"");
				return true;
			}
			catch(Exception e)
			{
				logger.error("Cannot load thumbnail for highlight \""+getTitle()+"\" from file \""+getFile().getAbsolutePath()+"\"", e);
			}
		}
		return false;
	}
	
	private String extractTitle() throws IOException
	{
		MovieBox movie = MP4Util.parseMovie(file);
		UdtaBox udta = NodeBox.findFirst(movie, UdtaBox.class, "udta");
		MetaBox meta = udta.meta();
		IListBox ilst = NodeBox.findFirst(meta, IListBox.class, "ilst");
		DataBox data = (DataBox) ilst.getValues().values().iterator().next().get(0);
		return new String(data.getData(), StandardCharsets.UTF_8);
	}
	
	private Image extractThumbnail(int width, int height)
	{
		try
		{
	        FFmpegFrameGrabber frameGrabber = new FFmpegFrameGrabber(file);

	        frameGrabber.setImageWidth(width);
	        frameGrabber.setImageHeight(height);
	        
	        frameGrabber.start();
	        
	        frameGrabber.setFrameNumber((int) (4*frameGrabber.getFrameRate()));
	        
	        Frame grabKeyFrame = frameGrabber.grabImage();
	        Java2DFrameConverter java2DFrameConverter = new Java2DFrameConverter();
	        BufferedImage bufferedImage = java2DFrameConverter.convert(grabKeyFrame);
	        frameGrabber.stop();
	        frameGrabber.close();

			return bufferedImage;
	    }
		catch (Exception e)
		{
	        throw new RuntimeException(e);
	    }
	}

	public Hero getHero()
	{
		return hero;
	}

	public Map getMap()
	{
		return map;
	}

	public HighlightType getType()
	{
		return type;
	}
}
