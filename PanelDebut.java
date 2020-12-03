

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.PixelGrabber;
import javax.swing.Timer;
import javax.imageio.ImageIO;

public class PanelDebut extends JPanel{
	int nb=0;
	int etat = 1;
	int[] tableauImage;
	int lon;
	int lar;
	public PanelDebut(){
		super();
		setVisible(true);
		String temp = getClass().getResource("sary/pong.png").toString().substring(6);
		try{
			Image im = ImageIO.read(new File(temp));
			if(im!=null){
				lon = im.getWidth(null);
				lar=im.getHeight(null);
				tableauImage = new int[lon*lar];
				(new PixelGrabber(im,0,0,lon,lar,tableauImage,0,lon)).grabPixels();
			}
			(new Timer(45,new Afficher())).start();
		}catch(Exception e){
			System.out.println("erreur sur emplacement: "+temp);
		}
		
	}

	class Afficher implements ActionListener{
		public void actionPerformed(ActionEvent a){
			nb = (nb+1)%20;
			if(nb==0)
				etat = (etat+1)%2;
			repaint();
		}
	}
	public void dessinerRect(Graphics g,int couleur,int x,int y){
		int r=(couleur>>16) & 0xff;
		int gr=(couleur>>8) & 0xff;
		int b=couleur & 0xff;
		g.setColor(new Color(r,gr,b));
		g.fillRect(x,y,1,1);
	}
	public void paint(Graphics g){
		for(int i=0;i<lar;i++)
			for(int j=0;j<lon;j++){
				if(!(new Color(255,255,255)).equals(new Color(tableauImage[i*lon+j])))
				dessinerRect(g,tableauImage[i*lon+j],140+j,30+i);
			}
		if(etat == 1){	
			g.setColor(getBackground());
			g.fillRect(670,670,200,45);
		}
		else if(etat==0){
			g.setFont(new Font("Courier",Font.ITALIC+Font.BOLD,17));
			g.setColor(Color.black);
			g.drawString("b y    l c s",670,700);
		}
	}
}