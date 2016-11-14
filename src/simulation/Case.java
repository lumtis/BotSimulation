package simulation;

public class Case {
    public enum NatureTerrain {EAU, FORET, ROCHE, TERRAIN_LIBRE, HABITAT}

    private int ligne;
    private int colonne;
    private NatureTerrain nature;

    /**
     * \brief compare deux cases
     * \return true si le cases sont égales, false si non
     * \param case 1
     * \param case 2
     */
    public static boolean casesEgales(Case c1, Case c2) {
    	return ((c1.ligne == c2.ligne) && (c1.colonne == c2.colonne));
    }

    /**
     * \brief constructeur case
     */
    public Case()
    {
      this.ligne = 0;
      this.colonne = 0;
      this.nature = NatureTerrain.TERRAIN_LIBRE;
    }


    /**
     * \brief constructeur case
     * \param ligne de la case
     * \param colonne de la case
     * \param nature de la case
     */
    public Case(int ligne, int colonne, NatureTerrain nature)
    {
        this.ligne = ligne;
        this.colonne = colonne;
        this.nature = nature;
    }


    /**
     * \brief
     * \ return la ligne de la cae
     */
    public int getLigne()
    {
        return ligne;
    }

    /**
     * \brief
     * \ return la colonne de la case
     */
    public int getColonne()
    {
        return colonne;
    }



    /**
     * \brief
     * \ return la nature du terrain
     */
    public NatureTerrain getNature()
    {
        return nature;
    }
}
