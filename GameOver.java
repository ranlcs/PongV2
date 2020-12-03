

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

class GameOver{
	Bord bord;
	GameOver(Bord b){
		bord = b;
	}
	public void dessiner(Graphics g){
		g.setColor(Color.black);
		g.setFont(new Font("Times New Roman",Font.BOLD,45));
		g.drawString("GAME",bord.getXY().x+bord.getDim().width/2-60,bord.getXY().y+bord.getDim().height/2-80);
		g.drawString("OVER",bord.getXY().x+bord.getDim().width/2-60,bord.getXY().y+bord.getDim().height/2-30);
	}
}