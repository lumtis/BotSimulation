
import io.LecteurDonnees;
import simulation.DonneesSimulation;

import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class Test2 {

    public static void main(String[] args) throws FileNotFoundException, DataFormatException {
        if (args.length < 1) {
            System.out.println("Syntaxe: java TestLecteurDonnees <nomDeFichier>");
            System.exit(1);
        }
        DonneesSimulation D =  new DonneesSimulation(args[0]);
        //LecteurDonnees.lire(args[0]);
        //System.out.println(D.getRobots()[2].getVitesse());
    }

}
