package cas.oop;

public class Vozilo
{
    public String naziv;

    public Vozilo(String naziv)
    {
        this.naziv = naziv;
    }

    public Vozilo()
    {
        this.naziv = "";
    }

    //PRAVILNA UPOTREBA
    public void vozi1()
    {
        System.out.println("-----------------------------");
        System.out.println(this.naziv);
        if(this instanceof PrevoziTeret)
        {
            System.out.println("Prevozi teret");
        }
        else
        {
            System.out.println("Prevozi putnike");
        }
        this.metoda();
        System.out.println("-----------------------------");
    }

    //NEPRAVILNA UPOTREBA
    public void vozi2()
    {
        System.out.println("-----------------------------");
        System.out.println(this.naziv);
        if(this instanceof Kamion)
        {
            System.out.println("Prevozi teret");
        }
        else
        {
            System.out.println("Prevozi putnike");
        }
        this.metoda();
        System.out.println("-----------------------------");
    }

    public void metoda()
    {
        System.out.println("Metoda klase vozilo");
    }
}




