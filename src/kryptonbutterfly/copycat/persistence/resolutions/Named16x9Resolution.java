package kryptonbutterfly.copycat.persistence.resolutions;

import java.awt.Dimension;

public enum Named16x9Resolution implements NamedResolution
{
	P144(256, 144, "144p"),
	P240(426, 240, "240p"),
	P360(640, 360, "360p"),
	P480(854, 480, "480p"),
	P720(1280, 720, "720p"),
	R1366x768(1366, 768),
	R1600x900(1600, 900),
	P1080(1920, 1080, "1080p"),
	QHD(2560, 1440, "2K"),
	UHD(3840, 2160, "4K"),
	K5(5120, 2880, "5K"),
	K8(7680, 4320, "8K");
	
	private String		resName;
	private Dimension	dimensions;
	
	Named16x9Resolution(int width, int height)
	{
		this(width, height, "%d×%d".formatted(width, height));
	}
	
	Named16x9Resolution(int width, int height, String name)
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
