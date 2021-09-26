package gameTest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

					//Extende o JPanel
public class Canvas extends JPanel implements Runnable, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Robo javaBot;
	private ArrayList<Shooter> shooters;
	
	public Canvas() {
		// TODO Auto-generated constructor stub
		
		Shooter.loadImage();
		javaBot = new Robo();
		shooters = new ArrayList<Shooter>();
		Thread gameLoop = new Thread(this);
		gameLoop.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while (true) {
			
			tick();
			repaint();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void tick() {
		
		System.out.println("Tiros na tela = "+shooters.size());
		
		javaBot.tick();
		
		for (int i = 0; i < shooters.size(); i++) {
			shooters.get(i).tick();
		}
	}
	
	
	@Override // Sobre escrever o método paintComponent do JPanel
	public void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		
		Graphics2D g = (Graphics2D) g2.create();
		
		//Desenhando o fundo
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 800,600);
		
		//Desenhado o chão
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 442, 800, 300);
		
		//Desenhando o robo
		javaBot.pintar(g);
		
		//shooters
		for (int i = 0; i < shooters.size(); i++) {
			shooters.get(i).pintar(g);
		}
		
		//remove shooters
		for (int i = 0; i < shooters.size(); i++) {
			if (shooters.get(i).remove()) {
				shooters.remove(i);
				i--;
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getKeyCode() == KeyEvent.VK_D) {
			javaBot.setDirection(1);
			
		}else if (e.getKeyCode() == KeyEvent.VK_A) {
			javaBot.setDirection(-1);
			
		}
		
		if (e.getKeyCode() == KeyEvent.VK_W) {
			javaBot.startJump();
			
		}
		
		if (e.getKeyCode() == KeyEvent.VK_M) {
			javaBot.morrer();
			
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			
			if (javaBot.canShoot()) {
				shooters.add(javaBot.shoot());
			}
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_A) {
			javaBot.setDirection(0);
		}
		
	}


	
}
