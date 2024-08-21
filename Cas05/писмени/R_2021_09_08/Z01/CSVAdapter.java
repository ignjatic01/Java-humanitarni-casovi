
import java.io.*;
import java.util.*;

//Nasljedjue adapter klasu
public class CSVAdapter extends Adapter {
    
	//Redefinisanje import metode
	@Override
    public void importData(String fileName) {
		ArrayList<String[]> linije = new ArrayList<>();
		String[] splitFileName = fileName.split("_");
		Integer numElements = Integer.parseInt(splitFileName[0]);
		
		//try with resources, ne moramo zatvarati streamove!!!
        try (BufferedReader br = new BufferedReader(new FileReader(fileName));
             PrintWriter pw = new PrintWriter(Adapter.PATH + File.separator + new File(fileName).getName())) {
            String linija;
            while ((linija = br.readLine()) != null) {
                pw.println(linija);
				//U CSV fajlovima elementi su razdvojeni sa , -> korisno za znati
                String[] elements = linija.split(",");
				linije.add(elements);
                //addObject(elements);
            }
						System.out.println("Linije " + linije.size());
			System.out.println("Br. elemenata " + numElements);
			if(linije.size() != numElements) {
				System.out.println("Razlicito!");
			} else {
				for(String[] linija2 : linije) {
					addObject(linija2);
				}
			}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}