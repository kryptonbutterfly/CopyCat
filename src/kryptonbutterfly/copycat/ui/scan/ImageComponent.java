package kryptonbutterfly.copycat.ui.scan;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public final class ImageComponent extends JComponent
{
	private BufferedImage image = null;
	
	public void setImage(BufferedImage image)
	{
		this.image = image;
		repaint();
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		if (image == null)
			return;
		
		final float	componentAspect	= ((float) getWidth()) / getHeight();
		final float	imageAspect		= ((float) image.getWidth()) / image.getHeight();
		
		final int resultWidth, resultHeight;
		if (componentAspect < imageAspect)
		{
			resultWidth		= getWidth();
			resultHeight	= (int) (getHeight() * componentAspect / imageAspect);
		}
		else
		{
			resultHeight	= getHeight();
			resultWidth		= (int) (getWidth() / componentAspect * imageAspect);
		}
		
		final int	xOff	= (getWidth() - resultWidth) / 2;
		final int	yOff	= (getHeight() - resultHeight) / 2;
		
		g.drawImage(
			image,
			xOff,
			yOff,
			xOff + resultWidth,
			yOff + resultHeight,
			0,
			0,
			image.getWidth(),
			image.getHeight(),
			null);
	}
}
