package gameTest;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Robo {
	
	private BufferedImage run[];
	private BufferedImage idle[];
	private BufferedImage jump[];
	private BufferedImage dead[];
	private BufferedImage runShoot[];
	
	private int indice, indiceTiro;
	private int timer, timerTiro;
	private int jumpY;
	private int height, width;
	private int posX, posY;
	private int direction, speed, lastDirection;
	
	private boolean statusJump;
	private boolean statusShoot;
	private boolean morreu;
	
	public Robo() {
		// TODO Auto-generated constructor stub
		
		run = new BufferedImage[8];
		idle = new BufferedImage[10];
		jump = new BufferedImage[10];
		dead = new BufferedImage[10];
		runShoot = new BufferedImage[9];
		
		indice = 0;
		timer = 0;
		jumpY = 3;
		height = 100; width = 100;
		posX = 100; posY = 350;
		direction = 0; lastDirection = 1; speed = 3;
		
		statusJump = false;
		morreu = false;
		statusShoot = false;
		
		try {
			
			//Running
			for (int i = 0; i < run.length; i++) {
				String imagemRun = "imagemRobo/Run ("+(i+1)+").png";
				
					run[i] = ImageIO.read(new File(imagemRun));
				
			}
			
			//Stopped
			for (int i = 0; i < idle.length; i++) {
				String imagemIdle = "imagemRobo/idle ("+(i+1)+").png";
				
					idle[i] = ImageIO.read(new File(imagemIdle));
				}
			
			//Jumping
			for (int i = 0; i < jump.length; i++) {
				String imagemJump = "imagemRobo/jump ("+(i+1)+").png";
				
				jump[i] = ImageIO.read(new File(imagemJump));
			}
			
			//Dead
			for (int i = 0; i < dead.length; i++) {
				String imagemRun = "imagemRobo/Dead ("+(i+1)+").png";
				
					dead[i] = ImageIO.read(new File(imagemRun));
				
			}
			
			//Run Shoot
			for (int i = 0; i < runShoot.length; i++) {
				String imagemRunShoot = "imagemRobo/RunShoot ("+(i+1)+").png";
				
					runShoot[i] = ImageIO.read(new File(imagemRunShoot));
				
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Não foi possivel carregar a imagem!");
			e.printStackTrace();
		}
	}
	
	public void tick() {
		
		//Responsavel por atualizar a animação do robo
		timer++;
		
		if(!morreu) {
			
			if (statusShoot) {
				timerTiro++;
				if (timerTiro == 5) {
					indiceTiro++;
					if (indiceTiro == runShoot.length) {
						indiceTiro = 0;
						statusShoot = false;
					}
					timerTiro = 0;
				}
			}
			
			if (statusJump) {
				
				if (timer == 5) {
					indice++;
					if (indice == jump.length) {
						indice = 0;
						statusJump = false;
					}
					timer = 0;
				}
				
				if(indice < 5) {
					posY -= jumpY;
				}else {
					posY += jumpY;
				}
				
			}else {
				
				if (timer == 5) {
					indice++;
					if (indice == run.length) {
						indice = 0;
					}
					timer = 0;
				}
			}
			
			//Direção que o robo anda
			if (direction == 1) {
				posX += speed;
			}else if (direction == -1) {
				posX -= speed;
			}
			
		}else {
			
			if (timer == 5) {
				indice++;
				if (indice >= dead.length) {
					indice = 9;
				}
				timer = 0;
			}
		}
		
	}
	
	public void pintar(Graphics2D g) {
		
		//Redimensionamento da imagem do robo
		if (morreu) {
			
			if (lastDirection == 1) {
				g.drawImage(dead[indice],									
						posX, posY,											
						posX + width, posY + height,						
						0, 0,												
						dead[indice].getWidth(), dead[indice].getHeight(),	
						null);
			} else {
				g.drawImage(dead[indice],									
						posX, posY,											
						posX + width, posY + height,						
						dead[indice].getWidth(), 0,												
						0, dead[indice].getHeight(),	
						null);
			}
			
		}else if (statusJump) {
			if (lastDirection == 1) {
				g.drawImage(jump[indice],									
						posX, posY,											
						posX + width, posY + height,						
						0, 0,												
						jump[indice].getWidth(), jump[indice].getHeight(),	
						null);
			} else {
				g.drawImage(jump[indice],									
						posX, posY,											
						posX + width, posY + height,						
						jump[indice].getWidth(), 0,												
						0, jump[indice].getHeight(),	
						null);
			}
		
		}else if (statusShoot) {
			
			if (lastDirection == 1) {
				g.drawImage(runShoot[indiceTiro],									
						posX, posY,											
						posX + width, posY + height,						
						0, 0,												
						runShoot[indiceTiro].getWidth(), runShoot[indiceTiro].getHeight(),	
						null);
			} else {
				g.drawImage(runShoot[indiceTiro],									
						posX, posY,											
						posX + width, posY + height,						
						runShoot[indiceTiro].getWidth(), 0,												
						0, runShoot[indiceTiro].getHeight(),	
						null);
			}
			
		}else if (lastDirection == 1) {
			if (direction == 0) {
				g.drawImage(idle[indice],									//Imagem a ser desenhada
						posX, posY,											//Posição x e y da imagem
						posX + width, posY + height,						//Posição + tamanho da imagem
						0, 0,												//Canto da imagem original
						idle[indice].getWidth(), idle[indice].getHeight(),	//Tamanho da imagem original
						null);
				
				
			} else {
				g.drawImage(run[indice],									
						posX, posY,											
						posX + width, posY + height,						
						0, 0,												
						run[indice].getWidth(), run[indice].getHeight(),	
						null);
			}

		}else if (lastDirection == -1) {
			if (direction == 0) {
				g.drawImage(idle[indice],								
						posX, posY,											
						posX + width, posY + height,						
						idle[indice].getWidth(), 0,											
						0, idle[indice].getHeight(),	
						null);
				
			}else {
				g.drawImage(run[indice],									
						posX, posY,											
						posX + width, posY + height,						
						run[indice].getWidth(), 0,							
						0, run[indice].getHeight(),							
						null);
			}	
		}
		
	}
	
	public void setDirection(int dir) {
		if (dir != 0) {
			this.lastDirection = dir;
			this.direction = dir;
		}else {
			this.direction = dir;
		}
		
	}

	public void startJump() {
		// TODO Auto-generated method stub
		
		if (statusJump == false) {
			statusJump = true;
			timer = 0;
			indice = 0;
			
		}
		
	}
	
	public void morrer() {
		morreu = true;
		indice = 0;
	}
	
	public Shooter shoot() {
		
		statusShoot = true;
		Shooter shooter;
		
		if (lastDirection == 1) {
			shooter = new Shooter(posX + width - 18, posY + 43, lastDirection);
		} else {
			shooter = new Shooter(posX, posY + 43, lastDirection);
		}
		
		return shooter;
	}
	
	public boolean canShoot() {
		
		if (statusShoot == false) {
			return true;
		}
		return false;
	}

}
