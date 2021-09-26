package spaceinvaders;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Nave {
	
	BufferedImage imagem;
	public int x, y;
	public double speed;
	private int reload;
	private boolean enableShoot;
	
	public Nave() {
		// TODO Auto-generated constructor stub
		try {
			imagem = ImageIO.read(new File("res/entity/naveV2.png"));
		} catch (IOException e) {
			System.out.println("Não foi possivel carregar a imagem!");
			e.printStackTrace();
		}
		
		this.x = 350; this.y = 450;
		this.speed = 3;
		enableShoot = true;
	}
	
	public void tick() {
		
	}
	
	public void pintar(Graphics2D g) {
			        //imagem, posx, posy - tamx, tamy - toda imagem
		g.drawImage(imagem, x, y, x+100, y+100, 0, 0, imagem.getWidth(), imagem.getHeight(), null);
	}
	
	public Bullet shoot() {
		enableShoot = false;
		reload = 0;
		Bullet bullet = new Bullet(x+49, y+23);
		return bullet;
	}
	
	public void mover(int valor) {
		
		if (valor == 1) {
			x+=speed;
		}else if (valor == -1) {
			x-=speed;
		}
		//Condição para criar intervalos entre os tiros
		if (reload >= 15) {
			enableShoot = true;
			reload = 0;
		}
		reload++;
	}
	
	public boolean enableShoot() {
		//Quando retornado "True" permite o tiro
		return enableShoot; 
	}
	
}
