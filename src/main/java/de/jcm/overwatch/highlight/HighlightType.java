package de.jcm.overwatch.highlight;

import java.util.Arrays;
import java.util.HashMap;

public enum HighlightType
{
	TOP5			(0x00, false, false),
	TOP5_POTG		(0x02, false, true ),
	CAPTURED		(0x08, true , false),
	CAPTURED_POTG	(0x0A, true , true )
	;

	private static final HashMap<Integer, HighlightType> byId = new HashMap<>();
	static
	{
		Arrays.stream(HighlightType.values()).forEach(hero->byId.put(hero.getId(), hero));
	}

	public static HighlightType getTypeById(int id)
	{
		return byId.get(id);
	}

	private int id;
	private boolean captured;
	private boolean potg;

	HighlightType(int id, boolean captured, boolean potg)
	{
		this.id = id;
		this.captured = captured;
		this.potg = potg;
	}

	public int getId()
	{
		return id;
	}

	public boolean isCaptured()
	{
		return captured;
	}

	public boolean isPotG()
	{
		return potg;
	}
}
