package db;

import org.joda.time.DateTime;

public class Programme {
	private Long id;
	private DateTime dateHeureDebut;
	private Long parcoursId;
	private Vehicule vehicule;
	private Conducteur conducteur;
	private int version;


	public Programme(Long id, DateTime dateHeureDebut, Long parcoursId,
			Vehicule vehicule, Conducteur conducteur, int version) {
		super();
		this.id = id;
		this.dateHeureDebut = dateHeureDebut;
		this.parcoursId = parcoursId;
		this.vehicule = vehicule;
		this.conducteur = conducteur;
		this.version = version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DateTime getDateHeureDebut() {
		return dateHeureDebut;
	}

	public void setDateHeureDebut(DateTime dateHeureDebut) {
		this.dateHeureDebut = dateHeureDebut;
	}


	public Long getParcoursId() {
		return parcoursId;
	}

	public void setParcoursId(Long parcoursId) {
		this.parcoursId = parcoursId;
	}

	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	public Conducteur getConducteur() {
		return conducteur;
	}

	public void setConducteur(Conducteur conducteur) {
		this.conducteur = conducteur;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
