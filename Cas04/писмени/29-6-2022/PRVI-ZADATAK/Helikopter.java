

abstract public class Helikopter extends Letjelica {

	public Helikopter() {}

	public Helikopter(String model, String oznaka) {
		super(model, oznaka);
	}
	
	/*@Override
	public String tip() {
		return "HELIKOPTER";
	}*/
	
	/*@Override 
	public void run() {
		
		while(yKoordinata != 50) {
			yKoordinata++;
			try {
				sleep(Simulacija.VRIJEME_PROVEDENO_NA_POLJU/2);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			if(yKoordinata != 50) {
				Simulacija.MAPA[xKoordinata][yKoordinata-1] = null;
				Simulacija.MAPA[xKoordinata][yKoordinata] = this;
			}
			
			System.out.println(this + "NASTAVIO KRETANJE NA POZICIJU " + "[" + xKoordinata + ","+yKoordinata + "]");
			
		}
		
		if(yKoordinata == 50) {
			System.out.println(this + " zavrsila kretanje!");
		}
		
	}*/
}