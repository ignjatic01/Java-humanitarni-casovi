import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

//I ova klasa nasljedjuje krovnu klasu Modul
public class KomunikacioniModul extends Modul
{
	//Komunikacioni modul prima poruke iz drugih klasa kao sto smo mogli vidjeti
	//I ispisuje ih na konzolu
	//Koristi se red jer FIFO pristup sa porukama ima smisla, prva koja je stigla se i ispisuje
	//Pokazati dokumentaciju i osnovne metode za red
	public static BlockingQueue<Poruka> poruke = new PriorityBlockingQueue<>();
	
	public KomunikacioniModul()
	{
		setDaemon(true);
	}
	
	//Modul vec nasljedjuje klasu Thread, pa to radi i KomunikacioniModul
	@Override
	public void run()
	{
		while(!Main.kraj)
		{
			//Provjera da li postoje poruke u redu
			if(!poruke.isEmpty())
			{
				try
				{
					//Receno da se poruke ispisuju na konzoli
					//Pomocu take() uzmemo poruku i ispisemo je 
					System.out.println(poruke.take());
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			try
			{
				sleep(1000);
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		while(!poruke.isEmpty())
		{
			try
			{
				System.out.println(poruke.take());
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}