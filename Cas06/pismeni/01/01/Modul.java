//Modul krovna klasa za tri vrste modula
//Cisto da se naznaci postojanje hijerarhije klasa
//U modul bi islo sve zajednicko za tri vrste Modula
//U ovom slucaju to nam nije trebalo
//Svi moduli se izvrsavaju istovremeno, zbog toga koristimo niti 
//Niti su jedini nacin da se vise stvari izvrsava istovremeno u Java aplikaciji
//Naslijedi se klasa Thread i redefinise metoda run() 
public class Modul extends Thread
{
}