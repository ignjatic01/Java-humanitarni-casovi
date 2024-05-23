//Trazeno da se implementira red (struktura podataka)
//Dvije niti : Dodaj Studenta i Oduzmi studenta (NE SMIJU ISTOVREMENO DA PRISTUPAJU REDU) -> STA TO ZNACI?
//Kroz komandnu liniju se proslijedi postotak koji predstavlja sansu da nit doda/ukloni studenta
//Program mora minimalno jednu minutu da se izvrsava
//Sve preostale studente neophodno upisati u fajl
//Niti odmaraju nakon upisa/brisanje pola sekunde

public class Student {

	
	public String ime, prezime, brIndeksa;
	public double prosjek;
	
	public Student(String i, String p, String brI, double pr) {
		ime = i;
		prezime = p;
		brIndeksa = brI;
		prosjek = pr;
	}
	
	@Override
	public String toString() {
		return "Ime: " + ime + " Prezime: " + prezime + " BrIndeksa: " + brIndeksa;
	}


}