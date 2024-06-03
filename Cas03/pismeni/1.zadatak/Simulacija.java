import java.util.*;
public class Simulacija
{	
	//n se unosi preko komandne linije, 3 kolone imamo
	public static int n = 0;
	public static boolean pauza = false;
	public static Object[][] matrica;
	public static void main(String [] args)
	{
		try{
			//n=30;
		n = Integer.parseInt(args[0]);
		if(n<20 || n>40)
			throw new Exception();
		}catch(Throwable e)
		{
			System.out.println("Invalid argument");
			System.exit(-1);
		}
		matrica = new Object[3][n];
		Random rand = new Random();
		int pozicijaRazaraca = rand.nextInt(3);
		int pozicijaNosaca;
		do{
			pozicijaNosaca = rand.nextInt(3);
		}while(pozicijaNosaca==pozicijaRazaraca);
		int pozicijaPrvePodmornice = rand.nextInt(3);
		int pozicijaDrugePodmornice;
		do{
		 pozicijaDrugePodmornice = rand.nextInt(3); 
		}while(pozicijaDrugePodmornice==pozicijaPrvePodmornice);
		
		ArrayList<VojnaPlovila> vpl = new ArrayList<>();
		RazaraciAviona ra = new RazaraciAviona(pozicijaRazaraca);
		NosaciAviona na = new NosaciAviona(pozicijaNosaca);
		Podmornica p1 = new Podmornica(pozicijaPrvePodmornice);
		Podmornica p2 = new Podmornica(pozicijaDrugePodmornice);
		vpl.add(ra);
		vpl.add(na);
		vpl.add(p1);
		vpl.add(p2);
		Scanner scan = new Scanner(System.in);
		for(VojnaPlovila vp : vpl)
		{
			vp.start();
		}
		String line ="";
		while(!line.equals("END"))
		{
			line = scan.nextLine();
			if("WAIT".equals(line))
			{
				pauza = true;
			}else if("NOTIFY".equals(line))
			{
				try{
				if(pauza == false)
				{
					throw new Exception("Nije bilo notify");
				}
				}catch(Exception  e)
				{
					System.out.println(e);
				}
				pauza = false;
				for(VojnaPlovila vp : vpl)
				{
					synchronized(vp.ObjectToLock)
					{
						vp.ObjectToLock.notify();
					}
					
				}
			}else if(line.startsWith("INFO"))
			{
				String[]niz = line.split(" ");
				int id = Integer.parseInt(niz[1]);
				boolean found = false;
				for(VojnaPlovila vp : vpl)
				{
					if(vp.id == id){
						System.out.println("Plovilo sa tim identifikatorom je na pozicijama "+vp.pozicijaX+" "+vp.pozicijaY);
						found = true;
					}
					
				}
				if(!found)
					System.out.println("Plovilo sa tim identifikatorom ne postoji");
			}
			else if(line.startsWith("TIME"))
			{
				String[]niz = line.split(" ");
				int id = Integer.parseInt(niz[1]);
				boolean found = false;
				for(VojnaPlovila vp : vpl)
				{
					if(vp.id == id){
						System.out.println("Vrijeme kretanja plovila "+(new Date().getTime() - vp.startTime)/1000+"s");
						found = true;
					}
					
				}
				if(!found)
					System.out.println("Plovilo sa tim identifikatorom ne postoji");
			}
		}
		
		for(VojnaPlovila vp:vpl)
		{
			try{
			vp.join();
			}catch(Exception e)
			{
				
			}
		}
		
	}
}