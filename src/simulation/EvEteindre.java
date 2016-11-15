package simulation;

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
		super(date);
		volume = v;
		robot = r;
		target = t;
	}
	/**
     * \brief réalise l'évènement à la bonne date
     */
	@Override
	void execute() {
		target.setIntensite(target.getIntensite() - volume);
		if(target.getIntensite() == 0) {
			target.setEtat(Incendie.EtatIncendie.ETEINT);
		}
	}

}
