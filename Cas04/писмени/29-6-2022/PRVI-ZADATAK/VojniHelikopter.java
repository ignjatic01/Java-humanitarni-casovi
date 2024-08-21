import java.util.Random;

//Vojni helikopteri imaju mitraljeze
public class VojniHelikopter extends Helikopter implements MitraljezInterface {

	public VojniHelikopter() {}
	
	//Vojni helikopteri mogu sletjeti, pa je to identifikovano kao atribut
	public int xZaSletanje;
	public int yZaSletanje;
		
	public VojniHelikopter(String model, String oznaka) {
		super(model, oznaka);
		//Pozicija za sletanje se odredjuje na slucajan nacin
		Random rand = new Random();
		xZaSletanje = rand.nextInt(51);
		yZaSletanje = rand.nextInt(51);
	}
	
	public void iskljuciTransponder() {
		transponderIskljucen = true;
	}

	
	@Override
	public String tip() {
		return "VOJNI_HELIKOPTER";
	}
	
	@Override 
	public void run() {
		
		while(yKoordinata != 50) {
			yKoordinata++;
			try {
				//DUPLO BRZE SE AVIONI KRECU
				sleep(Simulacija.VRIJEME_PROVEDENO_NA_POLJU/2);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			if(yKoordinata != 50) {
				Simulacija.MAPA[xKoordinata][yKoordinata-1] = null;
				Simulacija.MAPA[xKoordinata][yKoordinata] = this;
			}
			
			//Ako je sletio, izlazimo iz petlje!!!
			if(xKoordinata == xZaSletanje && yKoordinata == yZaSletanje) {
			System.out.println(this + " SLETIO!");
			break;
			}
				System.out.println(this + "NASTAVIO KRETANJE NA POZICIJU " + "[" + xKoordinata + ","+yKoordinata + "]");		
		}
		
		
		
		if(yKoordinata == 50) {
			System.out.println(this + " zavrsila kretanje!");
		}
		
	}
}