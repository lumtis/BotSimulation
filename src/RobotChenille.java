public class RobotChenille extends Robot {
    int getVitesse (NatureTerrain n) {
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
}
