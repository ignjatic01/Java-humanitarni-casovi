import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.io.IOException;

public class Main
{
	public static String putanja;
	public static String izlaznaPutanja;
	
	public static void main(String args[])
	{
		long pocetak = System.currentTimeMillis();
		if(args.length != 2)
		{
			return;
		}
		
		putanja = args[0];
		izlaznaPutanja = putanja + File.separator + args[1];
		
		File izlazFolder = new File(izlaznaPutanja);
		if(!izlazFolder.exists())
			izlazFolder.mkdir();
		
		obidji(putanja);
		obradaRezultata();
		System.out.println("Kraj " + (System.currentTimeMillis() - pocetak) + " ms");
	}
	
	public static void obradaRezultata()
	{
		File root = new File(izlaznaPutanja);
		File tipskiFolderi[] = root.listFiles();
		for(File folder : tipskiFolderi)
		{
			File[] fajlovi = folder.listFiles();
			System.out.println("Folder: " + folder.getName());
			System.out.println("Ukupan broj fajlova: " + fajlovi.length);
			try
			{
				long velicinaFajlova = 0;
				for(File f : fajlovi)
				{
					velicinaFajlova += Files.size(Path.of(f.getPath()));
				}
				System.out.println("Velicina foldera: " + velicinaFajlova + " bajtova");
				System.out.println("Prosjecna velicina fajlova: " + ((double)velicinaFajlova / (double) fajlovi.length));
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void obidji(String putanja)
	{
		File f = new File(putanja);
		if(f.isFile())
		{
			razvrstaj(f.getPath());
		}
		else if(f.isDirectory())
		{
			File[] files = f.listFiles();
			for(File file : files)
			{
				if(file.isDirectory())
				{
					obidji(file.getPath());
				}
				else if(file.isFile())
				{
					razvrstaj(file.getPath());
				}
			}
		}
	}
	
	public static void razvrstaj(String putanjaFajla)
	{
		String ekstenzija = putanjaFajla.substring(putanjaFajla.lastIndexOf(".") + 1, putanjaFajla.length());
		File folder = new File(izlaznaPutanja + File.separator + ekstenzija);
		if(!folder.exists())
		{
			folder.mkdir();
		}
		File ulaz = new File(putanjaFajla);
		File izlaz = new File(folder.getPath() + File.separator + ulaz.getName());
		
		try
		{
			Files.copy(Path.of(ulaz.getPath()), Path.of(izlaz.getPath()));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
