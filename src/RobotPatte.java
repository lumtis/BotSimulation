public class RobotPatte extends Robot {

    public RobotPatte(Case c, int vit) {
        super(c, vit);
        if (vit == -1) {
            this.setVitesse(30);
        }
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
