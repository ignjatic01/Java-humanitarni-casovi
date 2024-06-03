//APSTRAKTNA KLASA, NIGDJE SE NE INSTANCIRA, VODITI RACUNA NA PROJEKTU POGOTOVO
public abstract class VojnaPlovila extends Thread
{
	//SVA plovila imaju identifikator, izdvojiti u nadklasu da nemate tri identifikatora u klasama nasljednicama
	public static int identifikator = 0;
	public int id;
	//pozicija na mapi po X-osi
	public int pozicijaX;
	//pozicija na mapi po Y-osi
	public int pozicijaY;
	long startTime;
	boolean isAlive = true;
	//objekat koji se zakljucava kada se radi sa nitima
	public Object ObjectToLock = "";
	public VojnaPlovila(int pozicijaX)
	{
		this.pozicijaX = pozicijaX;
		id = identifikator;
		identifikator++;
	}
}