package simulation;

import simulation.Case.NatureTerrain;

public class Drone extends Robot {

    public Drone(Case c, int vit, Simulateur s) {
        super(c, vit, s);
        if (vit == -1) {
            this.setVitesse(100);
        }
    }

    public String getName() {
    	return "res/drone.png";
    }
    
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
    
    
    public void remplirReservoir () {
        allerChercherEau();
        //Attendre(30);
        this.volume =10000;
    }

	public void deverserEau(int vol) {
		if(target != null) {
			//EvEteindre ev = new EvEteindre(vol);
		}
	}
	
	@Override
	public void allerChercherEau() {

	}


}
