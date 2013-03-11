package org.http.server.pic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class FilePic implements IPic {
	private String src;
	public FilePic(String _src){
		this.src = _src;
	}
	public boolean output(OutputStream output) {
		try {
			BufferedImage image = ImageIO.read(new File(this.src));
			String extension = src.substring(src.lastIndexOf('.')+1);
			ImageIO.write(image, extension , output);
			return true;
		} catch (IOException e) {
			//e.printStackTrace();
			return false;
		}
	}

}
