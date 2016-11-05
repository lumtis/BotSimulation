
import gui.*;
import java.awt.*;
import java.util.*;

public class LancerSimulateur
{
    public static void main(String[] args)
    {
        // crée la fenêtre graphique dans laquelle dessiner
        GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
        // crée l'invader, en l'associant à la fenêtre graphique précédente
        Simulateur sim = new Simulateur(gui, args[0]);
    }
}


class Simulateur implements Simulable
{
    private DonneesSimulation data;
    private GUISimulator gui;

    public void Simulateur(GUISimulator gui, String name)
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
            break;
            case FORET:
                return "res/tree.png";
            break;
            case ROCHE:
                return "res/rock.png";
            break;
            case TERRAIN_LIBRE:
                return "res/grass.png";
            break;
            case HABITAT:
                return "res/house.png";
            break;
            default:
            break;
        }
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
