import java.util.ArrayList;
import java.util.Random;

public abstract class Radnik extends Thread
{
	public static final int BROJ_ZADATAKA_ZA_OBRACUN = 10;
	public static boolean obracunato = false;
	public static final String putanja = "./izvjestaj.txt";
	
	String ime;
	String prezime;
	int godineRada;
	int cijenaRada;
	ArrayList<String> zadaci;
	String tekstZadatka;
	int sekundeRada;
	
	public Radnik(String ime, String prezime, int godineRada, int cijenaRada, String tekstZadatka, int sekundeRada)
	{
		this.ime = ime;
		this.prezime = prezime;
		this.godineRada = godineRada;
		this.cijenaRada = cijenaRada;
		zadaci = new ArrayList<>();
		this.tekstZadatka = tekstZadatka;
		this.sekundeRada = sekundeRada;
	}
	
	public void run()
	{
		System.out.println(ime + " " + prezime);
		int brojac = 1;
		boolean bilaPauza = false;
		while(!Main.kraj)
		{
			Random rand = new Random();
			int sekundPauze = rand.nextInt(16) + 5;
			if((System.currentTimeMillis() - Main.pocetak >= sekundPauze * 1000) && !bilaPauza)
			{
				bilaPauza = true;
				try
				{
					System.out.println(ime + " " + prezime + " uzima pauzu.");
					sleep(5000);
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			String zadatak = tekstZadatka + " # " + brojac;
			System.out.println(zadatak);
			zadaci.add(zadatak);
			brojac++;
			if(zadaci.size() == BROJ_ZADATAKA_ZA_OBRACUN)
			{
				Main.brojZaObracun++;
				//System.out.println(Main.brojZaObracun);
			}
			try
			{
				sleep(sekundeRada * 1000L);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			
			if(this instanceof Racuna)
			{
				Racuna r = (Racuna) this;
				if(Main.brojZaObracun == 3 && !obracunato)
				{
					r.racunaj();
					obracunato = true;
					System.out.println("VRSENJE OBRACUNA");
				}
			}
		}
	}
	
	public String toString()
	{
		return ime + " " + prezime + " " + godineRada + " " + cijenaRada;
	}
}