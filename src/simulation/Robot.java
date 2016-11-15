package simulation;

import simulation.Incendie.EtatIncendie;

public abstract class Robot
{
	public enum EtatRobot {RIEN, DEPLACERINCENDIE, ETEINDRE, VIDE, DEPLACEREAU, REMPLIR}
	protected EtatRobot e;
    protected Case position;
    protected int volume;
    protected int vitesse;
    protected Incendie target;
    protected Simulateur s;

    public Robot(Case c, int vit, Simulateur s) {
        this.vitesse = vit;
        this.position = c;
        e = EtatRobot.RIEN;
        target = null;
        this.s = s;
    }

    public void setTarget(Incendie i) {
    	target = i;
    }
    
    public Incendie getTarget() {
    	return target;
    }

    public void setVitesse(int vit) {
        this.vitesse = vit;
    }

    public int getVitesse() {
        return this.vitesse;
    }

    public Case getPosition()
    {
        return position;
    }

    public void setPosition(Case c)
    {
        // TODO: Verifier si position valide
        position = c;
    }
    
    public EtatRobot getEtat() {
    	return e;
    }
    
    public void setEtat(EtatRobot e) {
    	this.e = e;
    }

    abstract public String getName();
    
    abstract public double getVitesse(Case.NatureTerrain n);

    abstract public void deverserEau(int vol);

    abstract public void remplirReservoir();
    abstract public void allerChercherEau();
}
