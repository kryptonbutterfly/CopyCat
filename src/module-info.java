module CopyCat
{
	exports kryptonbutterfly.copycat.persistence
			to kryptonbutterfly.ReflectionUtils, kryptonbutterfly.xmlConfig4J, kryptonbutterfly.SwingUtils;
	
	requires transitive java.desktop;
	requires lombok;
	requires transitive kryptonbutterfly.xmlConfig4J;
	requires kryptonbutterfly.System;
	requires kryptonbutterfly.ReflectionUtils;
	requires kryptonbutterfly.mathUtils;
	requires kryptonbutterfly.Monads;
	requires transitive kryptonbutterfly.SwingUtils;
	requires webcam.capture;
	requires com.google.zxing;
}