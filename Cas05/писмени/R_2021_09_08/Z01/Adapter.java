
import java.io.*;

//Apstrakna klasa, lakse dodavanje novih adaptera, naznaceno i u tekstu
public abstract class Adapter {
	
	//Putanja do foldera
    static String PATH = "."+File.separator+"PJ2_exam_data";
	
	//Apstraktna metoda cijim redefinisanjem se mogu uvesti razlicite vrste fajlova
    public abstract void importData(String fileName);

    public void addObject(String[] elements) {
		// Na osnovu fajla (uci na ispitu) vidjeti koliko elemenata ima
        if (elements.length != 6)
            return;
        // tf102,S8,2,1530,Samsung,Telefon
        try {
            Proizvod p = new Proizvod(elements[0], elements[1],
                    Integer.parseInt(elements[2]), Double.parseDouble(elements[3]));
            Proizvodjac pr = new Proizvodjac(elements[4]);
            Vrsta v = new Vrsta(elements[5]);
            Main.podaci.add(p);
            Main.podaci.add(pr);
            Main.podaci.add(v);
        } catch (NumberFormatException e) {
            return;
        }
    }
}