import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Treca extends Thread
{
	public Character slovo;
	public ArrayList<Character> samoglasnici = new ArrayList<>();
	public String odgovor = "";
	
	public Treca(Character slovo)
	{
		this.slovo = slovo;
		//Neophodni su samoglasnici jer suglasnike mijenjamo sa *
		samoglasnici.add('a');
		samoglasnici.add('e');
		samoglasnici.add('i');
		samoglasnici.add('o');
		samoglasnici.add('u');
	}
	
	@Override
	public void run()
	{
		//mapa je staticka varijabla pa joj je moguce pristupiti
		List<String> kol = Prva.mapa.get(slovo);
		System.out.println("Trazi se rijec na slovo: " + slovo);
		//Uzima se nasumicna rijec za pogadjanje
		int br = new Random().nextInt(kol.size());
		String rijec = kol.get(br);
		
		//Pregledati API za string, pretvaramo string u niz karaktera 
		//Da bismo mogli iterirati kroz karaktere
		char[] slovaRijeci = rijec.toCharArray();
		StringBuilder suglasnici = new StringBuilder();
		for(char c : slovaRijeci)
		{
			if(samoglasnici.contains(c))
			{
				System.out.print(c);
			}
			else
			{
				System.out.print("*");
				suglasnici.append(c);
			}
		}
		odgovor = suglasnici.toString();
		//Korisnik unosi rijec za pogadjanje 
		String unos = "";
		Scanner scanner = new Scanner(System.in);
		System.out.println("Unesite odgovor: ");
		unos = scanner.nextLine();
		//Pokretanje cetvrte niti sa odgovorom i korisnikovim unosom
		(new Cetvrta(odgovor, unos)).start();
	}
}