
public class PutnickiAvion extends Avion {
	//Za putnicke cuva se broj mjesta
	int brMjesta;

	public PutnickiAvion() {}

	public PutnickiAvion(String model, String oznaka, int mjesta) {
		super(model, oznaka);
		brMjesta = mjesta;
	}	
		
	@Override
	public String tip() {
		return "PUTNICKIAVION";
	}	
}