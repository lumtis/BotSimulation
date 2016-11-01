

public abstract class Robot
{
    protected Case position;
    protected int volume;

    protected Case getPosition()
    {
        return position;
    }

    protected void setPosition(Case c)
    {
        // TODO: Verifier si position valide
        position = c;
    }

    protected double getVitesse(NatureTerrain n)
    {

    }

    protected deverserEau(int vol)
    {

    }

    protected void remplirReservoir()
    {

    }
}
