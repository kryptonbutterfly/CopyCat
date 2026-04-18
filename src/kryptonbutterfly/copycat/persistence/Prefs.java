package kryptonbutterfly.copycat.persistence;

import kryptonbutterfly.copycat.persistence.resolutions.Named16x9Resolution;
import kryptonbutterfly.copycat.persistence.resolutions.NamedResolution;
import kryptonbutterfly.util.swing.state.WindowState;
import kryptonbutterfly.xmlConfig4J.annotations.Value;

public class Prefs
{
	@Value("main Window")
	public WindowState mainWindow = new WindowState(100, 100, 322, 415);
	
	@Value("scan Window")
	public WindowState scanWindow = new WindowState(100, 100, 650, 578);
	
	@Value("Webcam recording resolution")
	public NamedResolution resolution = Named16x9Resolution.P720;
}