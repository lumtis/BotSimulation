package simulation;

import simulation.Case.NatureTerrain;

public class RobotPatte extends Robot {

    public RobotPatte(Case c, int vit) {
        super(c, vit);
        if (vit == -1) {
            this.setVitesse(30);
        }
    }

    public String getName() {
    	return "res/robotpatte.jpg";
    }
    
	@Override
	public double getVitesse(NatureTerrain n) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deverserEau(int vol) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remplirReservoir() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void allerChercherEau() {
		// TODO Auto-generated method stub
		
	}

    /*
    int getVitesse(NatureTerrain n){
        this.vitesse =30;
        switch(n){
            case EAU : return 0;
            case ROCHE return 10;
            default return this.vitesse;
        }
    }
    public void remplirReservoir () {
        return;
    }
    */

}
