
import simulation.*;

import java.util.ArrayList;
import java.util.LinkedList;


import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

public class TestDijkstra {

    public static void main(String[] args) throws FileNotFoundException, DataFormatException {
        if (args.length < 1) {
            System.out.println("Syntaxe: java TestLecteurDonnees <nomDeFichier>");
            System.exit(1);
        }
        LinkedList<Case> chaine = new LinkedList<Case>();
        chaine.add(new Case());
        DonneesSimulation d =  new DonneesSimulation(args[0]);
        ArrayList<Carte.Direction> list = Utilitaire.dijkstra(d.getRobots(1),chaine,d).getPath();
        Object[] arr = list.toArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}
