


//Vojni avioni nose rakete
public class VojniAvion extends Avion implements RaketaInterface {

	public VojniAvion() {}

	public VojniAvion(String model, String oznaka) {
		super(model, oznaka);
	}
	
	@Override
	public String tip() {
		return "VOJNIAVION";
	}
}