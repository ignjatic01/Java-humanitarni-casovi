import java.util.Random;

//Vojni helikopteri imaju mitraljeze
public class VojniHelikopter extends Helikopter implements MitraljezInterface {

	public VojniHelikopter() {}
		
	public VojniHelikopter(String model, String oznaka) {
		super(model, oznaka);
	}
	
	@Override
	public String tip() {
		return "VOJNI_HELIKOPTER";
	}
}