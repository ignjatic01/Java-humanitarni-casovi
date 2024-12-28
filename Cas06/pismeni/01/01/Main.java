import java.util.Scanner;

public class Main
{
	//Staticka varijabla kojoj se pristupa iz vise razlicitih klasa
	//Jedinstvena vrijednost varijable na nivou citave aplikacije
	public static volatile boolean kraj = false;
	
	public static void main(String args[])
	{
		//Unutar letjelice kreiraju se 4 motora
		Letjelica letjelica = new Letjelica();
		//Scanner koristimo za korisnicki unos sa konzole
		Scanner scan = new Scanner(System.in);
		String line = "";
		//Unosom komande STOP zaustavlja se izvrsavanje programa!
		while(!"STOP".equals(line) && !kraj)
		{
			line = scan.nextLine();
		}
		try
		{
			//Pridruzivanje niti glavnoj (main) niti
			letjelica.join();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("Kraj simulacije");
	}
}