
//KLASE SU ABSTRACT JER IH NE INSTANCIRAM, A NEMA POTREBE NI DA IMPLEMENTIRAM NEKE STAVKE
abstract public class Avion extends Letjelica {

	public Avion() {}

	public Avion(String model, String oznaka) {
		super(model, oznaka);
	}
	
	@Override
	public long vrijemeKretanjaZaVozilo() {
		return Simulacija.VRIJEME_PROVEDENO_NA_POLJU;
	}
	
}