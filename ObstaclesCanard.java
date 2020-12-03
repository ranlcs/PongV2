

class ObstaclesCanard extends Obstacles{
	static String[] canard={
		"       GGGG                        ",
		"      GGGGGG                       ",
		"     GGGGGGGG                      ",
		"     GGKGGGGG                      ",
		"    FGGGGGGGG                      ",
		"  FFFFGGGGGGG                      ",
		"FFFFFGGGGGGG                       ",
		"       GGGG                        ",
		"       GGGG                        ",
		"        GGG                        ",
		"        GGG                        ",
		"       GGGGG                       ",
		"      GGGGGG    CCCCC              ",
		"      HHHGGGG ICCCCCCCCC           ",
		"     HHHHHHHIICCCCCCCCCCCC         ",
		"    HHHHHHHIIICCCCCCCCCCCCCC       ",
		"   HHHHHHHIIICCCAAAACCCCCAAAAAAA   ",
		"   HHHHHHHIIICAAAAAAAACCAAAAAAA    ",
		"   HHHHHHIIIIAAAAAAAAAAAAAAAAA     ",
		"    HHHHIIIIIIAAAAAAAAAAAAAAA      ",
		"JJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ"
	};

	ObstaclesCanard(Bord b){
		super(b,canard);
	}
	ObstaclesCanard(){
		super();
	}
	public void generer(Bord b){
		ObstaclesCanard t = new ObstaclesCanard(b);
		bord = t.bord;
		this.tab = t.tab;
	}
}