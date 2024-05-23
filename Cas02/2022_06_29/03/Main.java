import java.util.Date;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;

public class Main {

	public static int postotak;
	public static Red red;
	public static boolean nitiUkljucene = true;
	
	public static void main(String[] args) {
		long startnoVrijeme = new Date().getTime();
		postotak = Integer.parseInt(args[0]);
		
		
		red = new Red();
		DodajNit dN = new DodajNit();
		OduzmiNit oN = new OduzmiNit();
		dN.start();
		oN.start();
		do {
			
			
		} while(new Date().getTime() - startnoVrijeme <= 60_000);
	
		nitiUkljucene = false;
		
		System.out.println("OBRADA PREOSTALIH STUDENATA: ");
		try {
			PrintWriter pw = new PrintWriter(new File("UpisiPreostale.txt"));
			Student st;
			while((st = red.ukloniStudenta()) != null) {
				pw.println(st);
			}  
			pw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

}