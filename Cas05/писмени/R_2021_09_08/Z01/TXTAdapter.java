
import java.io.*;
import java.util.*;

public class TXTAdapter extends Adapter {
    
	@Override
    public void importData(String fileName) {
		ArrayList<String[]> linije = new ArrayList<>();
		String[] splitFileName = fileName.split("_");
		Integer numElements = Integer.parseInt(splitFileName[0]);
		
        try (BufferedReader br = new BufferedReader(new FileReader(fileName));
             PrintWriter pw = new PrintWriter(Adapter.PATH + File.separator + new File(fileName).getName())) {
            String linija;
            while ((linija = br.readLine()) != null) {
                pw.println(linija);
                linija = linija.substring(2, linija.length() - 2);
                String[] elements = linija.split("\\|\\|");
				
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