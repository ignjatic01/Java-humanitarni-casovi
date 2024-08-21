// G1.java
import java.io.*;

public class G1 {

	public static void main(String[] args) throws Exception {
		G3 g1 = new G3();
		G2 g2 = new G2(g1);
		try (ObjectOutputStream o = new ObjectOutputStream(
				new FileOutputStream("data.bin"))) {
			o.writeObject(g1);
			o.writeObject(g2);
		} finally {
			System.out.println("Data saved");
		}
		try (ObjectInputStream in = new ObjectInputStream(
				new FileInputStream("data.bin"))) {
			g1 = (G3) in.readObject();
			g2 = (G2) in.readObject();
		} finally {
			System.out.println("Data loaded");
		}
		System.out.println(g2.g3.i);
	}
}


class G2 implements Serializable {

	G3 g3;
	
	public G2() {
		System.out.println("G2()");
	}
	
	public G2(G3 g3) {
		if (g3 != null)
			this.g3 = g3;
		System.out.println("G2(G3)");
	}
	
	public void writeExternal(ObjectOutput out)
		throws IOException {
		System.out.println("G2.writeExternal");
	}
	public void readExternal(ObjectInput in)
		throws IOException, ClassNotFoundException {
		System.out.println("G2.readExternal");
		g3 = new G3();
	}
}


class G3 extends G2 implements Externalizable {

	int i = 0xaaa;
	
	public G3() {
		super();
		System.out.println("G3()");
	}
	
	public void writeExternal(ObjectOutput out)
		throws IOException {
		super.writeExternal(out);
		System.out.println("G3.writeExternal");
	}
	
	public void readExternal(ObjectInput in)
		throws IOException, ClassNotFoundException {
		super.readExternal(in);
		System.out.println("G3.readExternal");
	}
}

