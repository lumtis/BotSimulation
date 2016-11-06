import java.io.*;
import java.util.*;
import java.util.zip.DataFormatException;

public class DonneesSimulation {
    private Carte carte;
    private Incendie[] incendies;
    private Robot[] robots;

    private static Scanner scanner;



    public DonneesSimulation(String name) throws FileNotFoundException, DataFormatException {
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
                //Case nCase = new Case(lig, col, (Case.NatureTerrain) scanner.next());
                Case nCase = new Case(lig, col, Case.NatureTerrain.valueOf(scanner.next()));
                this.carte.setCase(lig, col, nCase);
                verifieLigneTerminee();
                /* Test
                System.out.println(lig + " " + col);
                System.out.println (this.carte.getCase(lig,col).getNature());
                */
            }
        }
        // Incendie
        ignorerCommentaires();
        int nbIncendies = scanner.nextInt();
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

        /* Test
        System.out.println(incendies[4].getIntensite());
        */
        // Robots
        ignorerCommentaires();
        int nbRobots = scanner.nextInt();
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
                    rob = new Drone(this.carte.getCase(lig,col),vit);
                    break;
                case "CHENILLES":
                    rob = new RobotChenille(this.carte.getCase(lig,col),vit);
                    break;
                case "PATTES":
                    rob = new RobotPatte(this.carte.getCase(lig,col),vit);
                    break;
                case "ROUES":
                    rob = new RobotRoue(this.carte.getCase(lig,col),vit);
                    break;
                default:
                    throw new DataFormatException("DataFormatException");
            }
            robots[i] = rob;

        }
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

    public Incendie[] getIncendies()
    {
        return incendies;
    }

    public Robot[] getRobots()
    {
        return robots;
    }


}
