

class ObstaclesEssai extends Obstacles{
	static String[] coeur ={
		
		"           ",
		"   A     "	
	};
	ObstaclesEssai(Bord bord){
		super(bord,coeur);
	}
	ObstaclesEssai(){
		super();
	}
	public void generer(Bord b){
		ObstaclesEssai t = new ObstaclesEssai(b);
		tab = t.tab;
		bord = t.bord;
	}
}