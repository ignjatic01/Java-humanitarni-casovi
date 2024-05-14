package cas.oop;

public class Automobil extends Vozilo implements PrevoziPutnike
{
    public Automobil()
    {
        super("Automobil");
    }

    @Override
    public void metoda()
    {
        System.out.println("Metoda klase automobil");
    }
}
