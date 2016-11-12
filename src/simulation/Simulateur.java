package simulation;
import gui.*;

import java.awt.*;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.zip.DataFormatException;


public class Simulateur implements Simulable
{
    private DonneesSimulation data;
    private GUISimulator gui;
    private long dateSimulation;
    private ArrayList<Evenement> ev;
    
    // MACRO
    public static final long PAS = 10;	// secondes
    public static final int FENETRELARGEUR = 800;
    public static final int FENETRELONGUEUR = 800;
    public static final String FIRENAME = "res/fire.gif";
    
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
        ev = new ArrayList<Evenement>();
        
        this.gui = gui;
        gui.setSimulable(this);

        
        
        System.out.println(gui.getParent().getComponentCount());
        
        
        
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
    	int i;
    	Evenement tmp;
    	
    	// On effectue toutles evenements liés à cette date
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
    }

    @Override
    public void restart() {
        draw();
    }

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
                gui.addGraphicalElement(new ImageElement( i * realLargeur,
                                                          j * realLongueur,
                                                          getImageName(data.getCarte().getCase(i, j).getNature()),
                                                          realLargeur,
                                                          realLongueur,
                                                          null ));
            }
        }
        
        // Affichage des incendies
        for(i=0; i<data.getNbIncendies(); i++) {
        	gui.addGraphicalElement(new ImageElement( 	data.getIncendies(i).getPosition().getLigne() * realLargeur,
        												data.getIncendies(i).getPosition().getColonne() * realLongueur,
        												FIRENAME,
									                    realLargeur,
									                    realLongueur,
									                    null ));
        }
        
        // Affichage des robots
        for(i=0; i<data.getNbRobots(); i++) {
        	gui.addGraphicalElement(new ImageElement( 	data.getRobots(i).getPosition().getLigne() * realLargeur,
        												data.getRobots(i).getPosition().getColonne() * realLongueur,
        												data.getRobots(i).getName(),
									                    realLargeur,
									                    realLongueur,
									                    null ));
        	
        
        }
    }
    
    // Ajouter un evenement
    public void ajouteEvenement(Evenement e) {
    	ev.add(e);
    }
    
    public void incrementeDate() {
    	
    }
    
    public long getDate() {
    	return dateSimulation;
    }
    
    public boolean simulationTerminee() {
    	return false;
    }
}
