package kryptonbutterfly.copycat.ui.main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kryptonbutterfly.copycat.Globals;
import kryptonbutterfly.copycat.assets.Assets;
import kryptonbutterfly.util.swing.ObservableGui;
import kryptonbutterfly.util.swing.events.GuiCloseEvent;

@SuppressWarnings("serial")
public final class MainGui extends ObservableGui<BL, Void, Void>
{
	private final FlowLayout flowCenter = new FlowLayout(FlowLayout.CENTER);
	
	private final JButton	btnScan;
	private final JButton	btnRefresh;
	private final JButton	btnClear;
	final JLabel			lblQR	= new JLabel();
	
	public MainGui(Consumer<GuiCloseEvent<Void>> closeListener)
	{
		super(closeListener);
		setTitle("CopyCat");
		Globals.prefs.mainWindow.setBoundsAndState(this);
		
		btnScan		= new JButton("import", Assets.getCamBackground(getBackground()));
		btnRefresh	= new JButton("refresh qr", Assets.getQr16Background(getBackground()));
		btnClear	= new JButton("clear qr");
		
		final var buttonPanel = new JPanel(new BorderLayout());
		add(buttonPanel, BorderLayout.NORTH);
		
		final var scanPanel = new JPanel(flowCenter);
		buttonPanel.add(scanPanel, BorderLayout.NORTH);
		scanPanel.add(btnScan);
		
		final var qrPanel = new JPanel(flowCenter);
		buttonPanel.add(qrPanel, BorderLayout.CENTER);
		qrPanel.add(btnRefresh);
		qrPanel.add(btnClear);
		
		final var qrIconPanel = new JPanel(flowCenter);
		add(qrIconPanel, BorderLayout.CENTER);
		qrIconPanel.add(lblQR);
		
		businessLogic.if_(this::init);
	}
	
	private void init(BL bl)
	{
		btnScan.addActionListener(bl::scan);
		btnRefresh.addActionListener(bl::refresh);
		btnClear.addActionListener(bl::clear);
	}
	
	@Override
	protected BL createBusinessLogic(Void arg0)
	{
		return new BL(this);
	}
}