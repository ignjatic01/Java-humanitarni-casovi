import java.io.Serializable;

public class Proizvod implements Serializable {
	
	public String sifra, naziv, opis;
	public double cijena;
	public String tip;
	
	public Proizvod() {
		
	}
	
	public Proizvod(String s, String n, String o, double c, String t) {
		sifra = s;
		naziv = n;
		opis = o;
		cijena = c;
		tip = t;
	}
	
	public String getTip() {
	  return tip;
	}

	@Override
	public String toString() {
		return "Sifra: " + sifra + " Naziv: " + naziv + " Opis: " + opis + " Cijena: " + cijena + " Tip: " + tip;
	}


	@Override
	public boolean equals(Object oth) {
		if(oth != null && oth instanceof Proizvod) {
			Proizvod obj = (Proizvod)oth;
			if(obj.sifra == sifra) {
				return true;
			}
		}
		
		return false;
		
	}
}
