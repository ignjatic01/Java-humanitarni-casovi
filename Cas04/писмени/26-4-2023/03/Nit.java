import java.util.Random;
import java.util.Iterator;

public class Nit extends Thread
{
	public int id;
	//Staticka varijabla da bismo imali jedinstven identifikator
	public static int count = 0;
	
	public Nit()
	{
		id = (++count);
		start();
	}
	
	@Override
	public void run()
	{
		Random rand = new Random();
		Iterator<Student> iterator = null;
		//50% sansi da idemo od vrha prema kraju
		if(rand.nextBoolean())
		{
			iterator = Main.studenti.descendingIterator();
		}
		else
		{
			iterator = Main.studenti.iterator();
		}
		
		//Dva studenta se nagradjuju, pa je < 2, i ako postoji naredni element -> iterator.hasNext()
		while(Main.counterStudenata < 2 && iterator.hasNext())
		{
			Student temp = null;
			//Sve druge niti cekaju prije ulaska u ovaj dio koda zbog zajednickog lock objekta
			synchronized(Main.lock)
			{
				temp = iterator.next();
			}
			System.out.println("Nit " + this.id + " obradjuje studenta " + temp); 
			
			//Druge niti ce automatski vidjeti promjenu counterStudenta jer je volatile
			
			if(rand.nextDouble() <= 0.02 && Main.counterStudenata < 2)
			{
				Main.izabrani.add(temp);
				Main.counterStudenata++;
				System.out.println("<-------------Nit " + this.id + " JE ODABRALA STUDENTA " + temp + "------------->"); 
			}
			try
			{
				sleep(100);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}