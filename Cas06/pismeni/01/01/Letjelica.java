//Letjelica se sastoji iz tri modula
//Cim se sastoji od njih, to su atributi unutar letjelice
//Da je bilo da Letjelica moze biti Avion, Helikopter, Dron, onda bi to bilo nasljedjivanje
//Bitno je raspoznati i razlikovati nasljedjivanje i kompoziciju/agregaciju
public class Letjelica extends Thread
{
	public PogonskiModul pogonskiModul;
	public KomunikacioniModul komunikacioniModul;
	public NavigacioniModul navigacioniModul;
	
	public Letjelica()
	{
		pogonskiModul = new PogonskiModul();
		komunikacioniModul = new KomunikacioniModul();
		navigacioniModul = new NavigacioniModul(0);
		start();
	}
	
	//Zapocne se kretanje tri vrste modula
	@Override
	public void run()
	{
		pogonskiModul.start();
		komunikacioniModul.start();
		navigacioniModul.start();
	}
}