package kryptonbutterfly.copycat.ui.scan;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Map;

import javax.swing.ImageIcon;

import com.github.sarxos.webcam.Webcam;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import kryptonbutterfly.copycat.Globals;
import kryptonbutterfly.copycat.utils.ImageLuminanceSource;
import kryptonbutterfly.copycat.utils.KeyTypedAdapter;
import kryptonbutterfly.copycat.utils.Utils;
import kryptonbutterfly.monads.opt.Opt;
import kryptonbutterfly.util.swing.Logic;
import kryptonbutterfly.util.swing.events.GuiCloseEvent;
import kryptonbutterfly.util.swing.events.GuiCloseEvent.Result;

final class BL extends Logic<ScanGui, Void>
{
	private static final Map<DecodeHintType, ?> HINTS = Map.of(DecodeHintType.TRY_HARDER, true);
	
	private boolean			keepScanning	= true;
	private final Webcam	webcam			= Webcam.getDefault();
	
	final KeyListener escapeListener = new KeyTypedAdapter(c -> abort(null), KeyEvent.VK_ESCAPE);
	
	BL(ScanGui gui)
	{
		super(gui);
	}
	
	void scanCamera()
	{
		gui.if_(gui -> {
			EventQueue.invokeLater(() -> scan(gui));
		});
	}
	
	void abort(ActionEvent ae)
	{
		gui.if_(ScanGui::dispose);
	}
	
	@Override
	protected void disposeAction()
	{
		this.keepScanning = false;
		gui.if_(Globals.prefs.scanWindow::persistBounds);
	}
	
	private void scan(ScanGui gui)
	{
		try
		{
			if (!webcam.isOpen())
				webcam.open();
			
			final var image = webcam.getImage();
			gui.cameraDisplay.setIcon(new ImageIcon(Utils.mirror(image)));
			final var result = decode(image);
			
			keepScanning = false;
			webcam.close();
			
			gui.dispose(new GuiCloseEvent<>(Result.SUCCESS, Opt.empty(), result));
		}
		catch (NotFoundException | ChecksumException | FormatException e)
		{
			if (keepScanning)
				EventQueue.invokeLater(() -> scan(gui));
			else
				webcam.close();
		}
	}
	
	private String decode(BufferedImage image)
		throws NotFoundException,
		ChecksumException,
		FormatException
	{
		final var	bitmap	= new BinaryBitmap(new HybridBinarizer(new ImageLuminanceSource(image)));
		final var	res		= new QRCodeReader().decode(bitmap, HINTS);
		return res.getText();
	}
}