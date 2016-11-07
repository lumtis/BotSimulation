package simulation;
import gui.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.zip.DataFormatException;


public class Simulateur implements Simulable
{
    private DonneesSimulation data;
    private GUISimulator gui;
    private long dateSimulation;
    public static final int fenetreLargeur = 800;
    public static final int fenetreLongueur = 800;
    public static final String fireName = "res/fire.gif";
    
    public Simulateur(GUISimulator gui, String name)
    {
        // Recupération des données
        try {
			data = new DonneesSimulation(name);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DataFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        dateSimulation = 0;
        this.gui = gui;
        gui.setSimulable(this);

        draw();
    }


    private String getImageName(Case.NatureTerrain nature) {
        switch(nature) {
            case EAU:
                return "res/water.gif";
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
        draw();
    }

    @Override
    public void restart() {
        draw();
    }

    private void draw() {
        int i, j;
        float totalLargeur = data.getCarte().getTailleCases() * data.getCarte().getNbColonnes();
        float totalLongueur = data.getCarte().getTailleCases() * data.getCarte().getNbLignes();
        int realLargeur = (int)(((float)data.getCarte().getTailleCases()) * ((float)fenetreLargeur)/totalLargeur);
        int realLongueur = (int)(((float)data.getCarte().getTailleCases()) * ((float)fenetreLongueur)/totalLongueur);
        
        gui.reset();	// clear the window
        
        // Affichage de la carte
        for(i=0; i<data.getCarte().getNbLignes(); i++) {
            for(j=0; j<data.getCarte().getNbColonnes(); j++) {
                gui.addGraphicalElement(new ImageElement( i * realLargeur,
                                                          j * realLongueur,
                                                          getImageName(data.getCarte().getCase(i, j).getNature()),
                                                          realLargeur,
                                                          realLongueur,
                                                          null ));
            }
        }
        
        // Affichage des incendies
        Incendie[] incendies = data.getIncendies();
        for(i=0; i<Array.getLength(incendies); i++) {
        	gui.addGraphicalElement(new ImageElement( 	incendies[i].getPosition().getLigne() * realLargeur,
        												incendies[i].getPosition().getColonne() * realLongueur,
									                    fireName,
									                    realLargeur,
									                    realLongueur,
									                    null ));
        }
        
        // Affichage des robots
        Robot[] robots = data.getRobots();
        for(i=0; i<Array.getLength(robots); i++) {
        	gui.addGraphicalElement(new ImageElement( 	robots[i].getPosition().getLigne() * realLargeur,
        												robots[i].getPosition().getColonne() * realLongueur,
									                    robots[i].getName(),
									                    realLargeur,
									                    realLongueur,
									                    null ));
        	
        
        }
    }
    
    // Ajouter un evenement
    public void ajouteEvenement(Evenement e) {
    	
    }
    
    public void incrementeDate() {
    	dateSimulation++;
    }
    
    public boolean simulationTerminee() {
    	return false;
    }
}
