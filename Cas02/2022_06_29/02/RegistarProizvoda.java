import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Date;
import java.io.*;
public class RegistarProizvoda {

	public static String fajlZaSer = "RegistarProizvoda_";

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		String unos = "";
		ArrayList<Proizvod> proizvodi = new ArrayList<>();
		
		File f = new File("."+File.separator);

		File[] files = f.listFiles();

		for(File file : files) {
			if(file.toString().trim().contains(fajlZaSer)) {
				try {

					FileInputStream fis = new FileInputStream(file);
					ObjectInputStream ois = new ObjectInputStream(fis);
					Proizvod p = (Proizvod)ois.readObject();
					proizvodi.add(p);
					ois.close();
					fis.close();					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		do {
			
			unos = scanner.nextLine();
			//a
			if(unos.equals("PREGLED_SVIH")) {
				proizvodi.stream().forEach(System.out::println);
			}
			
			//b
			if(unos.equals("PREGLED PO SIFRI")) {
				System.out.println("Unesite sifru: ");
				String dodatniUnos = scanner.nextLine();
				List<Proizvod> novaLista = proizvodi.stream().filter(x -> x.sifra.equals(dodatniUnos)).collect(Collectors.toList());
				if(novaLista.size() == 0) {
					System.out.println("NEMA PROIZVODA");
				}
				else {
					System.out.println(novaLista.get(0));
				}
				
			}
			
			//c
			if(unos.equals("NOVI PR")) {
				
				String sifra = scanner.nextLine();
				String naziv = scanner.nextLine();
				String opis = scanner.nextLine();
				double cijena = scanner.nextDouble();
				String tip = scanner.nextLine();
				
				List<Proizvod> novaLista = proizvodi.stream().filter((x) -> x.sifra.equals(sifra)).collect(Collectors.toList());
				
				if(novaLista.size() == 0) {
					proizvodi.add(new Proizvod(sifra, naziv, opis, cijena, tip)); 
				} else {
					System.out.println("Vec postoji proizvod!");
				}
				
			}
			
			//d
			if(unos.equals("BRISANJE")) {
				//Zbog ovoga sam vjerovatno dobio minus bodove, mogao sam koristiti filter da pronadjem dati objekat ako postoji i ukloniti ga po potrebi
				System.out.println("Unesite sifru: ");
				String dodatniUnos = scanner.nextLine();
				Proizvod zaUkloniti = new Proizvod();
				for(Proizvod p : proizvodi) {
					if(p.sifra.equals(dodatniUnos)) {
						zaUkloniti = p;
					}
				}
				proizvodi.remove(zaUkloniti);
			}
			
			//e
			if(unos.equals("GRUPISANI PO TIPU")) {
				Map<String, List<Proizvod>> grupacija = proizvodi.stream().collect(Collectors.groupingBy(Proizvod::getTip));
				System.out.println(Arrays.toString(grupacija.entrySet().toArray()));
			}
			
			//f
			if(unos.equals("OPSEG")) {
				double donjaGranica = scanner.nextDouble();
				double gornjaGranica = scanner.nextDouble();
				
				proizvodi.stream().filter((x) -> x.cijena > donjaGranica && x.cijena < gornjaGranica).forEach(System.out::println);
			}
			
		} while(!"EXIT".equals(unos));
		
		
		for(Proizvod p : proizvodi) {
			
			try {
			FileOutputStream fos = new FileOutputStream(new File(fajlZaSer + String.valueOf(new Date().getTime()) + ".ser"));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(p);
			oos.close();
			fos.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			
		}
		
	}
	


}
