import java.util.Random;

public class Druga extends Thread
{
	@Override 
	public void run()
	{
		//Slucajno biranje -> upotreba klase Random
		Random rand = new Random();
		char slovo = (char) ('a' + rand.nextInt(26));
		//Zapocne se kretanje trece niti i proslijedi joj se slovo
		(new Treca(slovo)).start();
	}
}