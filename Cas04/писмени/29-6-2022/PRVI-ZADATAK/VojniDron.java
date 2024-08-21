


public class VojniDron extends Dron {
	//jasno naznaceni atributi u tekstu zadatka
	String naourazanje;
	String kamera;
	
	public VojniDron(){}
	
	public VojniDron(String model, String oznaka, String n, String k) {
		super(model, oznaka);
		naourazanje = n;
		kamera = k;
	}
	
	@Override
	public String tip() {
		return "VOJNIDRON";
	}

}