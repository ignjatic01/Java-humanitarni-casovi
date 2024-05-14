package cas.io;

import java.io.*;

public class Serijalizacija
{
    public static void main(String[] args) {
        // Serijalizacija
        Objekat objekat = new Objekat("Primjer", 123);
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("objekat.ser"));
            out.writeObject(objekat);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserijalizacija
        Objekat deserijalizovaniObjekat = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("objekat.ser"));
            deserijalizovaniObjekat = (Objekat) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Deserijalizovani objekat:");
        System.out.println("Ime: " + deserijalizovaniObjekat.ime);
        System.out.println("Broj: " + deserijalizovaniObjekat.broj);
    }
}
