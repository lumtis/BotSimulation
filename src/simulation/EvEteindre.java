package simulation;

public class EvEteindre extends Evenement {
	private int volume;
	private Robot robot;
	private Incendie target;
	
	public EvEteindre(long date, int v, Robot r, Incendie t) {
		super(date);
		volume = v;
		robot = r;
		target = t;
	}

	@Override
	void execute() {
		target.setIntensite(target.getIntensite() - volume);
		if(target.getIntensite() == 0) {
			target.setEtat(Incendie.EtatIncendie.ETEINT);
		}
	}

}
