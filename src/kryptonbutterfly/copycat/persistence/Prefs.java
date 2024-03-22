package kryptonbutterfly.copycat.persistence;

import java.io.File;

import kryptonbutterfly.util.swing.state.PersistableValue;
import kryptonbutterfly.util.swing.state.WindowState;
import kryptonbutterfly.xmlConfig4J.FileConfig;
import kryptonbutterfly.xmlConfig4J.annotations.Value;

public class Prefs extends FileConfig
{
	public Prefs(File configFile)
	{
		super(configFile, Value.class, PersistableValue.class);
	}
	
	@Value("main Window")
	public WindowState mainWindow = new WindowState(100, 100, 322, 415);
	
	@Value("scan Window")
	public WindowState scanWindow = new WindowState(100, 100, 650, 578);
}