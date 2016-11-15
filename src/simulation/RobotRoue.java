package simulation;

import simulation.Case.NatureTerrain;

public class RobotRoue extends Robot {
    /**
     * \brief constructeur robot ROUES
     * \param sa case
     * \param sa vitesse
     * \param le Simulateur
     */
    public RobotRoue(Case c, int vit, Simulateur s) {
        super(c, vit, s);
        if (vit == -1) {
            this.setVitesse(80);
        }
        volume = 5000;
    }

    public String getName() {
    	return "res/robotroue.png";
    }

    /**
     * \brief
     * \return vitesse
     */
    public double getVitesse (NatureTerrain n) {
        switch(n){
            case TERRAIN_LIBRE :
            case HABITAT:
                if(this.vitesse == -1) {
                    this.vitesse = 80;
                }
                return this.vitesse;
            default : return 0;
        }
    }

    
    /**
     * \brief met à jour le reservoir
     */
    public void remplirReservoir () {
    	EvRemplir event = new EvRemplir(s.getDate() + 300, this, 2000);
		s.ajouteEvenement(event);
    }

    public void deverserEau(int vol) {	
    	Evenement event;
    	int i = 1;
    	
		if(target != null) {
			while(vol > 100) {
	    		event = new EvEteindre(s.getDate() + i*5, 100, this);
	    		s.ajouteEvenement(event);
	    		vol -= 100;
	    		i++;
	    	}
			
			event = new EvEteindre(s.getDate() + i*5, vol, this);
    		s.ajouteEvenement(event);
		}
	}

}
