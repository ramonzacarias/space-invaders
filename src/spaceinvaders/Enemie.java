package spaceinvaders;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemie {
	
	private BufferedImage imagem;
	private int x;
	private int y;
	private int speed;
	private int direction;
	
	public Enemie(int x, int y, int direction) {
		// TODO Auto-generated constructor stub
		try {
			imagem = ImageIO.read(new File("res/entity/enemie.png"));
		} catch (IOException e) {
			System.out.println("Não foi possivel carregar o enemie!");
			e.printStackTrace();
		}
		
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.speed = 1;
	}
	
	public void update() {
		x += speed * direction;
	}
	
	public void changeDirection() { //Muda os inimigos de direção
		direction = direction *-1;
		y+= 15;
	}
	
	public int getX() { 
		//Retorna a posição em "X" do inimigo
		return x;
	}
	
	public int getY() {
		// Retorna a posição em "Y" do inimigo
		return y;
	}
	
	public void pintar(Graphics2D g) {
		
		g.drawImage(imagem, x, y, x+55, y+70, 0, 0, imagem.getWidth(), imagem.getHeight(), null);
	}

	public int getTam() {
		// retorna o tamanho do inimigo
		return 55;
	}

}
