package kryptonbutterfly.copycat.persistence.resolutions;

import java.awt.Dimension;

public enum Named4x3Resolution implements NamedResolution
{
	VGA(640, 480, "VGA"),
	SVGA(800, 600, "SVGA"),
	XGA(1024, 768, "XGA"),
	XGAp(1152, 864, "XGA+"),
	R1280x960(1280, 960),
	R1400x1050(1400, 1050),
	UXGA(1600, 1200, "UXGA"),
	QXGA(2048, 1536, "QXGA"),
	R2560x1920(2560, 1920),
	R3200x2400(3200, 2400);
	
	private final String	resName;
	private final Dimension	dimensions;
	
	Named4x3Resolution(int width, int height)
	{
		this(width, height, "%d×%d".formatted(width, height));
	}
	
	Named4x3Resolution(int width, int height, String name)
	{
		this.resName	= name;
		this.dimensions	= new Dimension(width, height);
	}
	
	@Override
	public String resolutionName()
	{
		return resName;
	}
	
	@Override
	public Dimension size()
	{
		return dimensions;
	}
}
