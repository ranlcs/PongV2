

import java.awt.Color;
import java.awt.Graphics;

class Ensemble{
	Obstacles ob;
	boolean game;
	int score;

	Ensemble(Obstacles o, boolean g,int s){
		ob = o;
		game = g;
		score = s;
	}

	public boolean getBoolean(){
		return game;
	}
	public Obstacles getObstacles(){
		return ob;
	}
	public int getScore(){
		return score;
	}
}
public class Balle extends Objet{
	static final int HAUT_GAUCHE =0;
	static final int HAUT_DROITE=1;
	static final int BAS_GAUCHE = 2;
	static final int BAS_DROITE = 3;
	static final int VITESSE_DEFAUT = 70;
	static final int X_DEFAUT= 450;
	static final int Y_DEFAUT = 450;
	static final int RAYON = 15;

	int direction = (int)(Math.random()*19)%2;
	int xinclinaison = 5;
	int yinclinaison = 5;
	Bord bord;
	Palette palette;
	Obstacles obstacles;
	int vie=2;

	public Balle(int x,int y,int rayon,Color cou,Bord bord){
		super(x,y,rayon,rayon,VITESSE_DEFAUT,cou);
		this.bord=bord;
	}

	public Balle(int x,int y,int rayon,Bord bord){
		this(x,y,rayon,Color.blue,bord);
	}

