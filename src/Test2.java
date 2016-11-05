
import io.LecteurDonnees;

import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class Test2 {

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 1) {
            System.out.println("Syntaxe: java TestLecteurDonnees <nomDeFichier>");
            System.exit(1);
        }
        DonneesSimulation.testAccess();
        DonneesSimulation D =  new DonneesSimulation(args[0]);
        //LecteurDonnees.lire(args[0]);
    }

}
