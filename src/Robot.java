

public abstract class Robot
{
    protected Case position;
    protected int volume;
    protected int vitesse;

    protected Case getPosition()
    {
        return position;
    }

    protected void setPosition(Case c)
    {
        // TODO: Verifier si position valide
        position = c;
    }

    abstract protected double getVitesse(NatureTerrain n);


    abstract protected deverserEau(int vol);

    abstract protected void remplirReservoir();
    abstract protected void allerChercherEau();
}
