import java.util.Date;
import java.text.SimpleDateFormat;

public class Poruka implements Comparable<Poruka>
{
	//Poruka ima priroritet, opis i vrijeme (datum)
	public Prioritet prioritet;
	public String opis;
	public Date datum;
	
	public Poruka(Prioritet prioritet, String opis)
	{
		datum = new Date();
		this.prioritet = prioritet;
		this.opis = opis;
	}
	
	//Kao sto smo vidjeli, svaki objekat klase Poruka se ispise na standardni izlaz
	//U tom slucaju ispise se ono sto je redefinisano u metodi toString()
	@Override
	public String toString()
	{
		//SimpleDateFormat je korisno znati da postoji
		//Omogucava nam formatiranje datuma onako kako nama odgovara
		//U ovom slucaju samo sati, minute i sekunde
		//Nakon toga se pozove metoda format nad dateFormat da bismo dobili nas string
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String datumString = dateFormat.format(datum);
		return "Poruka: Prioritet: " + prioritet + " opis: " + opis + datumString;
	}
	
	@Override
	public int compareTo(Poruka o)
	{
		if(this.prioritet.equals(Prioritet.KRITICNO))
			return -1;
		else if(prioritet.equals(Prioritet.UPOZORENJE))
			return 0;
		else
			return 1;
	}
}