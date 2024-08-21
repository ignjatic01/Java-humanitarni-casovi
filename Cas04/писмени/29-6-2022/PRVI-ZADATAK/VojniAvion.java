


//Vojni avioni nose rakete
public class VojniAvion extends Avion implements RaketaInterface {

	public VojniAvion() {}

	public VojniAvion(String model, String oznaka) {
		super(model, oznaka);
	}
	
	public void iskljuciTransponder() {
		transponderIskljucen = true;
	}
	
	@Override
	public String tip() {
		return "VOJNIAVION";
	}
	
	//Redefinise se krovna metoda run klase Letjelica
	//Nije idealna implementacija, ali mnogo sigurnije nego da se VojniAvion rijec pojavila igdje u roditeljskoj klasi
	@Override 
	public void run() {
		
		while(yKoordinata != 50) {
			//KONTROLA LETA SRUSILA LETJELICU, pa break
			if(srusioLetjelicu) {
				break;
			}
			yKoordinata++;
			try {
				sleep(Simulacija.VRIJEME_PROVEDENO_NA_POLJU);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			if(yKoordinata != 50) {
				Simulacija.MAPA[xKoordinata][yKoordinata-1] = null;
				Simulacija.MAPA[xKoordinata][yKoordinata] = this;
			}
			//NAIZMJENICNO UKLJUCIVANJE I ISKLJUCIVANJE TRANSPONDERA
			if(transponderIskljucen) {
				transponderIskljucen = false;
			} else {
				transponderIskljucen = true;
			}
			System.out.println(this + "NASTAVIO KRETANJE NA POZICIJU " + "[" + xKoordinata + ","+yKoordinata + "]");
		}
		
		if(yKoordinata == 50) {
			System.out.println(this + " zavrsila kretanje!");
		}
		
	}

}