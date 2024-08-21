


abstract public class Dron extends Letjelica {

	public Dron() {}

	public Dron(String model, String oznaka) {
		super(model, oznaka);
	}
	
	@Override
	public long vrijemeKretanjaZaVozilo() {
		return Simulacija.VRIJEME_PROVEDENO_NA_POLJU / 3;
	}
	
}