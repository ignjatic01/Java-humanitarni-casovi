public class D1 implements Comparable<D1> {

	static D1 instance = new D1();
	String rijec;
	
	private D1() {}
	
	public static void main(String arr[]) {
		D1 d1 = D1.getInstance();
		D1 d2 = D1.getInstance();
		
		d1.rijec = "hello";
		d2.rijec = new String("HELLO");
		System.out.println(d1.rijec == d2.rijec);
		System.out.println(d1.rijec.equalsIgnoreCase(d2.rijec));
		System.out.println(d1.compareTo(d2));
		System.out.println(d1.rijec + " ? " + d2.rijec);
	}
	
	public static D1 getInstance() {
		if (instance == null)
			instance = new D1();
		return instance;
	}
	@Override
	public int compareTo(D1 other) {
		other.rijec.toLowerCase();
		return rijec.compareTo(other.rijec);
	}
}
