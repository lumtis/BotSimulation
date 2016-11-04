public class RobotRoue extends Robot {
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
