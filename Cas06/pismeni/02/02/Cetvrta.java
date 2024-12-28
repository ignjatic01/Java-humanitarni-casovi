public class Cetvrta extends Thread
{
	public String odgovor;
	public String unos;
	
	public Cetvrta(String odgovor, String unos)
	{
		this.odgovor = odgovor;
		this.unos = unos;
	}
	
	@Override
	public void run()
	{
		//Provjera validnosti odgovora
		if(odgovor.equals(unos))
		{
			System.out.println("Rijec pogodjena!");
		}
		else
		{
			//U suprotnom druga nit se opet pokrece
			(new Druga()).start();
		}
	}
}