package simulation;

public class Carte {
    public enum Direction {NORD, SUD, EST, OUEST}


    private int tailleCase;
    private int nbLignes;
    private int nbColonnes;
    private Case[][] matrice;



    /**
     * \brief constructeur de carte
     * \param taille des cases
     * \param nombre de lignes
     * \param nombre de colonnes
     */
    public Carte(int tailleCase, int nbLignes, int nbColonnes)
    {
        this.tailleCase = tailleCase;
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.matrice = new Case[nbLignes][nbColonnes];
    }

    public void Evenement(long date)
    {

    }


    /**
     * \brief
     * \return le nombre de ligne
     */
    public int getNbLignes()
    {
        return nbLignes;
    }




    /**
     * \brief
     * \return le nombre de colonnes
     */

     public int getNbColonnes()
    {
        return nbColonnes;
    }

    /**
     * \brief
     * \return la taille des cases de la carte
     */

    public int getTailleCases()
    {
        return tailleCase;
    }


    /**
     * \brief cherche la case par ligne et colonne
     * \return la case correspondante
     * \param la ligne
     * \param la colonne
     */

    public Case getCase(int lig, int col)
    {
        return matrice[lig][col];
    }

    /**
     * \brief constructeur case
     * \param la ligne
     * \param la colonne
     * \param la case
     */
    public void setCase(int lig, int col, Case nCase)
    {
        this.matrice[lig][col] = nCase;
    }


    /**
     * \brief Regarde si un voisin existe
     * \return true si voisin existe
     * \param case source
     * \param direction du voisin
     */
    public boolean voisinExiste(Case src, Direction dir)
    {
        switch(dir)
        {
            case NORD:
                if(src.getLigne() == 0)
                    return false;
                break;
            case SUD:
                if(src.getLigne() == this.getNbLignes()-1)
                    return false;
                break;
            case EST:
                if(src.getColonne() == this.getNbColonnes()-1)
                    return false;
                break;
            case OUEST:
                if(src.getColonne() == 0)
                    return false;
                break;
            default:
                break;
        }

        return true;
    }

    /**
     * \brief cherche le voisin
     * \return le voisin s'il existe, null si non
     * \param case source
     * \param direction du voisin
     */

    public Case getVoisin(Case src, Direction dir) {
        if(voisinExiste(src, dir)) {
            switch(dir) {
                case NORD:
                    return this.getCase(src.getLigne()-1, src.getColonne());
                case SUD:
                    return this.getCase(src.getLigne()+1, src.getColonne());
                case EST:
                    return this.getCase(src.getLigne(), src.getColonne()+1);
                case OUEST:
                    return this.getCase(src.getLigne(), src.getColonne()-1);
                default:
                    return this.getCase(src.getLigne(), src.getColonne()-1);
            }
        }
        else
            return null;
    }
}
