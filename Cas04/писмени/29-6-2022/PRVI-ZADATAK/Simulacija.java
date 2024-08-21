import java.lang.Thread;
import java.util.*;
import java.io.*;

public class Simulacija {

	public static final long VRIJEME_GENERISANJA = 500;
	public static final long VRIJEME_PROVEDENO_NA_POLJU = 3000;
	public static final String FAJL_ZA_EVIDENTIRANJE = "evidentiraj.txt";
	public static ArrayList<Letjelica> letjelice = new ArrayList<>();
	public static final int BR_RAZLICITH_VRSTA = 7;

	//Receno da je matrica dimenzija 50x50
	public static Object[][] MAPA = new Object[50][50];
	private static int generisanjeBrojac = 0;
	private boolean igratraje = true;
	private int BR_ZIVIH = 7;
	private boolean prviPokusaj = false;
	public static ArrayList<Letjelica> otkriveneLetjelice = new ArrayList<>();
	public static String fajlZaLetjelice = "." + File.separator + "letjelice.txt";
	public static String fajlZaRakete = "." + File.separator + "rakete.txt";
	
	public static void main(String[] args) {
		
		generisiLetjelice();
		KontrolaLeta kL = new KontrolaLeta();
		kL.start();
		for(Letjelica l : letjelice) {
			//svakih pola sekunde zapocni kretanje jedne letjelice
			l.start(); //redefinise se metoda run()
			try {
				Thread.sleep(VRIJEME_GENERISANJA);
			} catch(InterruptedException e) { //bolje catch Exception e
				e.printStackTrace();
			}
		}
		
		//Sacekati da sve simulacije zavrse kretanje
		for(Letjelica l : letjelice) {
			try {
				l.join();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		//Iskljuciti i KontroluLeta, da ne bude beskonacna while petlja
		KontrolaLeta.iskljuci = true;		
	}	
	
	//funkcija za generisanje letjelica, koristen switch-case i brojac da bi se generisala po jedna od svake moguce letjelice
	public static void generisiLetjelice() {
		Random random = new Random();
		
		for(int i = 0; i < 7; i++) {
			//Letjelice lete u slucajno odredjenom redu, cim je slucajno koristimo random
			int xKoordinata = random.nextInt(51);
			switch(generisanjeBrojac) {
				case 0:
					
					letjelice.add(new VojniAvion("VojniAvion", String.valueOf(i)));
					
					//return new VojniAvion("VojniAvion", String.valueOf(1));
					break;
				case 1:
					letjelice.add(new PutnickiAvion("PutnickiAvion", String.valueOf(i), random.nextInt(200)));
					//return new PutnickiAvion("PutnickiAvion", String.valueOf(2), random.nextInt(200));
					break;
				case 2:
					letjelice.add(new TeretniAvion("TeretniAvion", String.valueOf(3), random.nextDouble()*2000));
					break;
				case 3:
					letjelice.add(new VojniHelikopter("VojniHelikopter", String.valueOf(4)));
					break;
				case 4:
					letjelice.add(new PutnickiHelikopter("PutnickiHelikopter", String.valueOf(5), random.nextInt(200)));
					break;	
				case 5:
					letjelice.add(new CivilniDron("CivilniDron", String.valueOf(6), random.nextInt(30)));
					break;
				case 6:
					letjelice.add(new VojniDron("VojniDrong", String.valueOf(7), "NAOURAZNJE", "KAMERA"));
					break;
				default:
					break;
			}
			letjelice.get(i).xKoordinata = xKoordinata;
			generisanjeBrojac++;
			
	}
		
	}

}