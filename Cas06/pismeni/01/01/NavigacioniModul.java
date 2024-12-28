import java.util.Random;

public class NavigacioniModul extends Modul
{
	//Navigacioni Modul cuva koordinate odredista 
	public int koord;
	
	//Sve niti su daemon jer se zavrsavanjem programa i one zele terminirati
	//Tj., kad se glavna nit (nit glavnog programa) zavrsi, zavrsice se i sve ostale prethodno pokrenute
	public NavigacioniModul(int koord)
	{
		this.koord = koord;
		setDaemon(true);
	}
	
	@Override
	public void run()
	{
		while(!Main.kraj)
		{
			//Kretanje se vrsi u 10 koraka
			for(int i = 0; i < 10; i++)
			{
				KomunikacioniModul.poruke.add(new Poruka(Prioritet.INFO, "Kretanje letjelice: " + koord));
				
				//Koordinate se simuliraciju slucajnim vrijednostima od 0 do 100
				koord = new Random().nextInt(100);
				
				//Ako je 100, doslo je do kraja kretanja, zavrsavamo logiku u Main metodi
				if(koord == 100)
				{
					Main.kraj = true;
					break;
				}
				try
				{
					sleep(1000);
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}