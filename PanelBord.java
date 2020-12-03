

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

class Bord extends Objet{
	final static int LON_PETIT = 15;
	final static int EPAI_PETIT = 10;

	Bord(int x,int y,int lon,int epai,Color co){
		super(x,y,lon,epai,0,co);
	}

	Bord(int x,int y,int lon,int epai){
		this(x,y,lon,epai,Color.red);
	}

	Bord(int x,int y){
		this(x,y,700,450);
	}

	Bord(){
		this(400,150);
	}
	
	public void dessinerPetitH(int x,int y,Graphics g){
		g.setColor(couleur);
		g.fillRect(x,y,LON_PETIT,EPAI_PETIT);
		g.setColor(Color.black);
		g.drawLine(x,y,x+LON_PETIT,y);
		g.drawLine(x,y,x,y+EPAI_PETIT);
		g.drawLine(x+LON_PETIT,y,x+LON_PETIT,y+EPAI_PETIT);
		g.drawLine(x,y+EPAI_PETIT,x+LON_PETIT,y+EPAI_PETIT);
	}

	public void dessinerPetitV(int x,int y,Graphics g){
		g.setColor(couleur);
		g.fillRect(x,y,EPAI_PETIT,LON_PETIT);
		g.setColor(Color.black);
		g.drawLine(x,y,x+EPAI_PETIT,y);
		g.drawLine(x,y,x,y+LON_PETIT);
		g.drawLine(x+EPAI_PETIT,y,x+EPAI_PETIT,y+LON_PETIT);
		g.drawLine(x,y+LON_PETIT,x+EPAI_PETIT,y+LON_PETIT);
	}

	public void dessiner(Graphics g){
		for(int i=0;i<longueur;i+=LON_PETIT){
			dessinerPetitH(x+i,y,g);
			dessinerPetitH(x+i,y+epaisseur,g);
		}

		for(int i=0;i<epaisseur;i+=LON_PETIT){
			dessinerPetitV(x,y+i,g);
			dessinerPetitV(x+longueur,y+i,g);
		}
	}
}

public class PanelBord extends JPanel implements Runnable{
	static final Obstacles[] OBSTACLES = {new ObstaclesCanard(),new ObstaclesPONG(),new ObstaclesCoeur(),new ObstaclesPomme(),new ObstaclesChampignon()};
	Bord bord;
	Balle balle;
	Palette pal;
	Tireur tireur;
	Obstacles obstacles;
	Score score;
	boolean game = true;
	boolean pause = true;
	int xcentre;
	int ycentre;
	
	
	public PanelBord(int lon,int lar){
		/** lon = this.getSize().height;
		int lar = this.getSize().width;**/
		bord = new Bord(lon,lar);
		balle = new Balle(bord);
		pal = new Palette(bord);
		tireur = new Tireur(bord);
		score = new Score(bord);
		int j = (int)(Math.random()*37)%OBSTACLES.length;
		obstacles = OBSTACLES[3];
		obstacles.generer(bord);

		balle.setPalette(pal);
		obstacles.setPalette(pal);
		tireur.setPalette(pal);

		balle.setObstacles(obstacles);
		tireur.setObstacles(obstacles);

		addKeyListener(new Touche());
		(new Thread(this)).start();
	}
	public Bord getBord(){
		return bord;
	}

