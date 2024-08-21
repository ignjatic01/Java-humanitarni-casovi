import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Map;

public class Main
{
	public static void main(String args[])
	{
		//validacija argumenata komandne linije
		if(args.length != 1)
			return;
		
		//Kreiranje objekta klase File na osnovu proslijedjenje putanje
		File ulaz = new File(args[0]);

		//isDirectory() -> veoma korisna metoda za odredivanje da li je u pitanju folder
		if(ulaz.isDirectory())
		{
			folderLogika(ulaz);
		}
		//isFile() korisna metoda da se provjeri da li je u pitanju fajl
		else if(ulaz.isFile())
		{
			fajlLogika(ulaz);
		}
		else
		{
			return;
		}
	}
	
	//U slucaju da je folder u pitanju
	public static void folderLogika(File ulaz)
	{
		//listFiles omogucava iscitavanje svih fajlova u folderu
		File[] fajloviUlaz = ulaz.listFiles();
		ArrayList<File> fajlovi = new ArrayList<>();
		for(File f : fajloviUlaz)
			fajlovi.add(f);
		
		//1
		//Sortiranje fajlova po velicini, da bi se znalo koji je najveci
		//Iz NIO paketa, size metoda koja prima Path, korisna u ovom slucaju
		System.out.print("Najveci fajl: ");
		fajlovi.stream().sorted((f1, f2) -> {
			try
			{
				long f1s = Files.size(Path.of(f1.getPath()));
				long f2s = Files.size(Path.of(f2.getPath()));
				return Long.compare(f2s, f1s);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			return 0;
		}).limit(1).forEach(System.out::println); //pomocu limit 1 uzmemo samo prvi, nakon sortiranja najveci fajl i ispisemo na std izlaz
		
		//2
		System.out.println("Ukupna velicina svih fajlova: " + fajlovi.stream().mapToLong(f -> {
			//LongStream
			Long size = 0L;
			try
			{
				size = Files.size(Path.of(f.getPath()));
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			return size;
		}).sum()); //sum pozivamo jer smo prethodno pozvali mapToLong
		
		//3
		//Pomocu collect grupisemo fajlove na osnovu ekstenzije
		
		//Map<String, List<File>> 
		fajlovi.stream().collect(Collectors.groupingBy(f -> f.getName().substring(f.getName().lastIndexOf("."), f.getName().length())))
			//Nakon grupisanja, sortiramo ih na osnovu prvog slova u nazivu i ispisemo ih
			.forEach((key, value) -> {
				System.out.println(key + ": ");
				value.stream().sorted((f1, f2) -> Character.compare(f1.getName().charAt(0), f2.getName().charAt(0))).forEach(f -> System.out.println(f.getName()));
			});
			
		//4
		//Collectors.toMap prima kljuc a u ovom slucaju to je apsolutna vrijednost fajla, a kljuc je sam fajl
		Map<String, File> mapaFajlova = fajlovi.stream().collect(Collectors.toMap(f -> f.getAbsolutePath(), f -> f));
		mapaFajlova.forEach((key, value) -> System.out.println(key + ": " + value));
	}
	
	//U slucaju da je fajl u pitanju
	public static void fajlLogika(File ulaz)
	{
		try
		{
			//Iscitavanje sadrzaja fajla -> readAllLines veoma korisna metoda, svaki element liste jedna linija iz fajla
			List<String> lines = Files.readAllLines(Path.of(ulaz.getAbsolutePath()));
			//upotreba streamova za sortiranje -> stream vraca stream (nad kojim se mogu pozivati razne korisne metode)
			//sorted omogucava sortiranje na osnovu kriterijuma koji mi definisemo, u ovom slucaju poredjenjom prvog karaktera
			//kroz vjezbu upoznavanje sa metodama
			//collect vrsi obradu rezultata u listu
			List<String> sortedLines = lines.stream().sorted((line1, line2) -> Character.compare(line1.charAt(0), line2.charAt(0))).collect(Collectors.toList());
			HashMap<Character, Integer> mapaSlova = new HashMap<>();
			File folder = new File("./obradjeno");

			//try with resources blok, nema brige oko zatvranja streamova
			try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(ulaz))))
			{
				if(!folder.exists())
				{
					folder.mkdir();
				}
				
				//za sortirano u listi, provjeriti da li mapa sadrzi kljuc koji je pocetno slovo, ako ne, dodati u mapu i azurirati broj redova za pocetno slovo
				for(String line : sortedLines)
				{
					if(mapaSlova.containsKey(line.charAt(0)))
					{
						mapaSlova.put(line.charAt(0), mapaSlova.get(line.charAt(0)) + 1);
					}
					else
					{
						mapaSlova.put(line.charAt(0), 1);
					}
					//upis u fajl
					pw.println(line);
					//upis sortiranih elemenata u fajl
	
					
					try(PrintWriter pw2 = new PrintWriter(new BufferedWriter(new FileWriter(new File(folder.getAbsolutePath() + File.separator + "sortirani" + line.charAt(0) + ".txt"), true))))
					{
						pw2.println(line);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			//ispis putanje do fajla i broja redova za pocetno slovo
			System.out.println("Putanja do foldera je: " + folder.getPath());
			mapaSlova.entrySet().stream().forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}