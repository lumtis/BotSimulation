package simulation;

import java.util.ArrayList;

import simulation.Carte.Direction;

public class EvDeplacerEau extends EvDeplacerIncendie {
<<<<<<< HEAD

	/**
     * \brief constructeur Evenement EAU
     * \param la date
	 * \param le robot
	 * \param liste des Direction
     * \param le Simulateur
     */

	Drone drone;


	public EvDeplacerEau(long date, Robot r, ArrayList<Direction> p, Simulateur s) {
		super(date, r, p, s);
		drone = null;
	}

	public EvDeplacerEau(long date, Drone r, ArrayList<Direction> p, Simulateur s) {
		super(date, r, p, s);
		drone = r;
	}
	/**
     * \brief réalise l'évènement à la bsbonne date
     */
	void execute() {

		Case next = d.getVoisin(robot.getPosition(), path.get(0));

		if(!Utilitaire.isRobot(next, d)) {
			robot.setPosition(next);
			path.remove(0);


		if(path == null) {
			// Si il n'y a pas de chemin pour aller vers l'eau, alors on attend encore
			if(drone != null) {
				path = Utilitaire.eauPlusProche(drone, d);
				EvDeplacerEau nextMove = new EvDeplacerEau(s.getDate() + 10, drone, path, s);
				s.ajouteEvenement(nextMove);
			}
			else {
				path = Utilitaire.eauPlusProche(robot, d);
				EvDeplacerEau nextMove = new EvDeplacerEau(s.getDate() + 10, robot, path, s);
				s.ajouteEvenement(nextMove);
			}
		}
		else if (path.size() == 0) {
			robot.setEtat(Robot.EtatRobot.REMPLIR);
			robot.remplirReservoir();

		}
		else {
			Case next = d.getVoisin(robot.getPosition(), path.get(0));

			if(!Utilitaire.isRobot(next, d)) {
				robot.setPosition(next);
				path.remove(0);
			}

		}

		if(path.size() > 0) {
			// On calcule temps temps que prend le robot pour se deplacer sur la case actuelle
			long delay = Utilitaire.delayCase(robot, next, d);

			// On creer un nouvelle evvenement pour le prochaine mouvement
			EvDeplacerEau nextMove = new EvDeplacerEau(s.getDate() + delay, robot, path, s);
			s.ajouteEvenement(nextMove);
		}
		else {
			robot.setEtat(Robot.EtatRobot.REMPLIR);

			else {
				ArrayList<Direction> newPath;
				if(drone != null) {
					newPath = Utilitaire.eauPlusProche(drone, d);
				}
				else {
					newPath = Utilitaire.eauPlusProche(robot, d);
				}
				if(newPath != null) {
					path = newPath;
					next = d.getVoisin(robot.getPosition(), path.get(0));
					robot.setPosition(next);
					path.remove(0);
				}
			}

			if(path.size() > 0) {
				// On calcule temps temps que prend le robot pour se deplacer sur la case actuelle
				long delay = Utilitaire.delayCase(robot, next, d);

				// On creer un nouvelle evvenement pour le prochaine mouvement
				EvDeplacerEau nextMove;
				if(drone != null) {
					nextMove = new EvDeplacerEau(s.getDate() + delay, drone, path, s);
				}
				else {
					nextMove = new EvDeplacerEau(s.getDate() + delay, robot, path, s);
				}
				s.ajouteEvenement(nextMove);
			}
			else {
				robot.setEtat(Robot.EtatRobot.REMPLIR);
				robot.remplirReservoir();
			}
		}
	}
}
