package simulation;

import java.util.ArrayList;

import simulation.Carte.Direction;
import simulation.Case.NatureTerrain;

public class Drone extends Robot {


    /**
     * \brief constructeur Drone
     * \param la Case
     * \param la vitesse
     */
	public Drone(Case c, int vit, Simulateur s) {
		super(c, vit, s);
        if (vit == -1) {
            this.setVitesse(100);
        }
        volume = 10000;
    }

    /**
     * \brief
     * \return nom du fichier
     */
    public String getName() {
    	return "res/drone.png";
    }


    /**
     * \brief
     * \return la vitesse
     * \param nature terrain
     */

    public double getVitesse(NatureTerrain n) {
        // non initialisé
        if (this.vitesse == -1 ) {
            this.vitesse=100;
        }
        // vitesse max
        else if (this.vitesse >= 150) {
            this.vitesse=150;
        }
        //return vitesse
        return this.vitesse;
    }


    /**
     * \brief rempli le reservoir du robot
     */
    public void remplirReservoir () {
    	EvRemplir event = new EvRemplir(s.getDate() + 1800, this, 10000);
		s.ajouteEvenement(event);
    }

    /**
     * \brief rempli le reservoir du robot
     */
	public void deverserEau(int vol) {



	public void deverserEau(int vol) {

		if(target != null) {
			EvEteindre event = new EvEteindre(s.getDate() + 30, 10000, this);
			s.ajouteEvenement(event);
		}
	}


	@Override

	public void allerChercherEau() {
	    ArrayList<Direction> path = Utilitaire.eauPlusProche(this, s.getData());
	    long delay = Utilitaire.delayCase(this, this.getPosition(), s.getData());

		// On creer un nouvelle evvenement pour le prochaine mouvement
		EvDeplacerEau nextMove = new EvDeplacerEau(s.getDate() + delay, this, path, s);
		s.ajouteEvenement(nextMove);
	}
}
