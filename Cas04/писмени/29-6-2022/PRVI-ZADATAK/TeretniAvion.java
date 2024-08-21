


public class TeretniAvion extends Avion {
	//za teretne se cuva maksimalna tezina tereta koju mogu prevesti
	double maksimalnaTezina;
	
	public TeretniAvion() {}
	
	public TeretniAvion(String model, String oznaka, double tezina) {
		super(model, oznaka);
		tezina = maksimalnaTezina;
	}	
	
	@Override
	public String tip() {
		return "TERETNIAVION";
	}	
}