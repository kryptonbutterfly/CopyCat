module CopyCat
{
	// TODO evaluate if anything can be removed.
	
	exports kryptonbutterfly.copycat.persistence
			to kryptonbutterfly.ReflectionUtils, kryptonbutterfly.xmlConfig4J, kryptonbutterfly.SwingUtils;
	// exports kryptonbutterfly.copycat.data to kryptonbutterfly.ReflectionUtils,
	// kryptonbutterfly.SwingUtils;
	// exports kryptonbutterfly.copycat.utils.ui to kryptonbutterfly.SwingUtils;
	
	requires transitive java.desktop;
	requires lombok;
	requires transitive kryptonbutterfly.xmlConfig4J;
	requires kryptonbutterfly.System;
	requires kryptonbutterfly.ReflectionUtils;
	requires kryptonbutterfly.mathUtils;
	requires kryptonbutterfly.Monads;
	requires transitive kryptonbutterfly.SwingUtils;
	requires kryptonbutterfly.Collections;
	requires webcam.capture;
	requires com.google.zxing;
}