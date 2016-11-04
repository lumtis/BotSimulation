public class RobotPatte extends Robot {
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
    
}
