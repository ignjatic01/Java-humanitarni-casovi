import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Racunovodja extends Radnik implements Racuna
{
	public Racunovodja(String ime, String prezime, int godineRada, int cijenaRada)
	{
		super(ime, prezime, godineRada, cijenaRada, "Obracun troskova", 10);
	}
	
	public void racunaj()
	{
		File file = new File(putanja);
		try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file))))
		{
			for(Radnik r : Main.radnici)
			{
				int iznos = r.cijenaRada + r.godineRada;
				pw.println(r.ime + " " + r.prezime + ", " + iznos);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}