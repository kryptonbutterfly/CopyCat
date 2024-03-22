package kryptonbutterfly.copycat.startup;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InaccessibleObjectException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import kryptonbutterfly.copycat.Globals;
import kryptonbutterfly.copycat.assets.Assets;
import kryptonbutterfly.copycat.ui.main.MainGui;
import kryptonbutterfly.monads.opt.Opt;
import kryptonbutterfly.reflectionUtils.Accessor;
import kryptonbutterfly.util.swing.ObservableGui;
import kryptonbutterfly.xmlConfig4J.FileConfig;

public class CopyCat
{
	public static void main(String[] args)
	{
		loadConfig(Globals.prefs);
		setLookAndFeel();
		ObservableGui.setDefaultAppImage(Opt.of(Assets.APP_IMAGE));
		EventQueue.invokeLater(() -> new MainGui(gce -> persistData()));
	}
	
	private static final void persistData()
	{
		Globals.prefs.save();
	}
	
	private static final void loadConfig(FileConfig config)
	{
		if (!config.exists())
			config.save();
		else
		{
			try
			{
				config.load();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	private static void setLookAndFeel()
	{
		try
		{
			final var toolkit = Toolkit.getDefaultToolkit();
			new Accessor<>(toolkit, toolkit.getClass().getDeclaredField("awtAppClassName"))
				.applyObj(Field::set, Globals.PROGRAM_DOCK_NAME);
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (InaccessibleObjectException e)
		{
			e.printStackTrace();
		}
		catch (
			IllegalArgumentException
			| IllegalAccessException
			| NoSuchFieldException
			| SecurityException
			| ClassNotFoundException
			| InstantiationException
			| UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}
	}
}
