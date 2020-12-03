import java.awt.Color;

public class ObstaclesChampignon extends Obstacles{
	static final String[] champignon={
				"                     ",
				"         KKK         ",
				" KLLKKKKKKKLLKKKKKKK ",
				" KKKKKKKKKKKKKKKLLKK ",
				"   LLDLDLLLDLLDLDL   ",
				"         LLL         ",
				"         LLL         ",
				"        LLLLL        ",
				"         LLL         ",
				"         LLL         ",
				"         LLL         ",
				"         LLL     FF  ",
				"   FFF   LLL    F    ",
				"  F   F LLL    F     ",
				"       FLLLL   F     ",
				"        FLLLL F   F  ",
				"  FF   LFLLLL F  F   ",
				"    FF LLFLLLF  F    ",
				"     EFLEFELEFEF     ",
				" EEEEEEEE EEEE FEEE  ",
				"                     "
			};
	public static final Color[] paletteChampignon = {
			Color.black, Color.blue, Color.cyan, Color.darkGray,
			Color.gray, Color.green, Color.lightGray, Color.magenta,
			Color.orange, Color.pink, Color.red, Color.white,
			Color.yellow
	};

	ObstaclesChampignon(Bord b){
		super(b,champignon,paletteChampignon);
	}
	ObstaclesChampignon(){
		super();
	}
	public void generer(Bord b){
		ObstaclesChampignon t = new ObstaclesChampignon(b);
		bord = t.bord;
		this.tab = t.tab;
	}
}