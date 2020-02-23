package de.jcm.overwatch.highlight;

import org.ini4j.IniPreferences;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.prefs.BackingStoreException;
import java.util.stream.Stream;

public class HighlightTest
{
    @ParameterizedTest
    @MethodSource(value = "findHighlights")
    void test(File highlightFile) throws IOException
    {
        OverwatchHighlight highlight = new OverwatchHighlight(highlightFile);
        System.out.println("highlight.getType() = " + highlight.getType());
        System.out.println("highlight.getTitle() = " + highlight.getTitle());
        System.out.println("highlight.getHero() = " + highlight.getHero());
        System.out.println("highlight.getMap() = " + highlight.getMap());
    }

    static Stream<File> findHighlights() throws IOException, BackingStoreException
    {
        File directory = new File(System.getProperty("user.home"), "Documents\\Overwatch\\videos\\overwatch\\");

        File overwatchConfig =
                new File(System.getProperty("user.home"), "Documents\\Overwatch\\Settings\\Settings_v0.ini");
        if(overwatchConfig.exists())
        {
            IniPreferences iniPreferences = new IniPreferences(new FileInputStream(overwatchConfig));
            if(iniPreferences.nodeExists("MovieExport.1"))
            {
                directory = new File(iniPreferences.node("MovieExport.1").get("VideoPath",
                        System.getProperty("user.home")+"\\Documents\\Overwatch\\videos\\overwatch\\"));
            }
        }

        return Stream.of(Objects.requireNonNull(directory.listFiles((f, s) -> s.endsWith(".mp4"))));
    }
}
