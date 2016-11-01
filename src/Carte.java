

public enum Direction {NORD, SUD, EST, OUEST}


public class Carte
{
    private int tailleCase;
    private int nbLignes;
    private int nbColonnes;
    private Case[][] matrice;

    public Carte(String fileName)
    {
        // Initialiser la carte à partir du fichier
    }

    public void Evenement(long date)
    {

    }

    public int getNbLignes()
    {
        return nbLignes;
    }

    public int getNbColonnes()
    {
        return nbColonnes;
    }

    public int getTailleCases()
    {
        return tailleCase;
    }

    public Case getCase(int lig, int col)
    {
        return matrice[lig][col];
    }

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
            case WEST:
                if(src.getColonne() == 0)
                    return false;
                break;
            default:
                break;
        }

        return true;
    }

    public Case getVoisin(Case src, Direction dir)
    {
        if(voisinExiste(src, dir))
        {
            switch(dir)
            {
                case NORD:
                    return this.getCase(src.getLigne()-1, src.getColonne());
                    break;
                case SUD:
                    return this.getCase(src.getLigne()+1, src.getColonne());
                    break;
                case EST:
                    return this.getCase(src.getLigne(), src.getColonne()+1);
                    break;
                case WEST:
                    return this.getCase(src.getLigne(), src.getColonne()-1);
                    break;
                default:
                    break;
            }
        }
        else
            return null;
    }
}
