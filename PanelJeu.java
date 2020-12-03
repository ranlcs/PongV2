

import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

public class PanelJeu extends JPanel{
	PanelBord bord;
	PanelDebut debut;
	public PanelJeu() throws Exception{
		super();
		setSize(2000,1000);
		setLayout(new BorderLayout());
		bord= new PanelBord(this.getSize().height/2,this.getSize().width/12);
		debut = new PanelDebut();
		add(debut,"Center");
		addKeyListener(new Touche());
		requestFocus();
		setVisible(true);
	}
	
	class Touche extends KeyAdapter{
		public void keyPressed(KeyEvent k){
			switch(k.getKeyCode()){
				case KeyEvent.VK_ENTER:{
					repaint();
					remove(debut);
					setVisible(false);
					add(bord,"Center");
					bord.requestFocus();
					setVisible(true);
				}
				break;
				case KeyEvent.VK_ESCAPE:{
					System.exit(0);
				}
			}
		}
	}
}
