package gameTest;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Shooter {
	
	private int posX, posY;
	private int speed;
	private int width, height;
	private int direction;
	
	private static BufferedImage bullet[];
	private int indice;
	
	public static void loadImage() {
		
		bullet = new BufferedImage[5];
		
		for (int i = 0; i < bullet.length; i++) {
			
			try {
				bullet[i] = ImageIO.read(new File("imagemRobo/Objects/Bullet_00"+i+".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public Shooter(int startX, int startY, int direction) {
		// TODO Auto-generated constructor stub
		
		this.posX = startX;
		this.posY = startY;
		this.direction = direction;
		this.speed = 10;
		
		this.width = 20;
		this.height = 20;
		
	} 
	
	public void tick() {
		
		posX += direction * speed;
		
		indice++;
		if (indice >= 5) {
			indice = 0;
		}
	}
	
	public void pintar(Graphics2D g) {
		
		if (direction == 1) {
			
			g.drawImage(bullet[indice],									
					posX, posY,											
					posX + width, posY + height,						
					0, 0,												
					bullet[indice].getWidth(), bullet[indice].getHeight(),	
					null);
			
		}else if (direction == -1) {
			
			g.drawImage(bullet[indice],									
					posX, posY,											
					posX + width, posY + height,						
					bullet[indice].getWidth(), 0,												
					0, bullet[indice].getHeight(),	
					null);
			
		}
	}

	public boolean remove() {
		if (posX < 0 || posX > 800) {
			return true;
		}
		return false;
	}
}
