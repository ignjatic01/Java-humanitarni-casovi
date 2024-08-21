import java.lang.Thread;
import java.util.Random;
import java.io.*;

public class KontrolaLeta extends Thread {

	public static boolean iskljuci = false;
	@Override
	public void run() {
		
		while(iskljuci == false) {
			boolean srusen  = false;
			Random random = new Random();
			//naizmjenicna x i y koordinata koja ce biti skenirana
			int xKoord = random.nextInt(50);
			int yKoord = random.nextInt(50);
			
			//omoguceno rijetko skeniranje da bi simulacija bila fluidna
			boolean periodicnoSkeniraj = random.nextInt(101) < 10;
			
			if(periodicnoSkeniraj) {
				//sinhronizujemo mapu tako da se nijedno vozilo ne moze pomjeriti ako KontrolaLeta skenira mapu, imamo konzistentno stanje mape
				synchronized(Simulacija.MAPA) {
				//ako je pronadjeno vozilo na odredjenoj poziciji, pri cemu ovdje instanceof ne predstavlja problem
				if(Simulacija.MAPA[xKoord][yKoord] instanceof Letjelica) {
				
				//Object
				Letjelica l = (Letjelica)Simulacija.MAPA[xKoord][yKoord];
				//ako se letjelica ne nalazi u nizu otkrivenih letjelica
				if(!Simulacija.otkriveneLetjelice.contains(l)) {
					//dodaj letjelicu u otkrivene letjelice
					Simulacija.otkriveneLetjelice.add(l);
					System.out.println("OTKRIVENA LETJELICA: " + l);
					try {
						//Upisi string reprezentaciju same letjelice u fajl, nema potrebe za ObjectOutputStream i slicno
						//Fajl se nalazi u klasi Simulacija, statickom atributu pristupamo
						PrintWriter pw = new PrintWriter(new FileWriter(new File(Simulacija.fajlZaLetjelice)));
						pw.println(l);
						pw.close();
					} catch(Exception e) {
						e.printStackTrace();
					}
					
					
				}
				//Kontrola leta moze srusiti letjelicu samo ako je transponder iskljucen
				if(l.transponderIskljucen) {
					int pokusajSrusiti = random.nextInt(101);
					System.out.println("RAKETA SE ISPALJUJE!");
					
					//>50 jer je vjerovatnoca 50% da se moze srusiti letjelica, isto je i sa <50
					if(pokusajSrusiti > 50) {
						srusen = true;
						System.out.println("OBJEKAT " + l + " SRUSEN");
						Letjelica letj = (Letjelica)Simulacija.MAPA[xKoord][yKoord];
						letj.srusioLetjelicu = true;
						Simulacija.MAPA[xKoord][yKoord] = null;
					}
					
					try {
						//Evidentiranje o ispaljivanju raketa u txt.fajl
						//Fajl se nalazi u klasi Simulacija, statickom atributu pristupamo
						PrintWriter pw = new PrintWriter(new FileWriter(new File(Simulacija.fajlZaRakete)));
						String tmpStr = srusen?"JE":"NIJE";
						pw.println("RAKETA ISPALJENA NA OBJEKAT " + l + " KOJI " +tmpStr + " SRUSEN!" );
						pw.close();
					} catch(IOException e) {
						e.printStackTrace();
					}
				}				
			}
			}
			}
			//CISTO DA SE NE VRTI STALNO, nego svake sekunde
			//Nije naznaceno u tekstu zadatka, ali neke stvari se mogu proizvoljno uraditi
			try {
				sleep(1000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
		
	}

}