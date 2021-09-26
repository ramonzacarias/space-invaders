package spaceinvaders;

import javax.swing.JFrame;

public class MainGame {
	
	private final static int WIDTH = 800;
	private final static int HEIGHT = 600;
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("SPACE INVARDES");
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null); //N�o utiliza layout padr�o do java
		
		SpaceInvaders spaceInvaders = new SpaceInvaders();
		/*Especificar a posi��o e o tamanho do componente Space Invaders*/
		spaceInvaders.setBounds(0,0,WIDTH, HEIGHT); 
		
		frame.add(spaceInvaders);
		frame.addKeyListener(spaceInvaders);
		
		frame.setVisible(true);
	}
}
