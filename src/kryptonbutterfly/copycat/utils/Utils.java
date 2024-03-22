package kryptonbutterfly.copycat.utils;

import static kryptonbutterfly.math.utils.range.Range.*;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import kryptonbutterfly.functions.bool_.BoolToIntFunction;
import kryptonbutterfly.monads.failable.Failable;
import kryptonbutterfly.monads.opt.Opt;

public final class Utils
{
	private Utils()
	{}
	
	public static <E> E isBright(Color color, E light, E dark)
	{
		final var hsb = new float[3];
		Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsb);
		return hsb[2] < 0.5 ? dark : light;
	}
	
	public static final BufferedImage mirror(BufferedImage src)
	{
		final int	width	= src.getWidth();
		final int	height	= src.getHeight();
		final var	result	= new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		
		final var widthRange = range(width);
		
		for (final int y : range(height))
			for (final int x : widthRange)
				result.setRGB(width - x - 1, y, src.getRGB(x, y));
			
		return result;
	}
	
	public static final Opt<BufferedImage> generateQr(
		String data,
		Color dark,
		Color light,
		int size,
		ErrorCorrectionLevel level)
	{
		final BoolToIntFunction	colorConverter	= bit -> bit ? dark.getRGB() : light.getRGB();
		final var				sizeRange		= range(size);
		
		return Failable.attempt(
			() -> new MultiFormatWriter().encode(
				data,
				BarcodeFormat.QR_CODE,
				size,
				size,
				Map.of(EncodeHintType.ERROR_CORRECTION, level.name())))
			.toOpt(e -> e.printStackTrace())
			.map(matrix ->
			{
				final var image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
				for (int y : sizeRange)
					for (int x : sizeRange)
						image.setRGB(x, y, colorConverter.apply(matrix.get(x, y)));
				return image;
			});
	}
}
