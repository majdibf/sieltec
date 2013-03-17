package screenbean;

import java.util.Date;

public class Passage {
	private long num;
	private Date dateHeure;

	public Passage(long num, Date dateHeure) {
		super();
		this.num = num;
		this.dateHeure = dateHeure;
	}

	public long getNum() {
		return num;
	}

	public void setNum(long num) {
		this.num = num;
	}

	public Date getDateHeure() {
		return dateHeure;
	}

	public void setDateHeure(Date dateHeure) {
		this.dateHeure = dateHeure;
	}

}
