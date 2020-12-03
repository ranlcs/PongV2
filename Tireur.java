

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

class Tir extends Objet{
	Tir(int x,int y,int lon,int epai,int vite,Color cou){
		super(x,y,lon,epai,vite,cou);
	}

	Tir(int x,int y,int lon,int epai){
		this(x,y,lon,epai,100,Color.green);
	}

	Tir(int x,int y){
		this(x,y,5,7);
	}
	public void deplacer(){
		setXY(x,y+5);
	}
	public boolean toucheMurBas(Bord bord){
		return (y+longueur>=bord.getXY().y+bord.getDim().height-Bord.EPAI_PETIT);
	}
	public boolean touchePalette(Palette palette){
		return x+longueur>=palette.getXY().x && x<=palette.getXY().x+palette.getDim().width && y+longueur>=palette.getXY().y && y+longueur<=palette.getXY().y+palette.getDim().height/2;
	}
	public boolean toucheObstacleHaut(Obstacle ob){
		boolean infx = x+longueur>=ob.getXY().x;
		boolean supx = x<=ob.getXY().x+ob.getDim().height;
		boolean infy = y+epaisseur>=ob.getXY().y;
		boolean supy =y+epaisseur>=ob.getXY().y+ob.getDim().width/3;
		if(infx && supx && infy && supy)
			ob.setToucher(true);
		return (infx && supx && infy && supy);
	}
	public boolean toucheObstaclesHaut(Obstacles obstacles){
		for(int i=0;i<obstacles.getObstacles().size();i++){
			if(toucheObstacleHaut(obstacles.getObstacles().elementAt(i))){
				obstacles.getObstacles().elementAt(i).setToucher(true);
				return true;
			}
		}
		return false;
	}
	public void dessiner(Graphics g){
		int[]xpoints={x,x+longueur/2,x+longueur};
		int[]ypoints={y+epaisseur,y+epaisseur+epaisseur,y+epaisseur};
		g.setColor(couleur);
		g.fillRect(x,y,longueur,epaisseur);
		g.fillPolygon(xpoints,ypoints,3);
	}
}


public class Tireur extends Objet{
	static final int LON_DEFAUT=50;
	static final int EPAI_DEFAUT = 13;
	static final int GAUCHE =0;
	static final int DROITE = 1;
	Vector<Tir> tirs;
	int nbGen = 20;
	Bord bord;
	Palette palette;
	int direction;
	int i=0;
	Obstacles obstacles;

	Tireur(int x,int y,int lon,int epai,int vite,Color cou){
		super(x,y,lon,epai,vite,cou);
		tirs = new Vector<Tir>();
		direction = (int)(Math.random()*19)%2;
	}

	Tireur(int x,int y,int lon,int epai){
		this(x,y,lon,epai,100,Color.green);
	}

	Tireur(int x,int y){
		this(x,y,LON_DEFAUT,EPAI_DEFAUT);
	}

	Tireur(Bord bord){
		this(0,0);
		this.bord = bord;
		int xcentre=bord.getXY().x+bord.getDim().width/2-LON_DEFAUT/2;
		int ycentre=bord.getXY().y+Bord.EPAI_PETIT;
		setXY(xcentre,ycentre);
	}
	boolean toucheMurGauche(){
		return x<=bord.getXY().x+Bord.EPAI_PETIT;
	}

	boolean toucheMurDroite(){
		return x+longueur>=bord.getXY().x+bord.getDim().width-Bord.EPAI_PETIT;
	}

	public Ensemble deplacer(){
		boolean game=false;
		int score = 0;
		switch(direction){
			case GAUCHE:{
				setXY(x-5,y);
				if(toucheMurGauche())
					direction = DROITE;
			}
			break;
			case DROITE:{
				setXY(x+5,y);
				if(toucheMurDroite())
					direction = GAUCHE;
			}
		}
		for(int i=0;i<tirs.size();i++){
			tirs.elementAt(i).deplacer();
			if(tirs.elementAt(i).toucheMurBas(bord) || tirs.elementAt(i).toucheObstaclesHaut(obstacles)){
				tirs.remove(i);
				if(tirs.size()!=0)
					i=tirs.indexOf(tirs.firstElement());;
			}
			if(tirs.size()!=0 && tirs.elementAt(i).touchePalette(palette)){
				game = true;
				tirs.removeAllElements();
				score = -30;
			}
		}
		i=(i+1)%nbGen;
		if(i==0)
			tirs.addElement(new Tir(x+longueur/3+longueur/6,y+2*epaisseur));
		return new Ensemble(obstacles,game,score);
	}
	public void setPalette(Palette pal){
		palette = pal;
	}
	public void setObstacles(Obstacles ob){
		obstacles = ob;
	}
	public void initialiser(){
		int xcentre=bord.getXY().x+bord.getDim().width/2-LON_DEFAUT/2;
		int ycentre=bord.getXY().y+Bord.EPAI_PETIT;
		setXY(xcentre,ycentre);
		tirs.removeAllElements();
	}
	public void dessiner(Graphics g){
		g.setColor(couleur);
		g.fillRect(x,y,longueur,epaisseur);
		g.fillRect(x+longueur/3,y+epaisseur,longueur/3,epaisseur);
		for(int i=0;i<tirs.size();i++)
			tirs.elementAt(i).dessiner(g);
	}
}