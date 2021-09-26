package spaceinvaders;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class SpaceInvaders extends JPanel implements Runnable, KeyListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Nave nave;
	private Wallpaper wallpaper;
	private ArrayList<Enemie> enemies;
	private ArrayList<Bullet> bullet;
	private int movimento;
	
	public SpaceInvaders() {
		
		nave = new Nave();
		wallpaper = new Wallpaper();
		bullet = new ArrayList<Bullet>(); //Guarda os "tiros" em um array, para exibir varios tiros.
		enemies = new ArrayList<Enemie>();
		for (int i = 0; i < 50; i++) {
			enemies.add(new Enemie(50 + i % 10 * 60, 28 + i/10 * 28,1));
		}
		
		Thread gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			tick(); //Atualiza o jogo
			repaint(); //Redesenha o jogo
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void tick() {
		// TODO Auto-generated method stub
		nave.mover(movimento);
		
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update();
		}
		
		for (int i = 0; i < bullet.size(); i++) {
			bullet.get(i).update();
			
			if (bullet.get(i).destroy()) { //Remove os tiros que estão fora da tela
				bullet.remove(i);
				i--;
			}else {
				//Verifica a colisão com os inimigos
				for (int j = 0; j < enemies.size(); j++) {
					if (bullet.get(i).collision(enemies.get(j))) {
						enemies.remove(j);
						j--;
						bullet.remove(i);
						break;
					}
				}
			}
			
		}
		
		// Muda a direção dos inimigos
		for (int i = 0; i < enemies.size(); i++) { 
			if (enemies.get(i).getX() <= 0 || enemies.get(i).getX() >= 800 - 70) {
				
				for (int j = 0; j < enemies.size(); j++) {
					enemies.get(j).changeDirection();
				}
				break;
			}
		}
		
		System.out.println(bullet.size());
	}
	
	public void paintComponent(Graphics g2) {
		super.paintComponent(g2); //Limpar a tela
		
		//Ativa o antialiasing
		Graphics2D g = (Graphics2D) g2.create();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		wallpaper.pintar(g); //Rendenriza a imagem no fundo da tela.
		nave.pintar(g); //Rendenriza a nave
		
		
		//Renderiza os inimigos
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).pintar(g);
		}
		
		//Rendereiza o tiro
		for (int i = 0; i < bullet.size(); i++) {
			bullet.get(i).pintar(g);;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			movimento = 1;
		}else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			movimento = -1;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			movimento = 0;
		}else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			movimento = 0;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_SPACE && nave.enableShoot()) {
			bullet.add(nave.shoot());
		}
	}

}
