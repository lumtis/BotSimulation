package simulation;
import java.util.Scanner;
import java.util.zip.DataFormatException;

import io.LecteurDonnees;
import simulation.Case.NatureTerrain;

public class DonneesSimulation {

    private Carte carte;
    private Incendie[] incendies;
    private Robot[] robots;


    public DonneesSimulation(String name){
        /* Cartes */
    	Scanner sc = new Scanner(System.in);
    	
        ignorerCommentaires();
        int nbLignes = sc.nextInt();
        int nbColonnes = sc.nextInt();
        int tailleCase = sc.nextInt();
        this.carte = new Carte(tailleCase, nbLignes, nbColonnes);
        for (int lig = 0; lig < nbLignes; lig++) {
            for (int col = 0; col < nbColonnes; col++) {
                ignorerCommentaires();
                Case nCase = new Case(lig, col, getNature(sc.next()));
                carte.setCase(lig, col, nCase);
            }
        }
        
        /* Incendies
        ignorerCommentaires();
        int nbIncendies = scanner.nextInt();
        this.incendies = new Incendie[nbIncendies];
        */

        /* Incendie
        for (int k = 0; k < nbIncendies; k++) {
            ignorerCommentaires();
            int lig = scanner.nextInt();
            int col = scanner.nextInt();
            int intensite = scanner.nextInt();
            Incendie incendie = new Incendie(this.carte.getCase(lig,col),intensite);
            incendies[k] = incendie;
        }
        */


        /* Robots
        ignorerCommentaires();
        int nbRobots = scanner.nextInt();
        this.robots = new Robot[nbIncendies];
        for (int k = 0; k < nbIncendies; k++) {
            ignorerCommentaires();
            int lig = scanner.nextInt();
            int col = scanner.nextInt();
            NatureTerrain =
            int intensite = scanner.nextInt();
            Incendie incendie = new Incendie(this.carte.getCase(lig,col),intensite);
            Incendies[k] = incendie;
        }
        */


    }

    private Case.NatureTerrain getNature(String s) {
    	switch(s) {
    	case "EAU":
    		return NatureTerrain.EAU;
    	case "FORET": 
    		return NatureTerrain.FORET;
    	case "ROCHE":
    		return NatureTerrain.ROCHE;
    	case "TERRAIN_LIBRE":
    		return NatureTerrain.TERRAIN_LIBRE;
    	case "HABITAT":
    		return NatureTerrain.HABITAT;
    	default:
    		return NatureTerrain.TERRAIN_LIBRE;
    	}
    }

    private void ignorerCommentaires() {
		Scanner sc = new Scanner(System.in);
    	while(sc.hasNext("#.*")) {
        	sc.nextLine();
        }
    }

    /**
     * Verifie qu'il n'y a plus rien a lire sur cette ligne (int ou float).
     * @throws ExceptionFormatDonnees
     */
    private void verifieLigneTerminee() throws DataFormatException {
    	Scanner sc = new Scanner(System.in);
    	if (sc.findInLine("(\\d+)") != null) {
            throw new DataFormatException("format invalide, donnees en trop.");
        }
    }

    public Carte getCarte()
    {
        return carte;
    }


}
