package simulation;
import java.util.Scanner;
import java.util.zip.DataFormatException;

import simulation.Carte.Direction;

import java.io.*;
import java.util.*;

public class DonneesSimulation {
    private Carte carte;
    private int nbIncendies;
    private Incendie[] incendies;
    private int nbRobots;
    private Robot[] robots;

    private static Scanner scanner;

    /**
     * \brief Analyse le fichier et rempli les objets
     * \param nom de fichier
	*/
    public DonneesSimulation(String name, Simulateur sim) throws FileNotFoundException, DataFormatException {
        scanner = new Scanner(new File(name));
        scanner.useLocale(Locale.US);
        // Cartes
        ignorerCommentaires();
        int nbLignes = scanner.nextInt();
        int nbColonnes = scanner.nextInt();
        int tailleCase = scanner.nextInt();
        verifieLigneTerminee();

        this.carte = new Carte(tailleCase, nbLignes, nbColonnes);
        for (int lig = 0; lig < nbLignes; lig++) {
            for (int col = 0; col < nbColonnes; col++) {
                ignorerCommentaires();
                Case nCase = new Case(lig, col, Case.NatureTerrain.valueOf(scanner.next()));
                this.carte.setCase(lig, col, nCase);
                verifieLigneTerminee();

            }
        }

        // Incendie
        ignorerCommentaires();
        nbIncendies = scanner.nextInt();
        this.incendies = new Incendie[nbIncendies];
        verifieLigneTerminee();
        for (int i = 0 ; i < nbIncendies; i++) {
            ignorerCommentaires();
            int lig = scanner.nextInt();
            int col = scanner.nextInt();
            int inte = scanner.nextInt();
            verifieLigneTerminee();
            incendies[i] = new Incendie(this.carte.getCase(lig,col),inte);
        }

        // Robots

        ignorerCommentaires();
        nbRobots = scanner.nextInt();
        verifieLigneTerminee();
        this.robots = new Robot[nbRobots];
        for (int i = 0 ; i < nbRobots; i++) {
            ignorerCommentaires();
            int lig = scanner.nextInt();
            int col = scanner.nextInt();
            String type = scanner.next();
            String s = scanner.findInLine("(\\d+)");
            int vit;
            if ( s == null) {
                vit = -1;
            } else {
                vit = Integer.parseInt(s);
            }
            verifieLigneTerminee();
            Robot rob;
            switch (type) {
                case "DRONE":
                    rob = new Drone(this.carte.getCase(lig,col),vit, sim);
                    break;
                case "CHENILLES":
                    rob = new RobotChenille(this.carte.getCase(lig,col),vit, sim);
                    break;
                case "PATTES":
                    rob = new RobotPatte(this.carte.getCase(lig,col),vit, sim);
                    break;
                case "ROUES":
                    rob = new RobotRoue(this.carte.getCase(lig,col),vit, sim);
                    break;
                default:
                    throw new DataFormatException("DataFormatException");
            }
            robots[i] = rob;
        }
    }

    /**
     * \brief ignore les commentaires dans le fichier
     */
    private void ignorerCommentaires() {
    	while(scanner.hasNext("#.*")) {
    		scanner.nextLine();
        }
    }

    /**
     * \brief Verifie qu'il n'y a plus rien a lire sur cette ligne (int ou float).
     * @throws ExceptionFormatDonnees
     */


    private void verifieLigneTerminee() throws DataFormatException {
    	if (scanner.findInLine("(\\d+)") != null) {
            throw new DataFormatException("format invalide, donnees en trop.");
        }
    }

    /**
     * \brief
     * \return la carte
     */
    public Carte getCarte()
    {
        return carte;
    }

    /**
     * \brief
     * \return le nombre d'incendies
     */

    public int getNbIncendies() {
    	return nbIncendies;
    }


    /**
     * \brief
     * \return le n ème Incendie
     * \param n
     */

    public Incendie getIncendies(int n)
    {
        return incendies[n];
    }
    /**
     * \brief
     * \return le nombre d'incendies
     */

    public int getNbRobots() {
    	return nbRobots;
    }
    /**
     * \brief
     * \return le n ème robot
     * \ param n
     */
    public Robot getRobots(int n)
    {
        return robots[n];
    }


    public boolean voisinExiste(Case src, Direction dir) {
        return carte.voisinExiste(src, dir);
    }

    /**
     * \cherche le voisin
     * \return le voisin s'il existe, null si non
     */
    public Case getVoisin(Case src, Direction dir) {
    	return carte.getVoisin(src, dir);
    }
}
