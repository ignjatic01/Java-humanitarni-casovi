import java.util.Random;
import java.util.Date;
public class Podmornica extends VojnaPlovila implements StitOdTorpeda, Sonar
{
	Torpedo torpedo;
	public Podmornica(int x)
	{
		super(x);
		this.pozicijaY = Simulacija.n-1;
		Random rand = new Random();
		torpedo = new Torpedo();
	}
	@Override 
	public void run()
	{
		this.startTime = new Date().getTime();
		for(;this.pozicijaY!=0;this.pozicijaY--){
			
				int counter = 1;
				if(Simulacija.pauza == true)
				{ 
					synchronized(this.ObjectToLock){
						try{
					this.ObjectToLock.wait();
						}catch(Exception e){}
					}
				}
					
				synchronized (Simulacija.matrica){
				while(this.pozicijaY-counter>=0 && counter<=3)
				{
					
					var polje = Simulacija.matrica[this.pozicijaX][this.pozicijaY-counter];
					VojnaPlovila vp = (VojnaPlovila)polje;
					if(polje!=null && polje instanceof StitOdTorpeda)
					{
						Simulacija.matrica[this.pozicijaX][this.pozicijaY] = null;
						this.isAlive = false;
						break;
					}else if(polje!=null && !(polje instanceof StitOdTorpeda))
					{
						System.out.println("Vojno plovilo ID:"+this.id+" je potopilo vojno plovilo ID:"+vp.id);
						vp.isAlive = false;
						Simulacija.matrica[this.pozicijaX][this.pozicijaY-counter]  = null;
						break;
					}
					counter++;
					
				}
				}
				if(!isAlive){
					System.out.println("Vojno Plovilo"+this.id+" je unisteno.");
					break;
				}
				
				synchronized(Simulacija.matrica){
						Simulacija.matrica[this.pozicijaX][this.pozicijaY] = null;
						if(this.pozicijaY-1>=0)
						Simulacija.matrica[this.pozicijaX][this.pozicijaY-1] = this;
						}
						try
						{
							Thread.sleep(1000);
						}catch(Exception e)
						{
							e.printStackTrace();
						}
				
					System.out.println("Vojno Plovilo ID:"+this.id+"nastavlja kretanje na poziciju ["+this.pozicijaX
					+"]["+this.pozicijaY+"]");
			
		}
		if(this.isAlive)
			System.out.println("Vojno plovilo "+this.id+" je doslo do kraja");
	}
}