


public class PutnickiHelikopter extends Helikopter {

	public int brojMjesta;
	
	public PutnickiHelikopter() {}

	public PutnickiHelikopter(String model, String oznaka, int brMjesta) {
		super(model, oznaka);
		brojMjesta = brMjesta;
		
	}
	
	@Override
	public String tip() {
		return "PUTNICKIHELIKOPTER";
	}
}