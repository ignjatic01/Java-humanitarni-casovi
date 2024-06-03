import java.util.Date;
public class NosaciAviona extends VojnaPlovila implements RaketniStit,StitOdTorpeda, Radar
{
	public NosaciAviona(int x)
	{
		super(x);
		this.pozicijaY = 0;
	}
	@Override
	public void run()
	{
		this.startTime = new Date().getTime();
		for(;this.pozicijaY<Simulacija.n;this.pozicijaY++){
			if(Simulacija.pauza == true)
				{
					synchronized(this.ObjectToLock){
					try{
					this.ObjectToLock.wait();
						}catch(Exception e){}
					}
				}
			if(!this.isAlive){
				break;
			}
			synchronized(Simulacija.matrica)
			{
				Simulacija.matrica[this.pozicijaX][this.pozicijaY] = null;
				if(this.pozicijaY+1<Simulacija.n)
				Simulacija.matrica[this.pozicijaX][this.pozicijaY+1] = this;
			}
			System.out.println("Vojno Plovilo ID:"+this.id+"nastavlja kretanje na poziciju ["+this.pozicijaX
					+"]["+this.pozicijaY+"]");
					    try
						{
							Thread.sleep(1000);
						}catch(Exception e)
						{
							e.printStackTrace();
						}
		}
		if(this.isAlive)
			System.out.println("Vojno plovilo "+this.id+" je doslo do kraja");				
		}
	}
