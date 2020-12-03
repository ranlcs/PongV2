

class ObstaclesCoeur extends Obstacles{
	static String[] coeur ={
		"           ",
		" AAA   AAA ",
		"AAAAA AAAAA",
		"AAAAAAAAAAA",
		"AAAAAAAAAAA",
		" AAAAAAAAA ",
		"  AAAAAAA  ",
		"   AAAAA   ",
		"    AAA    ",
		"     A     ",
		"           ",
	};
	ObstaclesCoeur(Bord bord){
		super(bord,coeur);
	}
	ObstaclesCoeur(){
		super();
	}
	public void generer(Bord b){
		ObstaclesCoeur t = new ObstaclesCoeur(b);
		tab = t.tab;
		bord = t.bord;
	}
}