import java.util.ArrayList;
import java.util.Random;

//Pogonski modul ima motore -> opet atribut, i s obzirom da je mnozina, koristimo listu
//ArrayLista je najpogodnija za cuvanje podataka, pokazati dokumentaciju i metode

public class PogonskiModul extends Modul
{
	public ArrayList<Motor> motori;
	//Podatak da li modul ima kvar
	public boolean statusModula;
	//Oznaka da li je ukljucen raketni pogon
	public boolean raketniPogon;
	
	//Kreiranje motora i dodavanje u listu, podesavanje statusa modula i raketnog pogona
	public PogonskiModul()
	{
		motori = new ArrayList<>();
		motori.add(new Motor());
		motori.add(new Motor());
		motori.add(new Motor());
		motori.add(new Motor());
		statusModula = false;
		raketniPogon = new Random().nextBoolean();
		setDaemon(true);
	}
	
	public PogonskiModul(ArrayList<Motor> motori)
	{
		this.motori = motori;
		statusModula = false;
		raketniPogon = new Random().nextBoolean();
		setDaemon(true);
	}
	
	@Override
	public void run()
	{
		//Zapocinje se rad svakog motora
		//Sintaksa forEach metode ide tako da je m kao parametar funkcije 
		// -> reprezentuje ulaz u metodu 
		// m.start() je tijelo metode (nema viticastih zagrada jer je samo jedna linija u metodi)
		motori.forEach(m -> m.start());
		while(!Main.kraj)
		{
			//Opet upotreba streamova koja ubrzava pisanje koda 
			//Nakon stream() se poziva korisna metoda filter() koja omogucava korisniku postavljanje uslova
			//Uslov je da li je motor pokvaren 
			//Na kraju se poziva mapToInt() samo da bi se nakon toga mogla pozvati metoda count()
			//I dobija se brojPokvarenihMotora
			long brojPokvarenihMotora = motori.stream().filter(m -> m.status.equals(StatusMotora.POKVAREN)).mapToInt(m -> m.snaga).count();
			System.out.println(brojPokvarenihMotora + " " + motori.size());
			//U tekstu zadatka je receno da, ako je vise od pola motora pokvareno, PogonskiModul ima kvar
			if(brojPokvarenihMotora >= motori.size()/2)
			{
				//Oznaka da je PogonskiModul pokvaren
				statusModula = true;
				try
				{
					//Doda se poruka u komunikacioni modul
					KomunikacioniModul.poruke.put(new Poruka(Prioritet.KRITICNO, "Otkaz Pogonskog modula"));
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
				//Podesava se oznaka za kraj simulacije
				Main.kraj = true;
				System.out.println("Evakuacija");
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