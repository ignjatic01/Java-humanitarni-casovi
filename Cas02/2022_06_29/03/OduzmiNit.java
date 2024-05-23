import java.lang.Thread;
import java.util.Random;
public class OduzmiNit extends Thread {
	
	
	
	
	@Override
	public void run() {
		Random random = new Random();
		while(Main.nitiUkljucene) {
			boolean smijeBrisati = random.nextInt(101) >= Main.postotak;
		
		if(smijeBrisati) {
			Student s = Main.red.ukloniStudenta();
			
			if(s != null) {
				System.out.println("UKLONJEN STUDENT " + s);
			}
		}
		try {
				sleep(500);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}