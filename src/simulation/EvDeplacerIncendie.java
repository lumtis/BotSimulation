package simulation;

import java.util.ArrayList;
import java.util.LinkedList;

import simulation.Carte.Direction;

public class EvDeplacerIncendie extends Evenement {
	Robot robot;
	ArrayList<Direction> path;
	DonneesSimulation d;
	Simulateur s;
	/**
     * \brief constructeur Evenement incendies
     * \param la date
	 * \param le robot
	 * \param liste des Direction
     * \param le Simulateur
     */
	public EvDeplacerIncendie(long date, Robot r, ArrayList<Direction> p, Simulateur s) {
		super(date);
		robot = r;
		path = p;
		this.s = s;
		d = s.getData();
	}

	/**
     * \brief réalise l'évènement à la bonne date
     */
	void execute() {
		// On verifie que la case suivante ne contient pas un robot
		Case next = d.getVoisin(robot.getPosition(), path.get(0));
		if(!Utilitaire.isRobot(next, d)) {
			robot.setPosition(next);
			path.remove(0);

			// Si le robot n'a pas encore fini son trajet
			if(path.size() > 0) {
				// On calcule temps temps que prend le robot pour se deplacer sur la case actuelle
				long delay = Utilitaire.delayCase(robot, next, d);

				// On creer un nouvelle evvenement pour le prochaine mouvement
				EvDeplacerIncendie nextMove = new EvDeplacerIncendie(s.getDate() + delay, robot, path, s);
				s.ajouteEvenement(nextMove);
			}
			else {
				//S'il est arrivé on le fait éteindre son incendie
				robot.setEtat(Robot.EtatRobot.ETEINDRE);

				// ...
			}
		}
		else {
			// Si le robot est bloqué, il recherche un nouveau chemin vers cet incendie, sinon il s'arete
			LinkedList<Case> voisins = new LinkedList<Case>();

			// On obtient les cases à proximités de l'incendie
			for(Direction dir : Direction.values()) {
				if(d.voisinExiste(robot.getTarget().getPosition(), dir)) {
					voisins.add(d.getVoisin(robot.getTarget().getPosition(), dir));
				}
			}

			path = Utilitaire.dijkstra(robot, voisins, d).getPath();
			if(path != null) {
				next = d.getVoisin(robot.getPosition(), path.get(0));
				robot.setPosition(next);
				path.remove(0);
			}
			else {
				robot.setEtat(Robot.EtatRobot.RIEN);
				robot.getTarget().setEtat(Incendie.EtatIncendie.LIBRE);
			}
		}
	}
}
