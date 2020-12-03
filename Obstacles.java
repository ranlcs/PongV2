



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Vector;

class Obstacle extends Objet{
	static final int COTE= 12;
	static final int VITESSE_DEFAUT = 100;
	static final int PETIT = 0;
	static final int LONG = 1;
	static final int STOP = 2;
	static final int VITE = 3; 
	static final int LENT = 4;
	static final int TUE = 5;
	static final int NORMAL =6;
	static final int INVERSE = 7;
	static final Color[] COULEURS = {/*A*/Color.RED,/*B*/Color.GREEN,/*C*/new Color(0,100,0),/*D*/new Color(235,165,165),/*E*/new Color(140,50,0),
										/*F*/new Color(255,255,0),/*G*/new Color(64,64,64),/*H*/Color.WHITE,/*I*/new Color(255,100,0),/*J*/Color.BLUE,
										/*K*/Color.BLACK,/*L*/new Color(170,100,0),/*M*/new Color(255,100,0)};

	boolean toucher = false;
	int valeur = (int)(Math.random()*29)%8;
	int coul = 1+(int)((Math.random()*37)%(COULEURS.length-1));
	Obstacle(int x,int y,int cote,int vite,Color col){
		super(x,y,cote,cote,vite,col);
		// couleur = couleurs[coul];
	}

	Obstacle(int x,int y,int cote,Color c){
		this(x,y,cote,VITESSE_DEFAUT,c);
	}
	Obstacle(int x,int y,Color c){
		this(x,y,COTE,c);
	}
	Obstacle(int x,int y){
		this(x,y,Color.RED);
		couleur = COULEURS[coul];
	}

	public int getValeur(){
		return valeur;
	}

	public void setValeur(int vla){
		valeur = vla;
	}
	public boolean getToucher(){
		return toucher;
	}
	public void setToucher(boolean t){
		toucher = t;
	} 

	public boolean estDans(int nb){
		return (124<=nb && nb<=132);
	}

	public boolean estDans(int nb1,int nb2,int nb3){
		return estDans(nb1) && estDans(nb2) && estDans(nb3); 
	}

	public Color inverseColor(){
		int r = Math.abs(255-couleur.getRed());
		int b = Math.abs(255-couleur.getBlue());
		int g = Math.abs(255-couleur.getGreen());
		if(estDans(r,g,b)){
			r=0;
			g=0;
			b=0;
		}
		return new Color(r,g,b);
	}

	public void dessiner(Graphics g){
		String r="";
		g.setColor(couleur);
		g.fillRect(x,y,longueur,epaisseur);
		if(toucher){
			g.setFont(new Font("Times New Roman",Font.BOLD,12));
			switch(valeur){
				case PETIT:  r+="P";
				break;
				case LONG: r+="L";
				break;
				case STOP: r+="S";
				break;
				case VITE: r+="V";
				break;
				case LENT: r+="E";
				break;
				case TUE: r+="T";
				break;
				case NORMAL: r+="N";
				break;
				case INVERSE: r+="I";
			}
			g.setColor(inverseColor());
			g.drawString(r,x+2,y+longueur-2);
		}
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
	public String toString(){
		return "x="+Integer.toString(x)+"  y="+Integer.toString(y);
	}
}












public class Obstacles{
	protected Vector<Obstacle> tab;
	protected Bord bord;
	protected Palette palette;
	protected Balle balle;
	Obstacles(){
		tab=new Vector<Obstacle>();
	}
	Obstacles(Bord bord){
		tab = new Vector<Obstacle>();
		this.bord = bord; 
	}
	Obstacles(Bord bord,String[] t,Color[] couleur){
		this(bord);
		int xdebut = bord.getXY().x+bord.getDim().width/2-t[0].length()*Obstacle.COTE/2;
		int ydebut = bord.getXY().y+bord.getDim().height/6;
		for(int i=0;i<t.length;i++)
			for(int j=0;j<t[i].length();j++)
				if(t[i].charAt(j)>='A' && t[i].charAt(j)<='Z'){
					tab.addElement(new Obstacle(xdebut+j*Obstacle.COTE,ydebut+i*Obstacle.COTE,couleur[t[i].charAt(j)-65]));
				}
				else if(t[i].charAt(j)=='0')
					tab.addElement(new Obstacle(xdebut+j*Obstacle.COTE,ydebut+i*Obstacle.COTE));
	}
	Obstacles(Bord b,String[] t){
		this(b,t,Obstacle.COULEURS);
	}
	public void setPalette(Palette pal){
		palette = pal;
	}
	public int getObstacleRestant(){
		return tab.size();
	}
	public Ensemble deplacer(){
		boolean game = false;
		int score = 10;
		for(int i=0;i<tab.size();i++){
			if(tab.elementAt(i).getToucher()){
				tab.elementAt(i).deplacer();
				if(tab.elementAt(i).getValeur()==Obstacle.TUE && tab.elementAt(i).touchePalette(palette)){
					game = true;
					score=-30;
				}
				if(tab.elementAt(i).toucheMurBas(bord))
					tab.remove(i);
			}
		}
		return new Ensemble(new ObstaclesCoeur(),game,score);
	}

	public Vector<Obstacle> getObstacles(){
		return tab;
	}
	public void dessiner(Graphics g){
		for(int i=0;i<tab.size();i++)
			tab.elementAt(i).dessiner(g);
	}

	public void initialiser(){
		for(int i=0;i<tab.size();i++){
			if(tab.elementAt(i).getToucher()){
				tab.remove(i);
				if(tab.size()!=0)
					i=tab.indexOf(tab.firstElement());
			}
		}
	}

	public void remove(int i){
		tab.remove(i);
	}
	public void generer(Bord b){
		System.out.println(tab.size());
	}
}



  /************************************************************/
 /*     OBSTACLE A PARTIR IMAGE AVEC TAILLE ???????????      */
/************************************************************/