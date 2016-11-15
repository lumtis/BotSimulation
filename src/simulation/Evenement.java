package simulation;

public abstract class Evenement {
	private long date;
	/**
     * \brief constructeur évènement
     * \param date
     */
	public Evenement(long date) {
		this.date = date;
	}
	/**
     * \brief
     * \return la date
     */
	public long getDate() {
		return date;
	}

	abstract void execute();
}
