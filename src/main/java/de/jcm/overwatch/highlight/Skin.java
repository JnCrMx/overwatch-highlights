package de.jcm.overwatch.highlight;

import java.util.Arrays;
import java.util.HashMap;

public enum Skin
{
	ANA_BASTET(10820, Hero.ANA, "Bastet", Category.SPECIAL, Rarity.EPIC),

	ASHE_WINTER(10768, Hero.ASHE, "Winter", Category.WINTER_WONDERLAND, Rarity.EPIC),

	BAPTSITE_CLASSIC(8584, Hero.BAPTISTE, "Classic", Category.OVERWATCH, Rarity.COMMON),

	BASTION_STEALTH(7831, Hero.BASTION, "Stealth", Category.ANNIVERSARY, Rarity.LEGENDARY),

	BRIGITTE_IRONCLAD(7853, Hero.BRIGITTE, "Ironclad", Category.OVERWATCH, Rarity.EPIC),

	D_VA_TANGERINE(5654, Hero.D_VA, "Tangerine", Category.OVERWATCH, Rarity.RARE),

	DOOMFIST_PLAINS(5650, Hero.DOOMFIST, "Plains", Category.OVERWATCH, Rarity.RARE),
	DOOMFIST_TALON(7189, Hero.DOOMFIST, "Talon", Category.ARCHIVES, Rarity.LEGENDARY),

	ECHO_CLASSIC(8416, Hero.ECHO, "Classic", Category.OVERWATCH, Rarity.COMMON),

	GENJI_NOMAD(6025, Hero.GENJI, "Nomad", Category.OVERWATCH, Rarity.LEGENDARY),
	GENJI_BLACKWATCH(7037, Hero.GENJI, "Blackwatch", Category.ARCHIVES, Rarity.LEGENDARY),

	HANZO_DEMON(6567, Hero.HANZO, "Demon", Category.HALLOWEEN_TERROR, Rarity.EPIC),

	JUNKRAT_BILGERAT(7283, Hero.JUNKRAT, "Bilgerat", Category.ANNIVERSARY, Rarity.LEGENDARY),

	LUCIO_BITRATE(8428, Hero.LUCIO, "Bitrate", Category.ANNIVERSARY, Rarity.EPIC),

	MCCREE_UNDEAD(8653, Hero.MCCREE, "Undead", Category.HALLOWEEN_TERROR, Rarity.EPIC),

	MEI_SNOW_PLUM(6347, Hero.MEI, "Snow Plum", Category.OVERWATCH, Rarity.EPIC),

	MERCY_CLASSIC(5595, Hero.MERCY, "Classic", Category.OVERWATCH, Rarity.COMMON),
	MERCY_DEVIL(5591, Hero.MERCY, "Devil", Category.OVERWATCH, Rarity.LEGENDARY),
	MERCY_IMP(5592, Hero.MERCY, "Imp", Category.OVERWATCH, Rarity.LEGENDARY),
	MERCY_WINGED_VICTORY(7110, Hero.MERCY, "Winged Victory", Category.SUMMER_GAMES, Rarity.LEGENDARY),
	MERCY_WITCH(6506, Hero.MERCY, "Witch", Category.HALLOWEEN_TERROR, Rarity.LEGENDARY),
	MERCY_SUGAR_PLUM_FAIRY(8049, Hero.MERCY, "Sugar Plum Fairy", Category.WINTER_WONDERLAND, Rarity.LEGENDARY),
	MERCY_ZHUQUE(7185, Hero.MERCY, "Zhuque", Category.LUNAR_NEW_YEAR, Rarity.LEGENDARY),

	MOIRA_HOLLY(16886, Hero.MOIRA, "Holly", Category.WINTER_WONDERLAND, Rarity.EPIC),
	MOIRA_OASIS(7234, Hero.MOIRA, "Oasis", Category.OVERWATCH, Rarity.LEGENDARY),
	MOIRE_BLACKWATCH(7285, Hero.MOIRA, "Blackwatch", Category.ARCHIVES, Rarity.LEGENDARY),

	ORISA_OR15(7061, Hero.ORISA, "OR15", Category.OVERWATCH, Rarity.EPIC),
	ORISA_FOREST_SPIRIT(7279, Hero.ORISA, "Forest Spirit", Category.ANNIVERSARY, Rarity.LEGENDARY),

