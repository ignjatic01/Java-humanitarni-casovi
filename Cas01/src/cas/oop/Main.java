package cas.oop;

public class Main
{
    public static void main(String args[])
    {
        Vozilo[] vozila = { new Automobil(), new Kamion(), new Kamion(), new Automobil()};
        for(Vozilo vozilo : vozila)
        {
            vozilo.vozi1();
        }
    }
}
