package simulation;

import java.util.ArrayList;
import java.util.LinkedList;

import simulation.Carte.Direction;
import simulation.Case.NatureTerrain;

public class Utilitaire {
	public static class PairDijkstra {
	    private ArrayList<Direction> path;
	    private double cout;

	    public PairDijkstra(ArrayList<Direction> first, double second) {
	        this.path = first;
	        this.cout = second;
	    }

	    public ArrayList<Direction> getPath() {
	        return path;
	    }

	    public double getCout() {
	        return cout;
	    }
	}
	
	
	public static long metreSeconde(long v) {
		return (long) (v/3.6);
	}
	
	public static long delayCase(Robot r, Case c, DonneesSimulation d) {
		long vit = Utilitaire.metreSeconde((long) r.getVitesse(c.getNature()));
		long dist = d.getCarte().getTailleCases();
		long delay = dist/vit;
		
		return delay;
	}
	
	
	public static boolean isEau(Case c, DonneesSimulation d) {
		
		return (c.getNature() == NatureTerrain.EAU);
	}
	
	public static boolean isIncendie(Case c, DonneesSimulation d) {
		int i;
		
		for(i=0; i<d.getNbIncendies(); i++) {
			if(Case.casesEgales(d.getIncendies(i).getPosition(), c) && d.getIncendies(i).getEtat() != Incendie.EtatIncendie.ETEINT) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean isRobot(Case c, DonneesSimulation d) {
	int i;
		
		for(i=0; i<d.getNbRobots(); i++) {
			if(Case.casesEgales(d.getRobots(i).getPosition(), c)) {
				return true;
			}
		}
		
		return false;
	}
	
	
	public static boolean hasVoisinIncendie(Case c, DonneesSimulation d) {
		for (Direction dir : Direction.values()) {
			if(d.voisinExiste(c, dir))
			{
				if(isIncendie(d.getVoisin(c, dir), d))
					return true;
			}
		}
		
		return false;
	}
	
	
	public static boolean hasVoisinEau(Case c, DonneesSimulation d) {
		for (Direction dir : Direction.values()) {
			if(d.voisinExiste(c, dir))
			{
				if(isEau(d.getVoisin(c, dir), d))
					return true;
			}
		}
		return false;
	}
	
	
	public static Case getDestination(Case dep, ArrayList<Direction> path, DonneesSimulation d) {
		Case tmp;
		int i;
		
		tmp = dep;
		
		for(i = 0; i < path.size(); i++) {
			if(d.voisinExiste(tmp, path.get(i)))
				tmp = d.getVoisin(tmp, path.get(i));
			else
				return null;
		}
		
		return tmp;
	}
	
	
	private static double getCout(Case c, double[][] couts) {
		return couts[c.getColonne()][c.getLigne()];
	}
	
	private static double[][] setCout(Case c, double[][] couts, double cout) {
		couts[c.getColonne()][c.getLigne()] = cout;
		return couts;
	}
	
	private static Direction getDirection(Case c, Direction[][] directions) {
		return directions[c.getColonne()][c.getLigne()];
	}
	
	private static Direction[][] setDirection(Case c, Direction[][] directions, Direction direction) {
		directions[c.getColonne()][c.getLigne()] = direction;
		return directions;
	}
	
	
	public static PairDijkstra dijkstra(Robot r, LinkedList<Case> arr, DonneesSimulation d) {
		double[][] couts = new double[d.getCarte().getNbColonnes()][d.getCarte().getNbLignes()];
		Direction[][] directions = new Direction[d.getCarte().getNbColonnes()][d.getCarte().getNbLignes()];
		LinkedList<Case> caseFile = new LinkedList<Case>();
		ArrayList<Direction> ret = new ArrayList<Direction>();
		Case bestArr = null;
		int i, j;
		
		for(i=0; i<arr.size(); i++) {
			if(Case.casesEgales(r.getPosition(), arr.get(i))) {
				PairDijkstra cheminVide = new PairDijkstra(ret, 0);
				return cheminVide;
			}
		}
		if(r.getVitesse(r.getPosition().getNature()) == 0)
			return null;
		
		for(i=0; i<d.getCarte().getNbColonnes(); i++) {
			for(j=0; j<d.getCarte().getNbLignes(); j++) {
				couts[i][j] = -1;
			}
		}
		
		couts = setCout(r.getPosition(), couts, 0);
		caseFile.add(r.getPosition());
		
		while(!caseFile.isEmpty())
		{
			Case tmp = caseFile.getFirst();
			caseFile.removeFirst();
			
			// Le choix du numérateur pour le cout est totalement arbitraire
			double cout = getCout(tmp, couts) + (1000/r.getVitesse(tmp.getNature()));
				
			for (Direction dir : Direction.values()) {
				if(d.voisinExiste(tmp, dir))
				{
					Case next = d.getVoisin(tmp, dir);
						
					if(!(isIncendie(next, d) || isRobot(next, d)) && r.getVitesse(next.getNature()) > 0)
					{
						if(getCout(next, couts) == -1 || getCout(next, couts) > cout) {
							couts = setCout(next, couts, cout);
							caseFile.add(next);
							switch(dir) {
							case NORD:
								directions = setDirection(next, directions, Direction.SUD);
								break;
							case EST:
								directions = setDirection(next, directions, Direction.OUEST);
								break;
							case SUD:
								directions = setDirection(next, directions, Direction.NORD);
								break;
							case OUEST:
								directions = setDirection(next, directions, Direction.EST);
								break;
							}
						}
					}
				}
			}
		}
		
		// Choix de la destination la plus proche
		for(i=0; i<arr.size(); i++) {
			if(getCout(arr.get(i), couts) != -1) {
				if(bestArr == null) {
					bestArr = arr.get(i);
				}
				else if(getCout(arr.get(i), couts) < getCout(bestArr, couts)) {
					bestArr = arr.get(i);
				}
			}
		}
		
		// Maintenant on recupere le chemin
		if(bestArr == null)
			return null;
		
		Case tmp = bestArr;
		while(!Case.casesEgales(r.getPosition(), tmp)) {
			switch(getDirection(tmp, directions)) {
			case NORD:
				ret.add(0, Direction.SUD);
				break;
			case EST:
				ret.add(0, Direction.OUEST);
				break;
			case SUD:
				ret.add(0, Direction.NORD);
				break;
			case OUEST:
				ret.add(0, Direction.EST);
				break;
			}
			
			tmp = d.getVoisin(tmp, getDirection(tmp, directions));
		}
		
		return new PairDijkstra(ret, getCout(bestArr, couts));
	}
	
	
	
	
	public static ArrayList<Direction> eauPlusProche(Robot r, DonneesSimulation d) {
		LinkedList<Case> points = new LinkedList<Case>();
		int i, j;
		PairDijkstra ret;
		
		// On ajoute tout les points à proximité de l'eau
		for(i=0; i<d.getCarte().getNbLignes(); i++) {
			for(j=0; j<d.getCarte().getNbColonnes(); j++) {
				if(d.getCarte().getCase(i, j).getNature() == NatureTerrain.EAU) {
					for (Direction dir : Direction.values()) {
						if(d.voisinExiste(d.getCarte().getCase(i, j), dir))
							points.add(d.getVoisin(d.getCarte().getCase(i, j), dir));
					}
				}
			}
		}
		
		// on effectue le dijkstra
		ret = dijkstra(r, points, d);
		if(ret == null)
			return null;
		else
			return ret.getPath();
	}
	

	// Le drone doit aller sur l'eau
	public static ArrayList<Direction> eauPlusProche(Drone r, DonneesSimulation d) {
		LinkedList<Case> points = new LinkedList<Case>();
		int i, j;
		PairDijkstra ret;
		
		// On ajoute tout les points à proximité de l'eau
		for(i=0; i<d.getCarte().getNbLignes(); i++) {
			for(j=0; j<d.getCarte().getNbColonnes(); j++) {
				if(d.getCarte().getCase(i, j).getNature() == NatureTerrain.EAU) {
					points.add(d.getCarte().getCase(i, j));
				}
			}
		}
		
		// on effectue le dijkstra
		// on effectue le dijkstra
		ret = dijkstra(r, points, d);
		if(ret == null)
			return null;
		else
			return ret.getPath();
	}
	
	public static ArrayList<Direction> incendiePlusProche(Robot r, DonneesSimulation d) {
		LinkedList<Case> points = new LinkedList<Case>();
		int i;
		PairDijkstra ret;
		
		// On ajoute tout les points à proximité de l'incendie
		for(i=0; i<d.getNbIncendies(); i++) {
			for (Direction dir : Direction.values()) {
				if(d.voisinExiste(d.getIncendies(i).getPosition(), dir))
					points.add(d.getVoisin(d.getIncendies(i).getPosition(), dir));
			}
		}
		
		// on effectue le dijkstra
		ret = dijkstra(r, points, d);
		if(ret == null)
			return null;
		else
			return ret.getPath();
	}
}




