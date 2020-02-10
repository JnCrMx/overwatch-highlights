package de.jcm.overwatch.highlight;

import java.util.Arrays;
import java.util.HashMap;

public enum Hero
{
	ANA				( 315, "Ana"			, HeroClass.SUPPORT	),
	ASHE			( 512, "Ashe"			, HeroClass.DAMAGE	),
	BAPTISTE		( 545, "Baptiste"		, HeroClass.SUPPORT	),
	BASTION			(  21, "Bastion"		, HeroClass.DAMAGE	),
	BRIGITTE		( 405, "Brigitte"		, HeroClass.SUPPORT	),
	D_VA			( 122, "D.Va"			, HeroClass.TANK	),
	DOOMFIST		( 303, "Doomfist"		, HeroClass.DAMAGE	),
	GENJI			(  41, "Genji"			, HeroClass.DAMAGE	),
	HANZO			(   5, "Hanzo"			, HeroClass.DAMAGE	),
	JUNKRAT			( 101, "Junkrat"		, HeroClass.DAMAGE	),
	LUCIO			( 121, "Lúcio"			, HeroClass.SUPPORT	),
	MCCREE			(  -1, "McCree"			, HeroClass.DAMAGE	),
	MEI				(  -1, "Mei"			, HeroClass.DAMAGE	),
	MERCY			(   4, "Mercy"			, HeroClass.SUPPORT	),
	MOIRA			( 418, "Moira"			, HeroClass.SUPPORT	),
	ORISA			( 318, "Orisa"			, HeroClass.TANK	),
	PHARAH			(   8, "Pharah"			, HeroClass.DAMAGE	),
	REAPER			(   2, "Reaper"			, HeroClass.DAMAGE	),
	REINHARDT		(   7, "Reinhardt"		, HeroClass.TANK	),
	ROADHOG			(  -1, "Roadhog"		, HeroClass.TANK	),
	SOLDIER_76		(  -1, "Soldier: 76"	, HeroClass.DAMAGE	),
	SOMBRA			(  -1, "Sombra"			, HeroClass.DAMAGE	),
	SYMMETRA		(  22, "Symmetra"		, HeroClass.DAMAGE	),
	TORBJOERN		(   6, "Torbjörn"		, HeroClass.DAMAGE	),
	TRACER			(   3, "Tracer"			, HeroClass.DAMAGE	),
	WIDOWMAKER		(  -1, "Widowmaker"		, HeroClass.DAMAGE	),
	WINSTON			(  -1, "Winston"		, HeroClass.TANK	),
	WRECKING_BALL	( 458, "Wrecking Ball"	, HeroClass.TANK	),
	ZARYA			(  -1, "Zarya"			, HeroClass.TANK	),
	ZENYATTA		(  32, "Zenyatta"		, HeroClass.SUPPORT	)
	;
	
	public enum HeroClass
	{
		TANK,
		DAMAGE,
		SUPPORT
	}

	private static HashMap<Integer, Hero> byId = new HashMap<>();
	static
	{
		Arrays.stream(Hero.values()).forEach(hero->byId.put(hero.getId(), hero));
	}

	public static Hero getHeroById(int id)
	{
		return byId.get(id);
	}

	private int id;
	private String name;
	private HeroClass heroClass;

	Hero(int id, String name, HeroClass heroClass)
	{
		this.id = id;
		this.name = name;
		this.heroClass = heroClass;
	}

	/**
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
	
	public HeroClass getHeroClass()
	{
		return heroClass;
	}

	@Override
	public String toString()
	{
		return getName();
	}
}
