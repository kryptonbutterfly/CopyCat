package kryptonbutterfly.copycat.ui.scan;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Window;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JPanel;

import kryptonbutterfly.copycat.assets.Assets;
import kryptonbutterfly.copycat.startup.CopyCat;
import kryptonbutterfly.util.swing.ObservableDialog;
import kryptonbutterfly.util.swing.events.GuiCloseEvent;

@SuppressWarnings("serial")
public class ScanGui extends ObservableDialog<BL, String, Void>
{
	final static int padding = 5;
	
	final JButton			btnAbort		= new JButton("abort");
	final ImageComponent	cameraDisplay	= new ImageComponent();
	
	public ScanGui(Window owner, ModalityType modality, Consumer<GuiCloseEvent<String>> closeListener, String title)
	{
		super(owner, modality, closeListener);
		CopyCat.prefs().scanWindow.setBounds(this);
		setTitle(title);
		setIconImage(Assets.getQr16Background(getBackground()).getImage());
		
		final var camPanel = new JPanel(new BorderLayout(padding, padding));
		add(camPanel, BorderLayout.CENTER);
		camPanel.add(cameraDisplay, BorderLayout.CENTER);
		cameraDisplay.setBackground(Color.RED);
		
		final var abortPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		abortPanel.add(btnAbort);
		abortPanel.setBackground(new Color(0x80, 0x80, 0x80, 0x30));
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
