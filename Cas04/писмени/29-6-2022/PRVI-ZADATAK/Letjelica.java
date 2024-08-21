import java.lang.Thread;
import java.util.Random;

//Sve letjelice imaju transponder, a letjelica je prakticno krovna klasa koju nasljedjuju ostale, pa se samo tu i implementira
//nema ponavljanja, sve sto se moze staviti u klasu koja je na visem nivou hijerarhije, treba da se uradi
abstract public class Letjelica extends Thread  implements Transponder {
	//atributi definisani u tekstu zadatka
	public String model;
	public String oznaka;
	
	public int xKoordinata;
	public int yKoordinata;
	public static int VRIJEME_KRETANJA = 3000;
	
	//Jer je naznaceno da transponder moze biti iskljucen
	public boolean transponderIskljucen = false;
	public boolean srusioLetjelicu = false;
	
	public Letjelica() {
		
	}
	
	public Letjelica(String m, String o) {
		model = m;
		oznaka = o;
	}	

	//Provjera da li je transponder iskljucen, ako nije, vrati xKoordinatu	
	@Override
	public int posaljiX() {
		if(!transponderIskljucen) {
			return xKoordinata;
		} else {
			return -1;
		}
		
	}
	
	//KORISTENO SAMO ZA ISPIS -> komentar ostavljen dok sam radio rok, nekad nije lose ni to, ali sto manje!
	//izbjegnut problem sa instanceof
	abstract String tip();
		
	//Provjera da li je transponder iskljucen, ako nije, vrati yKoordinatu	
	@Override
	public int posaljiY() {
		if(!transponderIskljucen) {
			return yKoordinata;
		} else {
			return -1;
		}
	}

	
	@Override
	public String toString() {
		String t = tip();
		return "Model: " + model + " Oznaka: " + oznaka + " xK: " + xKoordinata + " yKoordinata" + yKoordinata + "Tip: " + t;
	}
	
	
	@Override 
	public void run() {
		//Letjelice pokusavaju da se krecu sve dok ne doju do kraja
		while(yKoordinata != 50) {
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
				//Azuriranje stanja na mapi, staro polje postaviti na null, a novo na this
				Simulacija.MAPA[xKoordinata][yKoordinata-1] = null;
				Simulacija.MAPA[xKoordinata][yKoordinata] = this;
			}
						
						
				
			
			System.out.println(this + "NASTAVIO KRETANJE NA POZICIJU " + "[" + xKoordinata + ","+yKoordinata + "]");
		}
		
		//Ako je letjelica dosla do kraja mape
		if(yKoordinata == 50) {
			System.out.println(this + " zavrsila kretanje!");
		}
		
	}

}