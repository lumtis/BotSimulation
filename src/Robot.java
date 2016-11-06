

public abstract class Robot
{
    protected Case position;
    protected int volume;
    protected int vitesse;

    public Robot(Case c, int vit) {
        this.vitesse = vit;
        this.position = c;
    }


    protected void setVitesse(int vit) {
        this.vitesse = vit;
    }

    protected int getVitesse() {
        return this.vitesse;
    }

    protected Case getPosition()
    {
        return position;
    }

    protected void setPosition(Case c)
    {
        // TODO: Verifier si position valide
        position = c;
    }


    abstract protected double getVitesse(Case.NatureTerrain n);


    abstract protected void deverserEau(int vol);

    abstract protected void remplirReservoir();
    abstract protected void allerChercherEau();
}
