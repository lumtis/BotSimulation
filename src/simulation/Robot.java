package simulation;

import java.util.ArrayList;

import simulation.Carte.Direction;
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
    	position = c;
    }
    
    public int getVolume() {
    	return volume;
    }
    
    public void setVolume(int v) {
    	volume = v;
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
    	// Debug
    	System.out.print("Robot ");
    	System.out.print(this.getName());
    	System.out.print(" ");
    	System.out.println(e);
    	
    	this.e = e;
    }

    public void eteindreIncendie() {
    	if(this.volume == -1) {
    		deverserEau(this.target.getIntensite());
    	}
    	else if(this.volume > this.target.getIntensite()) {
    		deverserEau(this.target.getIntensite());
    	}
    	else {
    		deverserEau(this.volume);
    	}
    }
    
    public void allerChercherEau() {
    	ArrayList<Direction> path = Utilitaire.eauPlusProche(this, s.getData());
    	long delay = Utilitaire.delayCase(this, this.getPosition(), s.getData());

		// On creer un nouvelle evvenement pour le prochaine mouvement
		EvDeplacerEau nextMove = new EvDeplacerEau(s.getDate() + delay, this, path, s);
		s.ajouteEvenement(nextMove);
    }
    
    abstract public String getName();

    abstract public double getVitesse(Case.NatureTerrain n);

    abstract public void deverserEau(int vol);

    abstract public void remplirReservoir();
}
