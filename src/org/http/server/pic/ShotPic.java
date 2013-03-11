package org.http.server.pic;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.OutputStream;

import javax.imageio.ImageIO;


public class ShotPic implements IPic {

	public ShotPic() {
		// TODO Auto-generated constructor stub
	}

	public boolean output(OutputStream output) {
		try{
			BufferedImage screenshot = (new Robot()).createScreenCapture(
					new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
	        ImageIO.write(screenshot, "png", output);
			return true;
		}catch(Exception ex){
			//ex.printStackTrace();
			return false;
		}
	}

}
