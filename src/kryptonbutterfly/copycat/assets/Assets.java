package kryptonbutterfly.copycat.assets;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import kryptonbutterfly.copycat.utils.Utils;
import lombok.SneakyThrows;

public class Assets
{
	@SneakyThrows
	private static BufferedImage loadImage(String image)
	{
		try (final var iStream = Assets.class.getResourceAsStream(image))
		{
			var img = ImageIO.read(iStream);
			if (img != null)
				return img;
			throw new IllegalArgumentException(
				"The asset \"%s\" is not a supported image type!".formatted(image));
		}
	}
	
	public static final BufferedImage	APP_IMAGE	= loadImage("/assets/icon_128.png");
	public static final ImageIcon		APP_ICON	= new ImageIcon(APP_IMAGE);
	
	private static final ImageIcon	QR_ICON_LIGHT_16	= new ImageIcon(loadImage("/assets/qr-icon-light_16.png"));
	private static final ImageIcon	QR_ICON_DARK_16		= new ImageIcon(loadImage("/assets/qr-icon-dark_16.png"));
	
	private static final ImageIcon	CAM_ICON_LIGHT	= new ImageIcon(loadImage("/assets/camera-light.png"));
	private static final ImageIcon	CAM_ICON_DARK	= new ImageIcon(loadImage("/assets/camera-dark.png"));
	
	public static ImageIcon getQr16Background(Color bgColor)
	{
		return Utils.isBright(bgColor, QR_ICON_LIGHT_16, QR_ICON_DARK_16);
	}
	
	public static ImageIcon getCamBackground(Color bgColor)
	{
		return Utils.isBright(bgColor, CAM_ICON_LIGHT, CAM_ICON_DARK);
	}
}