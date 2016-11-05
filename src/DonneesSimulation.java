import java.io.*;
import java.util.*;
import java.util.zip.DataFormatException;

public class DonneesSimulation {
    private Carte carte;
    /*
    private Incendie[] incendies;
    private Robot[] robots;
    */

    private static Scanner scanner;



    public DonneesSimulation(String name) throws FileNotFoundException {
        scanner = new Scanner(new File(name));
        scanner.useLocale(Locale.US);
        // Cartes
        ignorerCommentaires();
        int nbLignes = scanner.nextInt();
        int nbColonnes = scanner.nextInt();
        int tailleCase = scanner.nextInt();
        this.carte = new Carte(tailleCase, nbLignes, nbColonnes);
        System.out.println(tailleCase);
        System.out.println(nbLignes);
        System.out.println(nbColonnes);
        for (int lig = 0; lig < nbLignes; lig++) {
            for (int col = 0; col < nbColonnes; col++) {
                ignorerCommentaires();
                //Case nCase = new Case(lig, col, (Case.NatureTerrain) scanner.next());
                Case nCase = new Case(lig, col, Case.NatureTerrain.valueOf(scanner.next()));
                carte.setCase(lig, col, nCase);
                System.out.println(lig + " " + col);
            }
        }
        // Incendie
        ignorerCommentaires();






    }

      static public void testAccess(){
          System.out.println("DonneesSimulation est accessible");
      }



    private void ignorerCommentaires() {
        while(scanner.hasNext("#.*")) {
            scanner.nextLine();
        }
    }

    /**
     * Verifie qu'il n'y a plus rien a lire sur cette ligne (int ou float).
     * @throws ExceptionFormatDonnees
     */


    private void verifieLigneTerminee() throws DataFormatException {
        if (scanner.findInLine("(\\d+)") != null) {
            throw new DataFormatException("format invalide, donnees en trop.");
        }
    }

    public Carte getCarte()
    {
        return carte;
    }


}