	PHARAH_RAINDANCER(6443, Hero.PHARAH, "Raindancer", Category.OVERWATCH, Rarity.LEGENDARY),

	REAPER_MIDNIGHT(5786, Hero.REAPER, "Midnight", Category.OVERWATCH, Rarity.RARE),
	REAPER_SOLIDER_24(7284, Hero.REAPER, "Solider: 24", Category.ARCHIVES, Rarity.LEGENDARY),

	REINHARDT_BLACKHARDT(5695, Hero.REINHARDT, "Blackhardt", Category.OVERWATCH, Rarity.LEGENDARY),

	ROADHOG_BUTCHER(7195, Hero.ROADHOG, "Butcher", Category.OVERWATCH, Rarity.LEGENDARY),

	SIGMA_RIME(11637, Hero.SIGMA, "Rime", Category.WINTER_WONDERLAND, Rarity.LEGENDARY),

	SOLDIER_76_BONE(6373, Hero.SOLDIER_76, "Bone", Category.OVERWATCH, Rarity.EPIC),

	SOMBRA_DEMON_HUNTER(8507, Hero.SOMBRA, "Demon Hunter", Category.SPECIAL, Rarity.LEGENDARY),

	SYMMETRA_CLASSIC(5863, Hero.SYMMETRA, "Classic", Category.OVERWATCH, Rarity.COMMON),

	TORBJOERN_CLASSIC(5687, Hero.TORBJOERN, "Classic", Category.OVERWATCH, Rarity.COMMON),
	TORBJOERN_MAGNI(7192, Hero.TORBJOERN, "Magni", Category.OVERWATCH, Rarity.LEGENDARY),

	TRACER_CLASSIC(5903, Hero.TRACER, "Classic", Category.OVERWATCH, Rarity.COMMON),

	WIDOWMAKER_BLACK_LILY(7199, Hero.WIDOWMAKER, "Black Lily", Category.LUNAR_NEW_YEAR, Rarity.LEGENDARY),

	WINSTON_HORIZON(6377, Hero.WINSTON, "Horizon", Category.OVERWATCH, Rarity.EPIC),

	WRECKING_BALL_WOODEN_BALL(8515, Hero.WRECKING_BALL, "Wooden Ball", Category.OVERWATCH, Rarity.EPIC),

	ZARYA_FROSTED(6683, Hero.ZARYA, "Frosted", Category.WINTER_WONDERLAND, Rarity.EPIC),

	ZENYATTA_CLASSIC(5635, Hero.ZENYATTA, "Classic", Category.OVERWATCH, Rarity.COMMON),
	ZENYATTA_EARTH(5638, Hero.ZENYATTA, "Earth", Category.OVERWATCH, Rarity.RARE),
	ZENYATTA_CARBON_FIBER(7232, Hero.ZENYATTA, "Carbon Fiber", Category.OVERWATCH, Rarity.EPIC),
	;

	public enum Category
	{
		OVERWATCH,
		ACHIEVEMENTS,
		SUMMER_GAMES,
		HALLOWEEN_TERROR,
		WINTER_WONDERLAND,
		LUNAR_NEW_YEAR,
		ARCHIVES,
		ANNIVERSARY,
		OVERWATCH_LEAGUE,
		SPECIAL,
		LEGACY
	}

	public enum Rarity
	{
		COMMON,
		RARE,
		EPIC,
		LEGENDARY
	}

	private static final HashMap<Integer, Skin> byId = new HashMap<>();
	static
	{
		Arrays.stream(Skin.values()).forEach(skin->byId.put(skin.getId(), skin));
	}

	public static Skin getSkinById(int id)
	{
		return byId.get(id);
	}

	private int id;
	private Hero hero;
	private String name;
	private Category category;
	private Rarity rarity;

	Skin(int id, Hero hero, String name, Category category, Rarity rarity)
	{
		this.id = id;
		this.hero = hero;
		this.name = name;
		this.category = category;
		this.rarity = rarity;
	}

	/**
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @return the hero this skin belongs to
	 */
	public Hero getHero()
	{
		return hero;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return the category of this skin
	 */
	public Category getCategory()
	{
		return category;
	}

	/**
	 * @return the rarity of this skin
	 */
	public Rarity getRarity()
	{
		return rarity;
	}

	@Override
	public String toString()
	{
		return hero.getName()+" "+getName();
	}
}
