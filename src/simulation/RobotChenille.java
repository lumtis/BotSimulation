package simulation;

import simulation.Case.NatureTerrain;

public class RobotChenille extends Robot {

    /**
     * \brief constructeur robot Chenilel
     * \param sa case
     * \param sa vitesse
     * \param le Simulateur
     */
    public RobotChenille(Case c, int vit, Simulateur s) {
        super(c, vit, s);
        if (vit == -1) {
            this.setVitesse(60);
        }
        volume = 2000;
    }

    public String getName() {
    	return "res/robotchenille.png";
    }

    /**
     * \brief
     * \return la vitesse
     */
    public double getVitesse (NatureTerrain n) {
        if (this.vitesse == -1 ) {
            this.vitesse = 60;
        }
        else if ( this.vitesse > 80) {
            this.vitesse = 80;
        }
        switch(n)
        {
            case EAU :
            case ROCHE :
                return 0;
            case FORET :
                return (this.vitesse / 2);
            default:
                return this.vitesse;
        }
    }

    /**
     * \brief met à jour le reservoir
     */

    public void remplirReservoir () {
    	EvRemplir event = new EvRemplir(s.getDate() + 600, this, 5000);
		s.ajouteEvenement(event);
    }

    
    public void deverserEau(int vol) {	
    	Evenement event;
    	int i = 1;
    	
		if(target != null) {
			while(vol > 100) {
	    		event = new EvEteindre(s.getDate() + i*8, 100, this);
	    		s.ajouteEvenement(event);
	    		vol -= 100;
	    		i++;
	    	}
			
			event = new EvEteindre(s.getDate() + i*8, vol, this);
    		s.ajouteEvenement(event);
		}
	}
}
