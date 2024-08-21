import java.util.Random;
import java.util.Date;
import java.text.SimpleDateFormat;

//Atributi definisani u tekstu, JAVNI, nema getera i setera!!!
public class Student implements Comparable<Student>
{
	public String ime;
	public String prezime;
	public String indeks;
	public double prosjecnaOcjena;
	
	//Staticka varijabla, svi objekti dijele istu vrijednost
	public static int count = 0;
	
	public Student()
	{
		//Nasumicno generisanje podataka, uvijek upotreba random
		Random rand = new Random();
		char slovo1 = (char) ('A' + rand.nextInt(26));
		char slovo2 = (char) ('A' + rand.nextInt(26));
		ime = slovo1 + "ime";
		prezime = slovo2 + "prezime";
		
		//SimpleDateFormat omogucava formatiranje datuma na osnovu onoga sto mi definisemo
		//U ovom slucaju yyyy predstavlja 4 cifre koje su godina
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		Date datum = new Date();
		//Pozivom metode format, formatiramo datum na osnovu onog sto smo mi definisali
		indeks = (++count) + "-" + dateFormat.format(datum);
		prosjecnaOcjena = 6.0 + rand.nextDouble() + rand.nextInt(4);
	}
		
	//Ovo se ispisuje zapravo na konzoli
	@Override
	public String toString()
	{
		return ime + " " + prezime + " " + indeks + " " + prosjecnaOcjena;
	}
	
	//Sortiranje po prosjeku studenata
	//Pored toString() i equals(), moguce je redefinisati i pored ostalih i compareTo()
	//Programer definise uslov poredjenja
	@Override
	public int compareTo(Student o)
	{
		return Double.compare(o.prosjecnaOcjena, this.prosjecnaOcjena);
	}
}