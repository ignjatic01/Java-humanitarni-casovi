import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.ArrayList;

public class Main
{
	//Elementi u TreeSet-u se automatski sortiraju, te smo stoga redefinisali compareTo() u klasi Student
	//Receno da niti trebaju pristupiti kolekciji studenata, zbog toga staticka varijabla
	public static TreeSet<Student> studenti = new TreeSet<>();
	public static Object lock = new Object();
	public static BlockingQueue<Student> izabrani = new LinkedBlockingQueue<>();
	
	/*The volatile keyword in Java is used to indicate that a variable's value can be 
	modified by different threads. Used with the syntax, volatile dataType variableName = x; 
	It ensures that changes made to a volatile variable by one thread are immediately visible 
	to other threads.*/
	public static volatile int counterStudenata = 0;
	public static ArrayList<Nit> niti = new ArrayList<>();
	
	//inicijalizacija 2000 studenata i dodavanje u red
	public static void initialize()
	{
		for(int i = 0; i < 2000; i++)
		{
			//Sinhronizacija omogucava sigurno sortiranje, saceka se sortiranje prije dodavanja novog studenta
			synchronized(lock)
			{
				//Cim dodamo studenta, automatski se vrsi sortiranje
				studenti.add(new Student());
			}
		}
	}
	
	public static void main(String args[])
	{
		initialize();
		//studenti.stream().forEach(System.out::println);
		
		//Kreiranje 50 niti
		for(int i = 0; i < 50; i++)
		{
			niti.add(new Nit());
		}
		
		//Sacekamo da niti zavrse posao, da se main ne bi zavrsio a samim tim i niti
		niti.stream().forEach(n -> {
			try
			{
				n.join();
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		});
		System.out.println("KRAJ SIMULACIJE");
		
		//Pomocu streama se ispisuju izabrani studenti
		izabrani.forEach(System.out::println);
	}
}