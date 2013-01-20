package db;

import java.util.Date;

public class Evenement {
	private Long id;
	private Programme programme;
	private Station station;
	private TypeEvenement typeEvenement;
	private Date dateHeure;
	private int version;

	public Evenement(Long id, Programme programme, Station station,
			TypeEvenement typeEvenement, Date dateHeure, int version) {
		super();
		this.id = id;
		this.programme = programme;
		this.station = station;
		this.typeEvenement = typeEvenement;
		this.dateHeure = dateHeure;
		this.version = version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Programme getProgramme() {
		return programme;
	}

	public void setProgramme(Programme programme) {
		this.programme = programme;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public TypeEvenement getTypeEvenement() {
		return typeEvenement;
	}

	public void setTypeEvenement(TypeEvenement typeEvenement) {
		this.typeEvenement = typeEvenement;
	}

	public Date getDateHeure() {
		return dateHeure;
	}

	public void setDateHeure(Date dateHeure) {
		this.dateHeure = dateHeure;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
