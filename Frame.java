

import javax.swing.JFrame;

import java.awt.Toolkit;
import java.awt.Image;
class Frame extends JFrame{
	static PanelJeu jeu;
	Frame() throws Exception{
		jeu = new PanelJeu();
		add(jeu);	
		setSize(2000,1000);
		setTitle("Pong");
		setLocation(0,0);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Toolkit tk = getToolkit();
		Image im = tk.getImage(getClass().getResource("sary/lune.png"));
		setIconImage(im);
		setVisible(true);
	}

	
	public static void main(String[]arg) throws Exception{
		new Frame();
		jeu.requestFocus();
	}
}


   /**********************************************/
  /**               inclinaison ?????          **/
 /**   autre schema :PONG,Rose,pomme,coeur    **/
/**********************************************/

/********************************************/
/**      revoir system de score(Tireur)   **/
/**       ajouter nouveau obstacles      **/
/*****************************************/