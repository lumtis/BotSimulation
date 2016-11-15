package simulation;

public class EvRemplir extends Evenement {
	Robot robot;
	int vol;
	
	public EvRemplir(long date, Robot r, int vol) {
		super(date);
		robot = r;
		this.vol = vol;
	}

	@Override
	void execute() {
		System.out.println("TEST !!!!!!!!!!");
		robot.setVolume(vol);
		robot.setEtat(Robot.EtatRobot.RIEN);
	}

}
