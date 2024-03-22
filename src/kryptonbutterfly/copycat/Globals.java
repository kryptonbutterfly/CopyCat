package kryptonbutterfly.copycat;

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
	
	public static Prefs prefs = new Prefs(PREFS_FILE);
	
	public static final int QR_CODE_SIZE = 250;
}
