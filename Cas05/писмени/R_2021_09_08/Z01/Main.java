
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    static ArrayList<String> folderi = new ArrayList<>();
	//Ako u red treba da dodamo objekte vise razlicitih klasa, onda red stavimo da sadrzi interfejs
	//Tada vise klasa moze da implementira interfejs i to moze da se nalazi u redu
	//Svi objekti se smjestaju u jednu dijeljenu kolekciju
    static BlockingQueue<Element> podaci = new LinkedBlockingQueue<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String input;
        while (true) {
			//Korisnik unosi nacin citanja podataka
            input = scanner.nextLine();
            if (input.startsWith("IMPORT")) {
                String fajl = input.split("\t")[1]; // WARNING Splitujemo na tabu!!!
                processFile(fajl);
            } else if (input.startsWith("AUTO_IMPORT")) {
				//Za svaki novi folder, kreira se nova nit, kao po tekstu zadatka
                String folder = input.split("\t")[1]; // WARNING Splitujemo na tabu!!!
                AutoImportThread autoImportThread = new AutoImportThread(folder);
                folderi.add(folder);
            } else if ("STATUS".equals(input)) {
				//Ispis trazenih stavki pomocu komande STATUS
                System.out.println("Broj foldera koji se prate " + folderi.size());
                System.out.println("Broj stavki u kolekciji " + podaci.size());
                folderi.forEach(System.out::println);
            } else if ("SAVE".equals(input)) {
				//Veoma specificno, home direktorijum se trazi
				//Tesko nauciti sve, ovo jedino kroz vjezbu
                String path = System.getProperty("user.home");
				//try with resources, ne mora se paziti zatvaranje streamova
                try (ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream(path + File.separator + "podaci.ser"))) {
                    oos.writeObject(podaci);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void processFile(String fajl) {
        Adapter adapter = null;
        if (fajl.endsWith("csv")) {
			//Tokom izvrsavanja programa (run-time) se odredi koje redefinisanje apstraktne metode treba da se pozove
            adapter = new CSVAdapter();
        } else if (fajl.endsWith("txt")) {
            adapter = new TXTAdapter();
        }
		//poziv metode
        if (adapter != null)
            adapter.importData(fajl);
    }
}