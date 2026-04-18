package kryptonbutterfly.copycat.persistence.resolutions;

import java.awt.Dimension;
import java.util.ArrayList;

public sealed interface NamedResolution permits Named16x9Resolution, Named4x3Resolution
{
	String resolutionName();
	
	default String resolution()
	{
		return "%d×%d".formatted(size().width, size().height);
	}
	
	Dimension size();
	
	default String description()
	{
		return "%s: %s".formatted(resolutionName(), resolution());
	}
	
	public static Dimension[] allSizes()
	{
		final var result = new ArrayList<Dimension>();
		for (var res : Named16x9Resolution.values())
			result.add(res.size());
		for (var res : Named4x3Resolution.values())
			result.add(res.size());
		return result.toArray(Dimension[]::new);
	}
	
	public static NamedResolution getByDescription(String description)
	{
		final var resString = description.replace('x', '×');
		for (var res : Named16x9Resolution.values())
			if (res.resolutionName().equals(description) || res.resolution().equals(resString))
				return res;
			
		for (var res : Named4x3Resolution.values())
			if (res.resolutionName().equals(description) || res.resolution().equals(resString))
				return res;
			
		final var sb = new StringBuilder("Expected one of the following resolutions:\n")
			.append("16×9:\n");
		for (var res : Named16x9Resolution.values())
			sb.append("\t").append(res.description()).append("\n");
		
		sb.append("4×3:\n");
		for (var res : Named4x3Resolution.values())
			sb.append("\t").append(res.description()).append("\n");
		
		throw new IllegalArgumentException(sb.toString());
	}
}
