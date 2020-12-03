

class ObstaclesPomme extends Obstacles{
	static String[] pomme = {
		"              ",
		"          EEEE",
		"        EE    ",
		"       E      ",
		"      E       ",
		"  AAA E AAA  " ,
		" AAAAAAAAAAA " ,
		"AAAAAAAAAAAAA" ,
		"AAAAAAAAAAAAA" ,
		"AAAAAAAAAAAAA" ,
		"AAAAAAAAAAAAA" ,
		"AAAAAAAAAAAAA" ,
		" AAAAAAAAAAA " ,
		"  AAAAAAAAAA  " ,
	};

	ObstaclesPomme(Bord b){
		super(b,pomme);
	}
	ObstaclesPomme(){
		super();
	}
	public void generer(Bord b){
		ObstaclesPomme t = new ObstaclesPomme(b);
		tab = t.tab;
		bord = t.bord;
	}
}