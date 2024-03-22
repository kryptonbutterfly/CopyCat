package kryptonbutterfly.copycat.ui.main;

import static kryptonbutterfly.copycat.Globals.*;

import java.awt.Color;
import java.awt.Dialog.ModalityType;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import kryptonbutterfly.copycat.Globals;
import kryptonbutterfly.copycat.ui.scan.ScanGui;
import kryptonbutterfly.copycat.utils.Utils;
import kryptonbutterfly.monads.opt.Opt;
import kryptonbutterfly.util.swing.Logic;
import lombok.SneakyThrows;

final class BL extends Logic<MainGui, Void>
{
	private static final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	
	BL(MainGui gui)
	{
		super(gui);
	}
	
	void scan(ActionEvent ae)
	{
		gui.if_(
			gui -> EventQueue.invokeLater(
				() -> new ScanGui(
					gui,
					ModalityType.APPLICATION_MODAL,
					gce -> gce.getReturnValue().map(StringSelection::new).if_(t -> clipboard.setContents(t, t)),
					"Scan Qr Code")));
	}
	
	void refresh(ActionEvent ae)
	{
		gui.if_(
			gui -> getData().if_(data ->
			{
				gui.lblQR.setToolTipText(data);
				Utils.generateQr(data, Color.BLACK, Color.WHITE, QR_CODE_SIZE, ErrorCorrectionLevel.H)
					.map(ImageIcon::new)
					.if_(icon ->
					{
						gui.lblQR.setIcon(icon);
						gui.lblQR.setToolTipText(data);
					})
					.else_(() ->
					{
						gui.lblQR.setIcon(null);
						gui.lblQR.setToolTipText(null);
					});
			}));
	}
	
	void clear(ActionEvent ae)
	{
		gui.if_(gui -> {
			gui.lblQR.setIcon(null);
			gui.lblQR.setToolTipText(null);
		});
	}
	
	@Override
	protected void disposeAction()
	{
		gui.if_(Globals.prefs.mainWindow::persistBoundsAndState);
	}
	
	@SneakyThrows
	private Opt<String> getData()
	{
		final var flavor = DataFlavor.stringFlavor;
		if (clipboard.isDataFlavorAvailable(flavor))
			return Opt.of((String) clipboard.getData(flavor));
		return Opt.empty();
	}
}
