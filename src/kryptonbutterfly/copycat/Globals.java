package kryptonbutterfly.copycat;

import java.awt.Dimension;
import java.io.File;

import kryptonbutterfly.copycat.persistence.Prefs;
import kryptonbutterfly.os.BaseDirectory;
import kryptonbutterfly.os.Platforms;

public final class Globals
{
	private Globals()
	{}
	
	private static final String	CREATOR				= "kryptonbutterfly";
	private static final String	PROGRAM_NAME		= "copycat";
	public static final String	PROGRAM_DOCK_NAME	= "CopyCat";
	
	private static final BaseDirectory BASE_DIR = Platforms.getOS().baseDir().init(CREATOR, PROGRAM_NAME);
	
	private static final File PREFS_FILE = new File(BASE_DIR.stateHome(), "preferences.xml");
	
	public static final Prefs prefs = new Prefs(PREFS_FILE);
	
	public static final int QR_CODE_SIZE = 250;
	
	public static final Dimension	p144	= new Dimension(256, 144);
	public static final Dimension	p240	= new Dimension(426, 240);
	public static final Dimension	p360	= new Dimension(640, 360);
	public static final Dimension	p480	= new Dimension(854, 480);
	public static final Dimension	p720	= new Dimension(1280, 720);
	public static final Dimension	p1080	= new Dimension(1920, 1080);
	
	public static final Dimension[] COMMON_16x9 = { p144, p240, p360, p480, p720, p1080 };
}
