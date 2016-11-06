public class Drone extends Robot {

    public Drone(Case c, int vit) {
        super(c, vit);
        if (vit == -1) {
            this.setVitesse(100);
        }
    }

    public int getVitesse(NatureTerrain n) {
        // non initialisé
        if (this.vitesse == -1 ) {
            this.vitesse=100;
        }
        // vitesse max
        else if (this.vitesse >= 150) {
            this.vitesse=150
        }
        //return vitesse
        return this.vitesse
    }
    public void remplirReservoir () {
        // TODO LUCAAAAAAAAAAAS faut que tu écives Attendre
        allerChercherEau();
        Attendre(30);
        this.volume =10000;
    }


}
