package simulation;

import java.util.ArrayList;

import simulation.Carte.Direction;

public class EvDeplacerEau extends EvDeplacerIncendie {

	public EvDeplacerEau(long date, Robot r, ArrayList<Direction> p, Simulateur s) {
		super(date, r, p, s);
	}

	void execute() {
		Case next = d.getVoisin(robot.getPosition(), path.get(0));
		
		if(!Utilitaire.isRobot(next, d)) {
			robot.setPosition(next);
			path.remove(0);
		}
		else {
			ArrayList<Direction> newPath = Utilitaire.eauPlusProche(robot, d);
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
			EvDeplacerEau nextMove = new EvDeplacerEau(s.getDate() + delay, robot, path, s);
			s.ajouteEvenement(nextMove);
		}
		else {
			robot.setEtat(Robot.EtatRobot.REMPLIR);
		}
	}
}
