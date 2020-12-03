

class ObstaclesPONG extends Obstacles{
	static String[] pong={
		"                                    ",
		"000000  000000  00      00  00000000",
		"000000  000000  00      00  00000000",
		"00  00  00  00  00      00  00      ",
		"00  00  00  00  000     00  00      ",
		"00  00  00  00  0000    00  00      ",
		"00  00  00  00  00  00  00  00      ",
		"00  00  00  00  00  00  00  00  0000",
		"00  00  00  00  00    0000  00  0000",
		"000000  00  00  00     000  00    00",
		"000000  00  00  00      00  00    00",
		"00      000000  00      00  00000000",
		"00      000000  00      00  00000000",
	};
	ObstaclesPONG(Bord bord){
		super(bord,pong);
	}
	ObstaclesPONG(){
		super();
	}
	public void generer(Bord b){
		ObstaclesPONG t = new ObstaclesPONG(b);
		tab = t.tab;
		bord = t.bord;
	}
}