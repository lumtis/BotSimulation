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

	/**
     * \brief constructeur robots
     * \param sa case
     * \param sa vitesse
     * \param le Simulateur
     */
    public Robot(Case c, int vit, Simulateur s) {
        this.vitesse = vit;
        this.position = c;
        e = EtatRobot.RIEN;
        target = null;
        this.s = s;
    }
	/**
     * \briefmet à jour la Direction
     * \param l'incendie
     */
    public void setTarget(Incendie i) {
    	target = i;
    }
    /**
     * \brief
     * \return l'incendie affectée au robot
     */
    public Incendie getTarget() {
    	return target;
    }

	/**
     * \brief met à jour la vitesse
     * \param la vitesse
     */

    public void setVitesse(int vit) {
        this.vitesse = vit;
    }

	/**
     * \brief
     * \return la vitesse
     */
    public int getVitesse() {
        return this.vitesse;
    }
	/**
     * \brief
     * \return la position
     */
    public Case getPosition()
    {
        return position;
    }
	/**
     * \brief met à jour la position
     * \param la case
     */
    public void setPosition(Case c)
    {
		sim = this.Simulateur;
		data = sim.getData;
		carte= data.getCarte;
		ligne= carte.getLigne;
		colonne = carte.getColonne;
		// on vérifie si la case est dans carte
        if (0<= c.ligne<ligne && 0 <=c.colonne<colonne) {
        	position = c;
        }
        else {
			System.out.println("case en dehors de la carte")
		}
    }

	/**
     * \brief
     * \return l'état du robot
     */
    public EtatRobot getEtat() {
    	return e;
    }

	/**
     * \brief met à jour l'état du robot
     * \param le nouvel état
     */
    public void setEtat(EtatRobot e) {
    	this.e = e;
    }

    abstract public String getName();

    abstract public double getVitesse(Case.NatureTerrain n);

    abstract public void deverserEau(int vol);

    abstract public void remplirReservoir();
    abstract public void allerChercherEau();
}
