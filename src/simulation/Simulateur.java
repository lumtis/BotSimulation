package simulation;
import gui.*;

import java.awt.*;


public class Simulateur implements Simulable
{
    private DonneesSimulation data;
    private GUISimulator gui;
    
    
    public Simulateur(GUISimulator gui, String name)
    {
        // Recupération des données
        data = new DonneesSimulation(name);

        this.gui = gui;
        gui.setSimulable(this);

        draw();
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
        draw();
    }

    @Override
    public void restart() {
        draw();
    }

    private void draw() {
        int i, j;

        gui.reset();	// clear the window

        for(i=0; i<data.getCarte().getNbLignes(); i++) {
            for(j=0; j<data.getCarte().getNbColonnes(); j++) {
                gui.addGraphicalElement(new ImageElement( i * data.getCarte().getTailleCases(),
                                                          j * data.getCarte().getTailleCases(),
                                                          getImageName(data.getCarte().getCase(i, j).getNature()),
                                                          data.getCarte().getTailleCases(),
                                                          data.getCarte().getTailleCases(),
                                                          null ));
            }
        }
    }
}
