package simulation;

import simulation.Case.NatureTerrain;

public class RobotPatte extends Robot {
    /**
     * \brief constructeur robot patte
     * \param sa case
     * \param sa vitesse
     * \param le Simulateur
     */
    public RobotPatte(Case c, int vit, Simulateur s) {
        super(c, vit, s);
        if (vit == -1) {
            this.setVitesse(30);
        }
        volume = -1;	// Infini
    }

    public String getName() {
    	return "res/robotpatte.jpg";
    }


    public void deverserEau(int vol) {	
    	Evenement event;
    	int i = 1;
    	
		if(target != null) {
			while(vol > 10) {
	    		event = new EvEteindre(s.getDate() + i, 10, this);
	    		s.ajouteEvenement(event);
	    		vol -= 10;
	    		i++;
	    	}
			
			event = new EvEteindre(s.getDate() + i, vol, this);
    		s.ajouteEvenement(event);
		}
    }
	
		
    /**
     * \brief
     * \return vitesse
     */
    public double getVitesse(NatureTerrain n){
        this.vitesse =30;
        switch(n){
            case EAU: return 0;
            case ROCHE: return 10;
            default: return this.vitesse;
        }
    }

    public void remplirReservoir () {
    	
    }


}
