import java.lang.Thread;
import java.util.Random;

public class DodajNit extends Thread {

	
	
	@Override
	public void run() {
		Random random = new Random();
		
		while(Main.nitiUkljucene) {
			//da li je nasumican broj od 0 do 100 < postotka koji je proslijedjen preko komandne linije
			boolean upis = random.nextInt(101) < Main.postotak;
			if(upis) {
			char prvoSlovoImena = (char)(random.nextInt(26) + 'a');
			char prvoSlovoPrezimena = (char)(random.nextInt(26) + 'a');
			String indeks = "110" + String.valueOf(random.nextInt(60)) + "/20";
			double prosjek = 6.0 + random.nextDouble() * 4;
			Student s = new Student(prvoSlovoImena + "ime", prvoSlovoPrezimena + "prezime", indeks, prosjek);
			Main.red.dodajStudenta(s);
			System.out.println("DODAO STUDENTA " + s);
			
		}
		try {
				sleep(500);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}