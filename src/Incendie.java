

public class Incendie
{
    private Case position;
    private int intensite;


    public Incendie(Case p, int intensite)
    {
        this.position = p;
        this.intensite = intensite;
    }

    public Case getPosition()
    {
        return position;
    }
}
