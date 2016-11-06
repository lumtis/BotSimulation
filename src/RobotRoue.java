public class RobotRoue extends Robot {

    public RobotRoue(Case c, int vit) {
        super(c, vit);
        if (vit == -1) {
            this.setVitesse(80);
        }
    }



    int getVitesse (NatureTerrain n) {
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
    public void remplirReservoir () {
        // TODO LUCAAAAAAAAAAAS faut que tu écives Attendre
        allerChercherEau();
        Attendre(10);
        this.volume =5000;
    }

}
