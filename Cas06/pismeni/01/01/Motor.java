import java.util.Random;

//Motor ima Status, ovdje je naznaceno da je status enumeracija
//Kako generalno raspoznati da li je potrebno koristiti enumeraciju?
//Odgovor je da nam je unaprijed zadat skup vrijednosti za neki atribut, pa je tada koristimo!!!
public class Motor extends Thread
{
	public StatusMotora status;
	public int snaga;
	
	//Tipicno se u konstruktoru vrsi inicijalizacija svih atributa tako da ga mozemo pozvati sa drugog mjesta
	//Motor je na pocetku upaljen
	public Motor()
	{
		//Receno da je snaga slucajan broj od 1000 do 10000
		//Pokazati dokumentaciju i klasu Random
		Random rand = new Random();
		snaga = rand.nextInt(9001) + 1000;
		status = StatusMotora.UPALJEN;
		setDaemon(true);
	}
	
	//toString() se redefinise i ispisuje na std. izlazu prilikom pokretanja aplikacije
	//Ako kazemo System.out.println(motor) ispisace se ono sto je redefinisano u toString()
	//Isto tako, ako otvorimo PrintWriter i pozovemo metodu println(motor) ispisace se ispod redefinisano
	@Override 
	public String toString()
	{
		return "Motor: " + snaga + " " + status;
	}
	
	//Motor je dio PogonskogModula i on po tekstu zadatka ima sansu od 20% da se pokvari
	@Override
	public void run()
	{
		Random rand = new Random();
		while(!Main.kraj)
		{
			//Simulirano 20% sanse za kvar pomocu klase Random -> Random veoma korisna klasa
			if(rand.nextDouble() < 0.20)
			{
				status = StatusMotora.POKVAREN;
				try
				{
					//U KomunikacioniModul se smjesta nova poruka
					KomunikacioniModul.poruke.put(new Poruka(Prioritet.UPOZORENJE, "Otkaz Motora"));
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			try
			{
				//1 korak traje 1 sekund
				sleep(1000);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}