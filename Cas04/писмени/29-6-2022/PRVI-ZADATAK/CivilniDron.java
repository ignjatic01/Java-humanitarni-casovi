

public class CivilniDron extends Dron {

	
	//jasno naznacen atribut u tekstu zadatka
	public long domet;

	public boolean udesno = true;
	
	public CivilniDron(String model, String oznaka, long d) {
		super(model, oznaka);
		domet = d;
	}
	
	
	@Override
	public String tip() {
		return "CIVILNIDRON";
	}

	@Override 
	public void run() {
		
		while(yKoordinata != 50) {
			//ako ide ulijevo i ako je stigao do pocetka, to je to
			if(!udesno && yKoordinata == 0) {
				break;
			}
				
			//ide udesno ili ulijevo
			if(udesno) {
				yKoordinata++;
			} else {
				yKoordinata--;
			}
			
			//3 put se krace krece
			try {
				sleep(Simulacija.VRIJEME_PROVEDENO_NA_POLJU / 3);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			//AKO SMO DOSTIGLI DOMET, pa treba da se vratimo nazad, idemo ulijevo
			if(yKoordinata == domet && udesno) {
				udesno = false;
				Simulacija.MAPA[xKoordinata][yKoordinata-1] = null;
				Simulacija.MAPA[xKoordinata][yKoordinata] = this;
			}
			
			//u suprotnom idemo ulijevo
			if(!udesno) {
				Simulacija.MAPA[xKoordinata][yKoordinata+1] = null;
				Simulacija.MAPA[xKoordinata][yKoordinata] = this;
			}
			System.out.println(this + "NASTAVIO KRETANJE NA POZICIJU " + "[" + xKoordinata + ","+yKoordinata + "]");
		}
		
		if(yKoordinata == 50) {
			System.out.println(this + " zavrsila kretanje!");
		}
		
	}
	
}