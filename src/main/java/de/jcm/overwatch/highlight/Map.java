package de.jcm.overwatch.highlight;

import java.util.Arrays;
import java.util.HashMap;

public enum Map
{
	ADLERSBRUNN				( 1737, "Adlersbrunn",				MapType.SEASONAL_BRAWL		),
	BLIZZARD_WORLD			( 1886, "Blizzard World",			MapType.HYBRID				),
	BLIZZARD_WORLD_WINTER	( 2651, BLIZZARD_WORLD,				MapVariant.WINTER_WONDERLAND),
	DORADO					(  707, "Dorado",					MapType.ESCORT				),
	EICHENWALDE				( 1677,	"Eichenwalde",				MapType.HYBRID				),
	EICHENWALDE_HALLOWEEN	( 2036, EICHENWALDE,				MapVariant.HALLOWEEN_TERROR	),
	HANAMURA				(  357, "Hanamura",					MapType.ASSAULT				),
	HOLLYWOOD				(  687, "Hollywood",				MapType.HYBRID				),
	HORIZON_LUNAR_COLONY	( 1747, "Horizon Lunar Colony",		MapType.ASSAULT				),
	ILLIOS					( 1645, "Illios",					MapType.CONTROL				),
	JUNKERTOWN				( 1878, "Junkertown",				MapType.ESCORT				),
	KINGS_ROW				(  212, "King's Row",				MapType.HYBRID				),
	NEPAL					( 1207, "Nepal",					MapType.CONTROL				),
	OASIS					( 1694, "Oasis",					MapType.CONTROL				),
	PARIS					( 2193, "Paris",					MapType.ASSAULT				),
	ROUTE_66				( 1467, "Route 66",					MapType.ESCORT				),
	VOLSKAYA_INDUSTRIES		(  475, "Volskaya Industries",		MapType.ASSAULT				),
	LIJIANG_TOWER			( 1634, "Lijiang Tower",			MapType.CONTROL				),
	NUMBANI					( 1873, "Numbani",					MapType.HYBRID				),
	RIALTO					( 2161, "Rialto",					MapType.ESCORT				),
	TEMPLE_OF_ANUBIS		(   91, "Temple of Anubis",			MapType.ASSAULT				),
	WATCHPOINT_GIBRALTAR	(  388, "Watchpoint: Gibraltar",	MapType.ESCORT				)
	;

	public enum MapVariant
	{
		HALLOWEEN_TERROR	("Halloween Terror"	),
		WINTER_WONDERLAND	("Winter Wonderland"),
		LUNAR_NEW_YEAR		("Lunar New Year"	)
		;

		private String name;

		MapVariant(String name)
		{
			this.name = name;
		}

		public String getName()
		{
			return name;
		}

		@Override
		public String toString()
		{
			return getName();
		}
	}

	public enum MapType
	{
		ASSAULT			("Assault"			),
		ESCORT			("Escort"			),
		HYBRID			("Hybrid"			),
		CONTROL			("Control"			),

		ELIMINATION		("Elimination"		),
		DEATHMATCH		("Deathmatch"		),
		CAPTURE_THE_FLAG("Capture the Flag"	),

		SEASONAL_BRAWL	("Seasonal Brawl"	)
		;

		private String name;

		MapType(String name)
		{
			this.name = name;
		}

		public String getName()
		{
			return name;
		}

		@Override
		public String toString()
		{
			return getName();
		}
	}

	private static HashMap<Integer, Map> byId = new HashMap<>();
	static
	{
		Arrays.stream(Map.values()).forEach(map->byId.put(map.getId(), map));
	}

	public static Map getMapById(int id)
	{
		return byId.get(id);
	}

	private int id;

	private String name;
	private MapType type;

	private Map supermap;
	private MapVariant variant;
	private Map[] submaps = new Map[MapVariant.values().length];

	Map(int id, String name, MapType type)
	{
		this.id = id;

		this.name = name;
		this.type = type;
	}

	Map(int id, Map supermap, MapVariant variant)
	{
		this.id = id;

		this.supermap = supermap;
		this.variant = variant;
		this.supermap.submaps[this.variant.ordinal()] = this;

		this.name = this.supermap.name;
		this.type = this.supermap.type;
	}

	public int getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public MapType getType()
	{
		return type;
	}

	public boolean hasSupermap()
	{
		return getSupermap()!=null;
	}

	public Map getSupermap()
	{
		return supermap;
	}

	public boolean isVariant()
	{
		return getVariant()!=null;
	}

	public MapVariant getVariant()
	{
		return variant;
	}

	public Map[] getSubmaps()
	{
		return submaps;
	}

	public Map getSubmap(MapVariant variant)
	{
		return submaps[variant.ordinal()];
	}

	public boolean hasSubmap(MapVariant variant)
	{
		return getSubmap(variant)!=null;
	}

	@Override
	public String toString()
	{
		if(hasSupermap())
		{
			return getName()+" ("+getVariant()+")";
		}
		else
		{
			return getName();
		}
	}
}
