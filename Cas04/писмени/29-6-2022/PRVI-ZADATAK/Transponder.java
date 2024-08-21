
public interface Transponder {
	//salje svake sekunde koordinate
	//mogla je biti i jedna metoda, posalji koordinate, sve zavisi od konkretne logike pri implementaciji
	int posaljiX();
	int posaljiY();

}