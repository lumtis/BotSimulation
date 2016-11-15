package simulation;

import simulation.Case.NatureTerrain;

public class RobotPatte extends Robot {

    public RobotPatte(Case c, int vit, Simulateur s) {
        super(c, vit, s);
        if (vit == -1) {
            this.setVitesse(30);
        }
    }

    public String getName() {
    	return "res/robotpatte.jpg";
    }
    

	@Override
	public void deverserEau(int vol) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void allerChercherEau() {
		// TODO Auto-generated method stub
		
	}
    
    public double getVitesse(NatureTerrain n){
        this.vitesse =30;
        switch(n){
            case EAU: return 0;
            case ROCHE: return 10;
            default: return this.vitesse;
        }
    }
    
    public void remplirReservoir () {
        return;
    }
    

}
