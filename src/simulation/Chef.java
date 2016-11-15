package simulation;

import java.util.LinkedList;

import simulation.Carte.Direction;

public class Chef {
	private DonneesSimulation d;
	private Simulateur s;
	private Robot r;

	/**
     * \brief constructeur de Chef
     * \param toutes les données de simulation
     * \param le Simulateur
     * \param le robot
     */
	public Chef(DonneesSimulation d, Simulateur s, Robot r) {
		this.d = d;
		this.s = s;
		this.r = r;
	}


	public void test() {
		r.setPosition(d.getVoisin(r.getPosition(), Carte.Direction.EST));
	}


	/**
     * \brief le chef manage les robots
     */
	public void inspecter() {
		int i, j;
		Utilitaire.PairDijkstra tmp, bestPath;
		Robot bestRobot;

		for(i=0; i<d.getNbIncendies(); i++) {
			if(d.getIncendies(i).getEtat() == Incendie.EtatIncendie.LIBRE) {
				LinkedList<Case> voisins = new LinkedList<Case>();

				// On obtient les cases à proximités de l'incendie
				for(Direction dir : Direction.values()) {
					if(d.voisinExiste(d.getIncendies(i).getPosition(), dir)) {
						voisins.add(d.getVoisin(d.getIncendies(i).getPosition(), dir));
					}
				}

				// On regarde quel est le robot libre le plus proche
				for(j=0, bestRobot=null, bestPath = null; j<d.getNbRobots(); i++) {
					Robot r = d.getRobots(j);
					if(r.getEtat() == Robot.EtatRobot.RIEN) {
						tmp = Utilitaire.dijkstra(r, voisins, d);
						if(bestPath == null || tmp.getCout() < bestPath.getCout()) {
							bestPath = tmp;
							bestRobot = r;
						}
					}
				}

				// Si au moins un robot est dispo, on le fait aller vers le feu
				if(bestRobot != null) {
					bestRobot.setEtat(Robot.EtatRobot.DEPLACERINCENDIE);
					d.getIncendies(i).setEtat(Incendie.EtatIncendie.OCCUPE);
					bestRobot.setTarget(d.getIncendies(i));

					// On creer l'évenement pour commencer à deplacer le robot
					if(bestPath.getPath().size() > 0) {
						// On calcule temps temps que prend le robot pour se deplacer sur la case actuelle
						long delay = Utilitaire.delayCase(bestRobot, bestRobot.getPosition(), d);

						// On creer un nouvelle evvenement pour le prochaine mouvement
						EvDeplacerIncendie nextMove = new EvDeplacerIncendie(s.getDate() + delay, bestRobot, bestPath.getPath(), s);
						s.ajouteEvenement(nextMove);
					}
				}
			}
		}
	}
}