	public Balle(int x,int y,Bord bord){
		this(x,y,RAYON,bord);
	}
	public Balle(int x,int y){
		super(x,y,RAYON,RAYON,VITESSE_DEFAUT,Color.blue);
	}
	public Balle(Bord bord){
		this(X_DEFAUT,Y_DEFAUT,bord);
		int xcentre= bord.getXY().x+ (int)bord.getDim().width/2-RAYON/2;
		int ycentre=bord.getXY().y+bord.getDim().height-bord.getDim().height/10;
		setXY(xcentre,ycentre);
	}
	public void setObstacles(Obstacles ob){
		obstacles = ob;
	}
	public void dessiner(Graphics g){
		g.setColor(couleur);
		g.fillOval(x,y,longueur,epaisseur);
	}
	public int getVie(){
		return vie;
	}
	public void setVie(){
		vie=2;
	}
	boolean toucheMurHaut(){
		return (y<=bord.getXY().y+Bord.EPAI_PETIT);
	}
	boolean toucheMurBas(){
		return (y+longueur>=bord.getXY().y+bord.getDim().height-Bord.EPAI_PETIT);
	}
	boolean toucheMurGauche(){
		return (x<bord.getXY().x+Bord.EPAI_PETIT);
	}
	boolean toucheMurDroite(){
		return (x+longueur>=bord.getXY().x+bord.getDim().width-Bord.EPAI_PETIT);
	}
	boolean toucheObstacleHaut(Obstacle ob){
		boolean infx = x+longueur>=ob.getXY().x;
		boolean supx = x<=ob.getXY().x+ob.getDim().height;
		boolean infy = y+longueur>=ob.getXY().y;
		boolean supy = y+longueur<=ob.getXY().y+ob.getDim().height/2;
		boolean ret = !ob.getToucher();
		if(infx && supx && infy && supy && ret)
			ob.setToucher(true);
		return (infx && supx && infy && supy  && ret);
	}
	boolean toucheObstacleBas(Obstacle ob){
		boolean infx = x+longueur>=ob.getXY().x;
		boolean supx = x<=ob.getXY().x+ob.getDim().height;
		boolean infy = y<=ob.getXY().y+ob.getDim().height;
		boolean supy = y>=ob.getXY().y+ob.getDim().height/2;
		boolean ret = !ob.getToucher();
		if(infx && supx && infy && supy && ret)
			ob.setToucher(true);
		return (infx && supx && infy && supy && ret);
	}
	boolean toucheObstacleGauche(Obstacle ob){
		boolean infx = x+longueur>=ob.getXY().x;
		boolean supx = x+longueur<=ob.getXY().x+ob.getDim().height/2;
		boolean infy = y+longueur>=ob.getXY().y;
		boolean supy = y<=ob.getXY().y+ob.getDim().height;
		boolean ret = !ob.getToucher();
		if(infx && supx && infy && supy && ret)
			ob.setToucher(true);
		return (infx && supx && infy && supy && ret);
	}
	boolean toucheObstacleDroite(Obstacle ob){
		boolean infx = x<=ob.getXY().x+ob.getDim().height;
		boolean supx = x>=ob.getXY().x+ob.getDim().height/2;
		boolean infy = y+longueur>=ob.getXY().y;
		boolean supy = y<=ob.getXY().y+ob.getDim().height;
		boolean ret = !ob.getToucher();
		if(infx && supx && infy && supy && ret)
			ob.setToucher(true);
		return (infx && supx && infy && supy && ret);
	}
	boolean toucheObstaclesHaut(){
		for(int i=0;i<obstacles.getObstacles().size();i++){
			if(toucheObstacleHaut(obstacles.getObstacles().elementAt(i))){
				obstacles.getObstacles().elementAt(i).setToucher(true);
				return true;
			}
		}
		return false;
	}
	boolean toucheObstaclesBas(){
		for(int i=0;i<obstacles.getObstacles().size();i++){
			if(toucheObstacleBas(obstacles.getObstacles().elementAt(i))){
				obstacles.getObstacles().elementAt(i).setToucher(true);
				return true;
			}
		}
		return false;
	}
	boolean toucheObstaclesGauche(){
		for(int i=0;i<obstacles.getObstacles().size();i++){
			if(toucheObstacleGauche(obstacles.getObstacles().elementAt(i))){
				obstacles.getObstacles().elementAt(i).setToucher(true);
				return true;
			}
		}
		return false;
	}
	boolean toucheObstaclesDroite(){
		for(int i=0;i<obstacles.getObstacles().size();i++){
			if(toucheObstacleDroite(obstacles.getObstacles().elementAt(i))){
				obstacles.getObstacles().elementAt(i).setToucher(true);
				return true;
			}
		}
		return false;
	}
	void initialiser(){
		int xcentre= palette.getXY().x+palette.getDim().width/2-RAYON/2;
		int ycentre=palette.getXY().y-RAYON;
		setXY(xcentre,ycentre);
		direction = (int)(Math.random()*19)%2;
		xinclinaison=5;
		yinclinaison=5;
	}
	public void mort(){
		vie--;
	}
	public void ajouterVie(){
		vie++;
	}
	public boolean touchePalette(){
		return x+longueur>=palette.getXY().x && x<=palette.getXY().x+palette.getDim().width && y+longueur>=palette.getXY().y && y+longueur<=palette.getXY().y+palette.getDim().height/2;
	}
	public void setPalette(Palette pal){
		palette = pal;
	}
	public Ensemble deplacer(){
		boolean game = false;
		int score=0;
		switch(direction){
			case HAUT_GAUCHE:{
				setXY(x-xinclinaison,y-yinclinaison);
				if(toucheMurHaut() || toucheObstaclesBas()){
					direction = BAS_GAUCHE;
					score = 10;
				}
				if(toucheMurGauche() || toucheObstaclesDroite()){
					direction = HAUT_DROITE;
					score = 10;
				}
			}
			break;
			case HAUT_DROITE:{
				setXY(x+xinclinaison,y-yinclinaison);
				if(toucheMurHaut() || toucheObstaclesBas()){
					direction = BAS_DROITE;
					score = 10;
				}
				if(toucheMurDroite() || toucheObstaclesGauche()){
					direction = HAUT_GAUCHE;
					score = 10;
				}
			}
			break;
			case BAS_GAUCHE:{
				setXY(x-xinclinaison,y+yinclinaison);
				if(toucheMurGauche() || toucheObstaclesDroite()){
					direction = BAS_DROITE;
					score =10;
				}
				if(toucheMurBas()){
					game = true;
					direction=(int)(Math.random()*19)%2;
					initialiser();
					xinclinaison=5;
					yinclinaison=5;
					score = -20;
				}
				if(touchePalette() || toucheObstaclesHaut()){
					direction = HAUT_GAUCHE;
					score = 10;
				}
			}
			break;
			case BAS_DROITE:{
				setXY(x+xinclinaison,y+yinclinaison);
				if(toucheMurDroite() || toucheObstaclesGauche()){
					direction = BAS_GAUCHE;
					score = 10;
				}
				if(toucheMurBas()){
					game = true;
					direction = (int)(Math.random()*19)%2;
					initialiser();
					score = -20;
				}
				if(touchePalette() || toucheObstaclesHaut()){
					direction = HAUT_DROITE;
					score = 10;
				}
			}
			break;
		}
		return new Ensemble(obstacles,game,score);
	}
}