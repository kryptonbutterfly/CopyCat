package kryptonbutterfly.copycat;

import java.io.File;

import kryptonbutterfly.copycat.persistence.resolutions.NamedResolutionAdapter;
import kryptonbutterfly.os.BaseDirectory;
import kryptonbutterfly.os.Platforms;
import kryptonbutterfly.util.swing.state.PersistableValue;
import kryptonbutterfly.xmlConfig4J.BindingBuilder;
import kryptonbutterfly.xmlConfig4J.XmlDataBinding;
import kryptonbutterfly.xmlConfig4J.XmlTags;

public final class Globals
{
	private Globals()
	{}
	
	public static final XmlDataBinding c4j = new BindingBuilder()
		.addIncludeAnnotation(PersistableValue.class)
		.setTag(XmlTags.DATA, "config")
		.addTypeAdapter(new NamedResolutionAdapter())
		.build();
	
	private static final String	CREATOR				= "kryptonbutterfly";
	private static final String	PROGRAM_NAME		= "copycat";
	public static final String	PROGRAM_DOCK_NAME	= "CopyCat";
	
	private static final BaseDirectory BASE_DIR = Platforms.getOS().baseDir().init(CREATOR, PROGRAM_NAME);
	
	public static final File PREFS_FILE = new File(BASE_DIR.stateHome(), "preferences.xml");
	
	public static final int QR_CODE_SIZE = 250;
	
}
