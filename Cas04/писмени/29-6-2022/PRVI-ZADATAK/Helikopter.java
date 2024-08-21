

abstract public class Helikopter extends Letjelica {

	public Helikopter() {}

	public Helikopter(String model, String oznaka) {
		super(model, oznaka);
	}
	
	@Override
	public long vrijemeKretanjaZaVozilo() {
		return Simulacija.VRIJEME_PROVEDENO_NA_POLJU / 2;
	}
	
}