

class ObstaclesRose extends Obstacles{
	static String[] rose={
"                    BB ",
"             AAAA A BBB ",
"          AAAAAA AA BBBB ",
"       AAAAAAAAAAA BBCBB ",
"    DDAAAAAAAAAAAABCCCBB ",
"CCC DAAAAAADAAAAAACDCCBB ",
"BCCCAAAAAAADDADAAAADDCBB ",
"BBCCADAAAADDADDAAAADDBB ",
"BBCCAADDDDDDADDAAAADAA ",
"BBBCCAADDDDADDDAAAADAAAA ",
" BBBADAAAAADDDDAAAADAAAA ",
"   AAADAAADDDDAAAADAAAAA ",
"  DDAAADDDDDDAAAADAAAAACCC ",
"  DDDAAAADDDAAAAAAAAAACCCCC ",
"  DDDDAAAAAAAAAAAADAACCCCBBC ",
"  CDDDDDDDAAAAADAADBBBCCCCBB ",
" CCCCCCAAAAAAADAADDCCBBBBBCB ",
"CBBCCCDDAAAADDAADDCCCCC BBB ",
"BBCCCCDDDDDDDAAADDCCCCCC ",
"BBBBCCCDDDDDCAADDCCCCCCC ",
" BBBBBCCDDCCCCCBECBBCCCC ",
"       CBCCCCCCBEBBCBBBC ",
"        BBCCCBBBE   BBBC ",
"        BBCCBBBEE ",
"         BCCBB EE ",
"          CB   EE ",
"          C   EE ",
"              EE ",
"              EEFF ",
"             EEFF ",
"             EE ",
"             E ",
"            EE ",
"           EEE ",
"           EE ",
"            E"
	};

	ObstaclesRose(Bord b){
		super(b,rose);
	}
	ObstaclesRose(){
		super();
	}
	public void generer(Bord b){
		ObstaclesRose t = new ObstaclesRose(b);
		tab = t.tab;
		bord = t.bord;
	}
}