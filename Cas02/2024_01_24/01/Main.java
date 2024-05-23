import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
	public static boolean kraj = false;
	public static long pocetak;
	public static ArrayList<Radnik> radnici = new ArrayList<>();
	
	public static int brojZaObracun = 0;
	
	public static void main(String args[])
	{
		pocetak = System.currentTimeMillis();
		RadnikNabavke rn = new RadnikNabavke("Srdjan", "Grahovac", 5, 1500);
		RadnikProdaje rp = new RadnikProdaje("Stojan", "Vranjes", 5, 1500);
		Racunovodja r = new Racunovodja("Zoran", "Kvrzic", 2, 2000);
		
		radnici.add(rn);
		radnici.add(rp);
		radnici.add(r);
		
		rn.start();
		rp.start();
		r.start();
		
		Scanner scan = new Scanner(System.in);
		String input = "";
		while(!"KRAJ".equals(input))
		{
			input = scan.nextLine();
		}
		
		kraj = true;
		
		try
		{
			for(Radnik radnik : radnici)
			{
				radnik.join();
			}
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("KRAJ SIMULACIJE");
		System.out.println("Vrijeme trajanja: " + (System.currentTimeMillis() - pocetak) + " ms");
		for(Radnik radnik : radnici)
		{
			System.out.println(radnik.ime + " " + radnik.prezime + ": " + radnik.zadaci.size());
		}
	}
}