	public void paint(Graphics g){
		bord.effacer(g,getBackground());
		balle.effacer(g,getBackground());
		pal.effacer(g,getBackground());
		score.effacer(g,getBackground());
		tireur.dessiner(g);
		obstacles.dessiner(g);
		bord.dessiner(g);
		pal.dessiner(g);
		balle.dessiner(g);
		score.dessiner(g);
		if(balle.getVie()==-1)
			(new GameOver(bord)).dessiner(g);
	} 
	public void run(){
		for(;;){
			if(!pause && balle.getVie()!=-1 && obstacles.getObstacleRestant()!=0){
				Ensemble t= balle.deplacer();
				game = t.getBoolean();
				obstacles = t.getObstacles();
				score.setScore(score.getScore()+t.getScore());
				if(game){
					pause = true;
					obstacles.initialiser();
					pal.initialiser();
					balle.setPalette(pal);
					balle.initialiser();
					balle.mort();
					score.setVie(balle.getVie());
					tireur.initialiser();
				}
				else{
					Ensemble k=tireur.deplacer();
					game = k.getBoolean();
					obstacles = k.getObstacles();
					score.setScore(score.getScore()+k.getScore());
					if(game){
						pause = true;
						obstacles.initialiser();
						pal.initialiser();
						balle.setPalette(pal);
						balle.initialiser();
						balle.mort();
						score.setVie(balle.getVie());
						tireur.initialiser();
					}
					else{
						Ensemble tj = pal.changerPalette(obstacles);
						obstacles=tj.getObstacles();
						score.setScore(score.getScore()+tj.getScore());
						Ensemble tu = obstacles.deplacer();
						game = tu.getBoolean();
						score.setScore(score.getScore()+tj.getScore());
						if(game){
							pause = true;
							obstacles.initialiser();
							pal.initialiser();
							balle.setPalette(pal);
							balle.initialiser();
							balle.mort();
							score.setVie(balle.getVie());
							tireur.initialiser();
						}
					} 
				}
			}
			else if(obstacles.getObstacleRestant() == 0){
				obstacles = OBSTACLES[(int)(Math.random()*79)%OBSTACLES.length];
				obstacles.generer(bord);
				game = true;
				pause = true;
				pal.initialiser();
				obstacles.setPalette(pal);
				balle.initialiser();
				tireur.setPalette(pal);
				balle.setObstacles(obstacles);
				tireur.setObstacles(obstacles);
				tireur.initialiser();
			}
			else if(balle.getVie()==-1)
				score.enregistrerScore();
			repaint();
			try{
				Thread.sleep(balle.getVitesse());
			}catch(InterruptedException i){
				System.out.println(i);
			}
		}
	}

	class Touche extends KeyAdapter{
		public void keyPressed(KeyEvent k){
			switch(k.getKeyCode()){
				case KeyEvent.VK_P:{
					if(balle.getVie()!=-1){
						if(obstacles.getObstacleRestant()==0){
							obstacles = OBSTACLES[(int)(Math.random()*79)%OBSTACLES.length];
							obstacles.generer(bord);
						}
						pause = !pause;
						game = false;
					}
				}
				break;
				case KeyEvent.VK_LEFT:case KeyEvent.VK_A:case KeyEvent.VK_D:{
					if(balle.getVie()!=-1 && (((!pal.toucheMurDroite() && pal.getInverse()) || (!pal.toucheMurGauche() && !pal.getInverse())) && (!pause || (pause && game)) )){
						pal.setXY(pal.getXY().x-pal.getVitesse(),pal.getXY().y);
						if(balle.touchePalette())
							balle.setXY(balle.getXY().x-pal.getVitesse(),balle.getXY().y);
					}
					balle.setPalette(pal);
					tireur.setPalette(pal);
					obstacles.setPalette(pal);
				}
				break;
				case KeyEvent.VK_RIGHT:case KeyEvent.VK_K:case KeyEvent.VK_E:{
					if(balle.getVie()!=-1 && (((!pal.toucheMurGauche() && pal.getInverse()) || (!pal.toucheMurDroite() && !pal.getInverse())) && (!pause || (pause && game)) )){
						pal.setXY(pal.getXY().x+pal.getVitesse(),pal.getXY().y);
						if(balle.touchePalette())
							balle.setXY(balle.getXY().x+pal.getVitesse(),balle.getXY().y);
					}
					balle.setPalette(pal);
					tireur.setPalette(pal);
					obstacles.setPalette(pal);
				}
				break;
				case KeyEvent.VK_ESCAPE:{
					System.exit(0);
				}
				break;
				case KeyEvent.VK_ENTER:{
					if(balle.getVie()==-1){
						balle.setVie();
						obstacles = OBSTACLES[(int)(Math.random()*79)%OBSTACLES.length];
						obstacles.generer(bord);

						balle.setPalette(pal);
						obstacles.setPalette(pal);
						tireur.setPalette(pal);

						balle.setObstacles(obstacles);
						tireur.setObstacles(obstacles);
						score.initialiser();
						repaint();
					}
				}
				break;
			}
			repaint();
		}
	}
}

