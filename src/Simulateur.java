

import java.awt.image.ImageObserver;


public class LancerSimulateur
{
    public static void main(String[] args)
    {
        // crée la fenêtre graphique dans laquelle dessiner
        GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
        // crée l'invader, en l'associant à la fenêtre graphique précédente
        Simulateur sim = new Simulateur(gui, args[1]);
    }
}


public class Simulateur implements Simulable
{
    private DonneesSimulation data;
    private enum NameTerrain {"res/water.png", "res/tree.png", "res/rock.png", "res/grass.png", "res/house.png"}


    public void Simulateur(GUISimulator gui, String name)
    {
        this.gui = gui;
        gui.setSimulable(this);

        // Recupération des données
        data = new DonneesSimulation(name);

        planCoordinates();
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

    private void planCoordinates() {
        // panel must be large enough... unchecked here!
        // total invader size: height == 120, width == 80
        int xMin = 60;
        int yMin = 40;
        int xMax = gui.getWidth() - xMin - 80;
        xMax -= xMax % 10;
        int yMax = gui.getHeight() - yMin - 120;
        yMax -= yMax % 10;

        // let's plan the invader displacement!
        List<Integer> xCoords = new ArrayList<Integer>();
        List<Integer> yCoords = new ArrayList<Integer>();
        // going right
        for (int x = xMin + 10; x <= xMax; x += 10) {
            xCoords.add(x);
            yCoords.add(yMin);
        }
        // going down
        for (int y = yMin + 10; y <= xMin + 150; y += 10) {
            xCoords.add(xMax);
            yCoords.add(y);
        }
        // going left
        for (int x = xMax - 10; x >= xMin; x -= 10) {
            xCoords.add(x);
            yCoords.add(yMin + 170);
        }

        this.xIterator = xCoords.iterator();
        this.yIterator = yCoords.iterator();
        // current position
        this.x = xMin;
        this.y = yMin;
    }

    @Override
    public void next() {
        if (this.xIterator.hasNext())
            this.x = this.xIterator.next();
        if (this.yIterator.hasNext())
            this.y = this.yIterator.next();
        draw();
    }

    @Override
    public void restart() {
        planCoordinates();
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
                                                          new ImageObserver));
            }
        }
    }
}
