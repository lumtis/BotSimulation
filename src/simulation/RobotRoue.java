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
        // TODO LUCAAAAAAAAAAAS faut que tu écives Attendre
        allerChercherEau();
        //Attendre(10);
        this.volume =5000;
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
