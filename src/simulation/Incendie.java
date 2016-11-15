package simulation;
public class Incendie
{
	public enum EtatIncendie {LIBRE, OCCUPE, ETEINT}
    private Case position;
    private int intensiteDepard;
    private int intensite;
    private EtatIncendie e;

    public Incendie(Case p, int intensite)
    {
        this.position = p;
        this.intensite = intensite;
        this.intensiteDepard = intensite;
        e = EtatIncendie.LIBRE;
    }

    public Case getPosition()
    {
        return position;
    }

    public int getIntensite() {
        return this.intensite;
    }
    
    public int getIntensiteDepard() {
    	return intensiteDepard;
    }
    
    public void setIntensite(int i) {
    	intensite = i;
    }
    
    public EtatIncendie getEtat() {
    	return e;
    }
    
    public void setEtat(EtatIncendie e) {
    	this.e = e;
    }
}
