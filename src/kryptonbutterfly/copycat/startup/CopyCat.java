package kryptonbutterfly.copycat.startup;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InaccessibleObjectException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import kryptonbutterfly.copycat.Globals;
import kryptonbutterfly.copycat.assets.Assets;
import kryptonbutterfly.copycat.persistence.Prefs;
import kryptonbutterfly.copycat.ui.main.MainGui;
import kryptonbutterfly.monads.opt.Opt;
import kryptonbutterfly.reflectionUtils.Accessor;
import kryptonbutterfly.util.swing.ObservableGui;
import kryptonbutterfly.xmlConfig4J.persistence.PersistableResource;
import kryptonbutterfly.xmlConfig4J.persistence.PersistableResourceBuilder;
import lombok.SneakyThrows;

public class CopyCat
{
	private static PersistableResource<Prefs> prefs;
	
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		setLookAndFeel();
		ObservableGui.setDefaultAppImage(Opt.of(Assets.APP_IMAGE));
		
		prefs = new PersistableResourceBuilder<>(Globals.c4j, false, Prefs.class)
			.fromFile(Globals.PREFS_FILE, Prefs::new);
		
		EventQueue.invokeLater(() -> new MainGui(gce -> persist()));
	}
	
	@SneakyThrows
	private static void persist()
	{
		prefs.persist();
	}
	
	public static Prefs prefs()
	{
		return prefs.data();
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
