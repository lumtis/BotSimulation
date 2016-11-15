package simulation;

import java.util.ArrayList;

import simulation.Carte.Direction;

public class EvEteindre extends Evenement {
	private int volume;
	private Robot robot;

	private Incendie target;

	/**
     * \brief constructeur Evenement éteindre
     * \param la date
	 * \param le robot
	 * \param liste des Direction
     * \param l'incendie'
     */
	public EvEteindre(long date, int v, Robot r, Incendie t) {

	private DonneesSimulation d;

	public EvEteindre(long date, int v, Robot r) {

		super(date);
		volume = v;
		robot = r;
	}
	/**
     * \brief réalise l'évènement à la bonne date
     */
	@Override
	void execute() {

		robot.target.setIntensite(robot.target.getIntensite() - volume);

		if(robot.getVolume() != -1) {
			robot.setVolume(robot.getVolume() - volume);
		}

		if(robot.target.getIntensite() <= 0) {
			robot.target.setEtat(Incendie.EtatIncendie.ETEINT);

			if(robot.getVolume() > 0 || robot.getVolume() == -1) {
				robot.setEtat(Robot.EtatRobot.RIEN);
			}
		}

		if(robot.getVolume() == 0) {
			// Faire aller cherche de l'eau au robot
			if(robot.target.getEtat() != Incendie.EtatIncendie.ETEINT) {
				robot.target.setEtat(Incendie.EtatIncendie.LIBRE);
			}
			robot.setEtat(Robot.EtatRobot.DEPLACEREAU);
			robot.allerChercherEau();
		}
	}

}
