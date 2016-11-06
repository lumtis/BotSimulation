package simulation;
public abstract class Robot
{
    protected Case position;
    protected int volume;
    protected int vitesse;

    public Robot(Case c, int vit) {
        this.vitesse = vit;
        this.position = c;
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

    abstract public String getName();
    
    abstract public double getVitesse(Case.NatureTerrain n);

    abstract public void deverserEau(int vol);

    abstract public void remplirReservoir();
    abstract public void allerChercherEau();
}
