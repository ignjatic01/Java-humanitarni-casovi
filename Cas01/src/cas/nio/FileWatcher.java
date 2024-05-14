package cas.nio;

import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

public class FileWatcher
{
    public static void main(String[] args)
    {
        /*
        * PREPORUKA
        * Implementirati Watcher kao posebnu demonsku nit
        * Radi demonstracije i jednostavnosti je ovdje implementiran u main metodi.
        * */
        try
        {
            //Kreiramo WatchService objekat
            WatchService service = FileSystems.getDefault().newWatchService();

            //Kreiramo objekat klase Path sa URI-jem foldera koji zelimo posmatrati
            Path dir = Path.of("./folder/");

            //Registujrmo direktorijum kod WatchService-a za pracenje odredjenih vrsta dogadjaja
            //Posto je kao WatchEventKind stavljen ENTRY_CREATE posmatramo kreiranje novih datoteka
            //Postoje jos ENTRY_MODIFY za pracenje modifikacija i ENTRY_DELETE za detekciju brisanja datoteka
            dir.register(service, ENTRY_CREATE);

            //Koristimo beskonacnu petlju kako bismo bili sigurni da ce se folder posmatrati dok traje program
            while(true)
            {
                //Sledeci dio kopiramo iz API-ja (Nalazi se u klasi WatchKey)

                //Metodom take() cekamo da se pojavi dogadjaj u nadgledanom direktorijumu i vraca nam objekat
                //WatchKey koji je povezan sa tim dogadjajem
                WatchKey key = service.take();

                //Prolazimo kroz sve dogadjaje koji su povezni sa dobijenim WatchKey-em
                for(WatchEvent<?> event : key.pollEvents())
                {
                    //Dohvatamo vrstu dogadjaja
                    WatchEvent.Kind<?> kind = event.kind();

                    //Kasujemo dogadjaj kako bismo mogli lakse pristupiti informacijama o dogadjaju (npr. putanja datoteke)
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    if(kind.equals(ENTRY_CREATE))
                    {
                        //Logika obrade dogadjaja (zavisno od zadatka)
                        System.out.println("Datoteka kreirana");
                    }
                }

                //Resetujemo kljuc; kao povratnu vrijednost dobijamo da li je kljuc validan (false ako je folder obrisan)
                boolean valid = key.reset();
                if(!valid)
                {
                    break;
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
