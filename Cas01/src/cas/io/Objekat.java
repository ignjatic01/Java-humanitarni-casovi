package cas.io;

import java.io.Serializable;

public class Objekat implements Serializable
{
    public String ime;
    public int broj;

    public Objekat(String ime, int broj)
    {
        this.ime = ime;
        this.broj = broj;
    }
}
