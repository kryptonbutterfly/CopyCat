package kryptonbutterfly.copycat.ui.scan;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kryptonbutterfly.copycat.Globals;
import kryptonbutterfly.copycat.assets.Assets;
import kryptonbutterfly.util.swing.ObservableDialog;
import kryptonbutterfly.util.swing.events.GuiCloseEvent;

@SuppressWarnings("serial")
public class ScanGui extends ObservableDialog<BL, String, Void>
{
	final JButton btnAbort = new JButton("abort");
	
	final JLabel cameraDisplay = new JLabel();
	
	public ScanGui(Window owner, ModalityType modality, Consumer<GuiCloseEvent<String>> closeListener, String title)
	{
		super(owner, modality, closeListener);
		Globals.prefs.scanWindow.setBounds(this);
		setTitle(title);
		setIconImage(Assets.getQr16Background(getBackground()).getImage());
		
		final var camPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		add(camPanel, BorderLayout.CENTER);
		camPanel.add(cameraDisplay);
		
		final var abortPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		abortPanel.add(btnAbort);
		add(abortPanel, BorderLayout.SOUTH);
		
		businessLogic.if_(this::init);
		
		setVisible(true);
	}
	
	private void init(BL bl)
	{
		btnAbort.addActionListener(bl::abort);
		btnAbort.addKeyListener(bl.escapeListener);
		
		bl.scanCamera();
	}
	
	@Override
	protected BL createBusinessLogic(Void args)
	{
		return new BL(this);
	}
}
