package simulation;
public class Incendie
{
	public enum EtatIncendie {LIBRE, OCCUPE, ETEINT}
    private Case position;
    private int intensiteDepard;
    private int intensite;
    private EtatIncendie e;
	/**
     * \brief constructeur incendie
	 * \param la case
	 * \param l'intensité
     */
    public Incendie(Case p, int intensite)
    {
        this.position = p;
        this.intensite = intensite;
        this.intensiteDepard = intensite;
        e = EtatIncendie.LIBRE;
    }

	/**
     * \brief
     * \return la position de l'incendie
     */
    public Case getPosition()
    {
        return this.position;
    }

	/**
     * \brief
     * \return la position de l'incendie
     */
    public int getIntensite() {
        return this.intensite;
    }

    public int getIntensiteDepard() {
    	return intensiteDepard;
    }

	/**
     * \brief initialise l'intensité
     */
	 public void setIntensite(int i) {
    	intensite = i;
    }
	/**
     * \brief
	 / \ return l'état de l'incendie
     */
    public EtatIncendie getEtat() {
    	return e;
    }
	/**
     * \brief initialise l'états
     */
    public void setEtat(EtatIncendie e) {
    	this.e = e;
    }
}
