package simulation;

public class Drone extends Robot
{
    public int getVitesse(Case.NatureTerrain n) {
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
        //Attendre(30);
        this.volume =10000;
    }
    
    
	protected void deverserEau(int vol) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void allerChercherEau() {
		// TODO Auto-generated method stub
		
	}

    }
}
