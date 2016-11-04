public class DonneesSimulation {

    private Carte carte;
    private Incendie[] incendies;
    private Robot[] robots;


    public DonneesSimulation(String name){
        /* Cartes */
        ignorerCommentaires();
        int tailleCase = scanner.nextInt();
        int nbLignes = scanner.nextInt();
        int nbColonnes = scanner.nextInt();
        this.carte = new Carte(tailleCase, nbLignes, nbColonnes);
        for (int lig = 0; lig < nbLignes; lig++) {
            for (int col = 0; col < nbColonnes; col++) {
                ignorerCommentaires();
                nCase = new Case(lig, col, (Case.NatureTerrain) scanner.next());
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

    public Carte getCarte()
    {
        return carte;
    }


}
