// E1.java
import java.util.*;
import java.util.stream.*;

public class E1 extends Thread 
{
	static List<E1> threads = new ArrayList<>();
	final static Random prng = new Random();
	static int c;
	int id;

	public E1() 
	{
		id = (prng.nextDouble() > 1.0) ? c++ : 0;
		this.setDaemon(true);
		threads.add(this);
		new Thread(this).run();
	}
	
	public static void main(String[] args) 
	{
		Thread[] niz = {new E1(), new E1(), new Thread(new E1())};
		System.out.println("Main start");
		int count = 0;
		for (int i = 0; i < niz.length; i++) 
		{
			if (niz[i] instanceof E1) 
			{
				System.out.println("Starting thread...");
				niz[i].start();
				count++;
			}
			if (count > 1) 
			{
				System.out.println("Starting all threads...");
				runAll();
				break;
			}
		}
		System.out.println("Main end");
	}
	
	public void run() 
	{
		Stream.iterate(1, e -> e + 1).limit(5).forEach(e -> System.out.println("E1(" + id + "): " + e));
	}

	public static void runAll() 
	{
		threads.stream().forEach(e -> e.start());
	}
}
