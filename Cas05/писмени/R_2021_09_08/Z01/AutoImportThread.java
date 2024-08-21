
import java.io.*;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;

public class AutoImportThread extends Thread {

    String folder;

    public AutoImportThread(String folder) {
        this.folder = folder;
		// Zapocinje se izvrsavanje niti, jako lako pogrijesiti na ispitu, treba run() pozvati
        start();
    }

    @Override
    public void run() {
        try {
			//Veoma bitno pregledati lab za NIO
			//Radjeno i na prvom terminu (github)
			//WatchService omogucava pracenje zeljenih promjena u folderu
            WatchService service = FileSystems.getDefault().newWatchService();
			//Kreiramo objekat klase Path da bismo mogli registrovati
            Path dir = Paths.get(folder);
			//Nad direktorijumom vrsimo registraciju WatchService-a i pratimo nove unose (ENTRY_CREATE)
            dir.register(service, ENTRY_CREATE);
            
			
			
			while (true) {
				//Kod sa laba, ima i u dokumentaciji, ne uciti
                WatchKey key = null;
                try {
                    key = service.take();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
					//Ako je dogadjaj ENTRY_CREATE
                    if (kind.equals(ENTRY_CREATE)) {
                        //Pronalazimo koji fajl je dodan
						String fileName = new File(folder).getAbsolutePath() + File.separator + ev.context();
                        System.out.println("CONTEXT ABSOLUTE " + fileName);
                        //Procesiranje fajla
						Main.processFile(fileName);
                    }
                }

                boolean valid = key.reset();
                if (!valid)
                    break;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}