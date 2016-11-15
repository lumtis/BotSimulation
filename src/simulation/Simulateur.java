package simulation;
import java.awt.*;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.zip.DataFormatException;

import gui.GUISimulator;
import gui.ImageElement;
import gui.Simulable;


public class Simulateur implements Simulable {
    private DonneesSimulation data;
    private GUISimulator gui;
    private long dateSimulation;
    private ArrayList<Evenement> ev;
    private Chef chef;
    
    // MACRO
    public static final long PAS = 10;	// secondes
    public static final int FENETRELARGEUR = 800;
    public static final int FENETRELONGUEUR = 800;
    public static final String FIRENAME = "res/fire.gif";

    /**
     * \brief constructeur Simulateur
     * \param interface graphique
     * \param fichier
     */
    public Simulateur(GUISimulator gui, String name)
    {
        // Recupération des données
        try {
			data = new DonneesSimulation(name, this);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        dateSimulation = 0;
        ev = new ArrayList<Evenement>();
        chef = new Chef(data, this);
        this.gui = gui;
        gui.setSimulable(this);

        draw();
    }

    public DonneesSimulation getData() {
    	return data;
    }

    private String getImageName(Case.NatureTerrain nature) {
        switch(nature) {
            case EAU:
                return "res/water.png";
            case FORET:
                return "res/tree.png";
            case ROCHE:
                return "res/rock.png";
            case TERRAIN_LIBRE:
                return "res/grass.png";
            case HABITAT:
                return "res/house.png";
            default:
            break;
        }
		return null;
    }

    @Override
    public void next() {
    	int i;
    	Evenement tmp;

    	// On effectue toutles evenements antérieur à la date
    	if(ev.size() > 0) {
    		while(ev.get(0).getDate() <= this.getDate()) {
    			ev.get(0).execute();
    			ev.remove(0);
    			if(ev.size() == 0)
    				break;
    		}
    	}

    	for(i = 0; i< ev.size(); i++) {
    		tmp = ev.get(i);
    		if(tmp.getDate() == this.getDate()) {
    			tmp.execute();
    			ev.remove(i);
    			i--;
    		}
    	}

        draw();
    	incrementeDate();
    	
    	// On realise l'inspection des robots
    	if(dateSimulation % 10 == 0) {
    		chef.inspecter();
    	}
    }

    @Override
    public void restart() {
        draw();
    }

    /**
     * \brief dessine la carte
     */

    private void draw() {
        int i, j;
        float totalLargeur = data.getCarte().getTailleCases() * data.getCarte().getNbColonnes();
        float totalLongueur = data.getCarte().getTailleCases() * data.getCarte().getNbLignes();
        int realLargeur = (int)(((float)data.getCarte().getTailleCases()) * ((float)FENETRELARGEUR)/totalLargeur);
        int realLongueur = (int)(((float)data.getCarte().getTailleCases()) * ((float)FENETRELONGUEUR)/totalLongueur);

        gui.reset();	// clear the window

        // Affichage de la carte
        for(i=0; i<data.getCarte().getNbLignes(); i++) {
            for(j=0; j<data.getCarte().getNbColonnes(); j++) {
                gui.addGraphicalElement(new ImageElement( j * realLongueur,
                										  i * realLargeur,
                                                          getImageName(data.getCarte().getCase(i, j).getNature()),
                                                          realLargeur,
                                                          realLongueur,
                                                          null ));
            }
        }

        // Affichage des incendies
        for(i=0; i<data.getNbIncendies(); i++) {
        	if(data.getIncendies(i).getEtat() != Incendie.EtatIncendie.ETEINT) {
	        	gui.addGraphicalElement(new ImageElement( 	data.getIncendies(i).getPosition().getColonne() * realLargeur,
	        												data.getIncendies(i).getPosition().getLigne() * realLongueur,
	        												FIRENAME,
										                    realLargeur,
										                    realLongueur,
										                    null ));
        	}
        }

        // Affichage des robots
        for(i=0; i<data.getNbRobots(); i++) {
        	gui.addGraphicalElement(new ImageElement( 	data.getRobots(i).getPosition().getColonne() * realLargeur,
        												data.getRobots(i).getPosition().getLigne() * realLongueur,
        												data.getRobots(i).getName(),
									                    realLargeur,
									                    realLongueur,
									                    null ));


        }
    }


    /**
     * \brief ajoute un évèvenement à la liste
     * \param un évènement
     */
    public void ajouteEvenement(Evenement e) {
    	int i;

    	for(i = 0; i< ev.size(); i++) {
    		if(e.getDate() < ev.get(i).getDate()) {
    			ev.add(i, e);
    			return;
    		}
    	}

    	ev.add(e);
    }

    public void incrementeDate() {
    	dateSimulation++; // Une seconde de plus
    }

    public long getDate() {
    	return dateSimulation;
    }

    public boolean simulationTerminee() {
    	return false;
    }
}
