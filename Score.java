

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.io.File;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

class Score{
	int score;
	int nbVie;
	Bord bord;
	int bestScore;
	String temp;
	Score(Bord b) {
		bord = b;
		score =0;
		nbVie = 2;
		bestScore = 0;
		temp = getClass().getResource("Objet.class").toString();
		temp = temp.substring(6,temp.length()-11)+"data.lcs";
		if(new File(temp).exists()){
			try{
				DataInputStream in = new DataInputStream(new FileInputStream(temp));
				bestScore = (int)in.readInt();
			}catch(IOException f){
					System.out.println(f);
			}
		}
	}
	
	public int getScore(){
		return score;
	}
	public void setScore(int s){
		score = s;
	}
	public void setVie(int v){
		nbVie = v;
	}
	public void initialiser(){
		score = 0;
		nbVie = 2;
	}
	public void initialiserBest(){
		if(bestScore>0){
			try{
				DataOutputStream out = new DataOutputStream(new FileOutputStream(temp));
				out.writeInt(0);
				out.close();
				bestScore = 0;
			}catch(IOException f){
				System.out.println(f);
			}
		}
	}
	public void effacer(Graphics g,Color f){
		g.setColor(f);
		g.fillRect(bord.getXY().x/4,bord.getXY().y+bord.getDim().width/5,120,250);
	}
	public void dessiner(Graphics g){
		int xd=bord.getXY().x/4;
		int yd = bord.getXY().y+bord.getDim().width/5;
		g.setFont(new Font("Times New Roman",Font.BOLD,30));
		g.setColor(Color.red);
		g.drawString("Meilleur Score",xd,yd);
		g.setFont(new Font("Times New Roman",Font.BOLD+Font.ITALIC,30));
		g.setColor(Color.black);
		g.drawString(Integer.toString(bestScore),xd+20,yd+30);
		g.setColor(Color.red);
		g.setFont(new Font("Times New Roman",Font.BOLD,30));
		g.drawString("Vie restante",xd,yd+90);
		for(int i=0;i<=nbVie;i++)
			(new Balle(xd+20+(Balle.RAYON+15)*i,yd+105)).dessiner(g);
		g.setFont(new Font("Times New Roman",Font.BOLD,30));
		g.setColor(Color.red);
		g.drawString("Score",xd,yd+165);
		g.setColor(Color.black);
		g.setFont(new Font("Times New Roman",Font.BOLD+Font.ITALIC,30));
		g.drawString(Integer.toString(score),xd+20,yd+195);

	}
	public void enregistrerScore(){
		if(bestScore<score)
			bestScore = score;
		try{
			DataOutputStream out = new DataOutputStream(new FileOutputStream(temp));
			out.writeInt(bestScore);
			out.close();
		}catch(IOException f){
			System.out.println(f);
		}
	}
}