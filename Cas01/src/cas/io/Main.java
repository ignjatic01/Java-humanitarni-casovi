package cas.io;

import java.io.*;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            //Na ispitu koristiti samo relativne putanje

            BufferedReader br = new BufferedReader(new FileReader("broj.txt"));

            String ulaz = br.readLine();
            int broj = Integer.parseInt(ulaz);
            broj *= 2;

            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("broj.txt")));

            pw.println(broj);

            br.close();
            pw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
