//import java.util.*; korektnije
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
//import java.io.*; korektnije
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

//Nit pa nasledjujemo klasu Thread 
public class Prva extends Thread
{
	//Putanja do fajla
	public String path;
	//Grupisanje po pocetnom slovu, znaci mapa sa karakterom kao kljucem i listom rijeci za dati karakter
	//Pogledati API HashMape, ne uciti metode napamet
	public static HashMap<Character, List<String>> mapa = new HashMap<>();
	
	public Prva(String path)
	{
		this.path = path;
	}
	
	//Redefinise se metoda run, a pokrece se pozivom metode start()
	@Override 
	public void run()
	{
		//Klasican nacin iscitavanja txt fajla 
		//BufferedReader okruzuje FileReader koji okruzuje File 
		//Nakon toga jako korisna metoda se poziva, readLine()
		//Pogledati API za BufferedReader, PrintWriter...
		//try with resources blok, ne moramo zatvoriti BufferedReader u tom slucaju
		try(BufferedReader br = new BufferedReader(new FileReader(new File(path))))
		{
			String line = "";
			int brojac = 0;
			//Iscitava se linija po linija
			while((line = br.readLine()) != null)
			{
				//Koristiti API za rad stringovima, korisna metoda charAt 
				Character slovo = line.charAt(0);
				//Dodavanje rijeci za pocetno slovo u mapu
				if(mapa.containsKey(slovo))
				{
					mapa.get(slovo).add(line);
				}
				else
				{
					ArrayList<String> temp = new ArrayList<>();
					temp.add(line);
					mapa.put(slovo, temp);
				}
				//Trazi se broj procesiranih rijeci
				brojac++;
			}
			System.out.println("Prva nit je procesirala " + brojac + " rijeci.");
			//Pokrece se izvrsavanja druge niti
			(new Druga()).start();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}