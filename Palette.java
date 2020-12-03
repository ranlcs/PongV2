

import java.awt.Color;
import java.awt.Graphics;

class Palette extends Objet{
	static final int VITE_DEFAUT =5;
	static final int LON_DEFAUT= 70;
	static final int EPAI_DEFAUT = 10;
	Bord bord; 
	boolean inverse = false;
	public  Palette(int x,int y,int longueur,int epaisseur,int vitesse,Color couleur,Bord bord){
		super(x,y,longueur,epaisseur,vitesse,couleur);
		this.bord = bord;
	}

	public Palette(int x,int y,int longueur,int epaisseur,Bord bord){
		this(x,y,longueur,epaisseur,VITE_DEFAUT,Color.green,bord);
	}

	public Palette(int x,int y,Bord b){
		this(x,y,LON_DEFAUT,EPAI_DEFAUT,b);
	} 

	public Palette(Bord b){
		this(400,400,b);
		int xcentre= bord.getXY().x+ (int)bord.getDim().width/2-LON_DEFAUT/2;
		int ycentre=bord.getXY().y+bord.getDim().height-bord.getDim().height/10+EPAI_DEFAUT;
		setXY(xcentre,ycentre);
	}
	public void dessiner(Graphics g){
		g.setColor(couleur);
		g.fillRect(x,y,longueur,epaisseur);
	}

	public boolean toucheMurGauche(){
		return x<=bord.getXY().x+Bord.EPAI_PETIT;
	} 

	public boolean toucheMurDroite(){
		return x+longueur>=bord.getDim().width+bord.getXY().x;
	}

	public void initialiser(){
		longueur = LON_DEFAUT;
		vitesse = VITE_DEFAUT;
		inverse = false;
		int xcentre= bord.getXY().x+ (int)bord.getDim().width/2-LON_DEFAUT/2;
		int ycentre=bord.getXY().y+bord.getDim().height-bord.getDim().height/10+EPAI_DEFAUT;
		setXY(xcentre,ycentre);
	}

	public boolean getInverse(){
		return inverse;
	}
	public Ensemble changerPalette(Obstacles b){
		int score = 0;
		for(int i=0;i<b.getObstacles().size();i++){
			Obstacle t = b.getObstacles().elementAt(i);
			if(t.touchePalette(this)){
				switch(t.getValeur()){
					case Obstacle.LONG:{
						if(longueur<140)
							longueur+=10;
						score = 10;
					}
					break;
					case Obstacle.PETIT:{
						if(longueur>=30)
							longueur-=10;
						score = 10;
					}
					break;
					case Obstacle.VITE:{
						if(!inverse)
							vitesse+=5;
						else 
							vitesse-=5;
						score = 10;
					}
					break;
					case Obstacle.LENT:{
					if(!inverse){
						if(vitesse>6) 
							vitesse-=5;
					}
					else if(vitesse<-6)
					 	vitesse+=5;
					score = 10;
					}
					break; 
					case Obstacle.NORMAL :{
						longueur = LON_DEFAUT;
						vitesse = VITE_DEFAUT;
						inverse = false;
						score = 10;
					}
					break;
					case Obstacle.INVERSE:{
						if(vitesse>0)
							vitesse = -vitesse;
						inverse = true;
						score = 20;
					}
				}
				b.remove(i); 
			}
		}
		return new Ensemble(b,true,score);
	}
}