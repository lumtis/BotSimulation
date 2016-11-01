


public enum NatureTerrain {EAU, FORET, ROCHE, TERRAIN_LIBRE, HABITAT}


public class Case
{
    private int ligne;
    private int colonne;
    private NatureTerrain nature;

    public Case()
    {
      this.ligne = 0;
      this.colonne = 0;
      this.nature = TERRAIN_LIBRE;
    }

    public Case(int ligne, int colonne, NatureTerrain nature)
    {
        this.ligne = ligne;
        this.colonne = colonne;
        this.nature = nature;
    }

    public int getLigne()
    {
        return ligne;
    }

    public int getColonne()
    {
        return colonne;
    }

    public NatureTerrain getNature()
    {
        return nature;
    }
}
