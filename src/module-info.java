module CopyCat
{
	opens kryptonbutterfly.copycat.persistence to kryptonbutterfly.xmlConfig4J;
	opens kryptonbutterfly.copycat.persistence.resolutions to kryptonbutterfly.xmlConfig4J;
	
	requires transitive java.desktop;
	requires lombok;
	requires transitive kryptonbutterfly.xmlConfig4J;
	requires kryptonbutterfly.System;
	requires kryptonbutterfly.mathUtils;
	requires kryptonbutterfly.ReflectionUtils;
	requires kryptonbutterfly.Monads;
	requires transitive kryptonbutterfly.SwingUtils;
	requires webcam.capture;
	requires com.google.zxing;
}