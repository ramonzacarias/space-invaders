package spaceinvaders;

import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet {
	
	private int x;
	private int y;
	private int tamX = 3, tamY = 15;
	private double speed;
	
	public Bullet(int x, int y) {
		this.x = x;
		this.y = y;
		speed = 10;
	}
	
	public void pintar(Graphics2D g) {
		//Desenha o tiro
		g.setColor(Color.orange);
		g.fillRect(x, y, tamX, tamY);
	}
	
	public void update() {
		//movimentação do tiro
		y-=speed; 
	}

	public boolean destroy() {
		// TODO Auto-generated method stub
		return y < 0;
	}

	public boolean collision(Enemie enemie) {
		// TODO Auto-generated method stub
		
		if (x >= enemie.getX() && x + tamX < enemie.getX() + enemie.getTam()) {
			
			if ( y <= enemie.getY() + enemie.getTam()) {
				return true;
			}
		}
		
		return false;
	}
	
}