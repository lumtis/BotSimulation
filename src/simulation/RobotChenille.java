package simulation;

import simulation.Case.NatureTerrain;

public class RobotChenille extends Robot {

    public RobotChenille(Case c, int vit, Simulateur s) {
        super(c, vit, s);
        if (vit == -1) {
            this.setVitesse(60);
        }
    }
    
    public String getName() {
    	return "res/robotchenille.png";
    }

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
    
    
    public void remplirReservoir () {
        // TODO SLUCAAAAAAAAAAAS faut que tu écives Attendre
        allerChercherEau();
        //Attendre(5);
        this.volume =2000;
    }

	@Override
	public void deverserEau(int vol) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void allerChercherEau() {
		// TODO Auto-generated method stub
		
	}

}
