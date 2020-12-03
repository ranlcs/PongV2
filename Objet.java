

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Dimension;

public abstract class Objet{
	int xprec;
	int yprec;
	protected int x;
	protected int y;
	protected int longueur;
	protected int epaisseur;
	protected int vitesse;
	protected Color couleur;

	protected Objet(int x,int y,int lon,int epai,int vitesse,Color couleur){
		this.x=x;
		this.y=y;
		xprec = x;
		yprec = y;
		longueur = lon;
		epaisseur = epai;
		this.vitesse = vitesse;
		this.couleur = couleur;
	}

	public void setXY(int x,int y){
		xprec = this.x;
		yprec = this.y;
		this.x=x;
		this.y=y;
	}

	public Point getXY(){
		return new Point(x,y);
	}

	public void setDim(int lon,int epai){
		longueur = lon;
		epaisseur=epai;
	}

	public Dimension getDim(){
		return new Dimension(longueur,epaisseur);
	}

	public void setVitesse(int vit){
		vitesse = vit;
	}

	public int getVitesse(){
		return vitesse;
	}

	public void setCouleur(Color v){
		couleur= v;
	}

	public Color getCouleur(){
		return couleur;
	}

	public void effacer(Graphics g,Color r){
		g.setColor(r);
		g.fillRect(xprec,yprec,longueur,epaisseur);
	}

	public abstract void dessiner(Graphics g);
}