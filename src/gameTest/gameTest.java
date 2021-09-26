package gameTest;

import javax.swing.JFrame;

public class gameTest {
	
	private final static int WIDTH = 800;
	private final static int HEIGHT = 600;
	
	public static void main(String[] args) {
		
		/**
		* (Pega a resolução do computador)
		* Toolkit tk;
		* tk = Toolkit.getDefaultToolkit();
		* Dimension d = tk.getScreenSize();
		* System.out.println("Resolução: "+d.width+" X "+d.height);
		* 
		**/
		
		JFrame janela = new JFrame("WINDOWS");
		janela.setSize(WIDTH,HEIGHT);
		janela.setResizable(false);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setLocationRelativeTo(null);
		
		Canvas canvas = new Canvas();
		janela.add(canvas);
		janela.addKeyListener(canvas);
		
		janela.setVisible(true);
	}
}

