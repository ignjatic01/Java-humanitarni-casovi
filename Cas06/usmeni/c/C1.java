public class C1 
{
	C1() 
	{
		System.out.println("C1()");
	}
	
	public static void main(String[] args) 
	{
		C1 c1 = new C1();
		try 
		{
			System.out.println("main 1");
			c1.metoda();
		} catch (CE2 e) {
			System.out.println("main 2: " + e);
		} catch (CE1 e) {
			System.out.println("main 3: " + e);
		} catch (Exception e) {
			System.out.println("main 4: " + e);
		} catch (Throwable e) {
			System.out.println("main 5: " + e);
		}
	}
	
	void metoda() throws Throwable 
	{
		C2 c2 = new C2();
		try 
		{
			c2.metoda();
			System.out.println("C1: metoda()");
		} 
		finally 
		{
			System.out.println("finally");
		}
	}
}

class C2 extends C1 
{
	C2 () 
	{
		System.out.println("C2()");
	}
	
	void metoda() throws CE1 
	{
		C3 c3 = new C3();
		System.out.println("C2: metoda()");
		System.out.println(c3.metoda2());
		throw new CE2("CE2");
	}
}

class C3 extends C2 
{
	C3 () 
	{
		System.out.println("C3()");
	}
	
	protected int metoda2() throws CE1 
	{
		try 
		{
			System.out.println("C3: metoda()");
			throw new CE2("CE2");
		}
		finally 
		{
			return 3;
		}
		
	}
}

class CE1 extends Exception 
{
	CE1(String s) 
	{
		super(s);
		System.out.println("CE1: " + s);
	}
}

class CE2 extends CE1 
{
	CE2(String s) 
	{
		super(s);
		System.out.println("CE2: " + s);
	}
}
