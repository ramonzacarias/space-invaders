package spaceinvaders;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Wallpaper {
	
	private BufferedImage image;
	private int y;
	
	public Wallpaper() {
		
		try {
			image = ImageIO.read(new File("res/graphics/fundo.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void pintar(Graphics2D g) {
		
		g.drawImage(image, 0, y-720, image.getWidth(), image.getHeight(), null);
		
		g.drawImage(image, 0, y, image.getWidth(), image.getHeight(), null);
		
		y+=1;
		
		if (y >= 720) {
			y=0;
		}
	}

}
