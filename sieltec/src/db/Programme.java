package db;

import org.joda.time.DateTime;

public class Programme {
	private double id;
	private DateTime dateHeureDebut;
	private Parcours parcours;
	private Vehicule vehicule;
	private Conducteur conducteur;
	private int version;

	public Programme(double id, DateTime dateHeureDebut, Parcours parcours,
			Vehicule vehicule, Conducteur conducteur, int version) {
		super();
		this.id = id;
		this.dateHeureDebut = dateHeureDebut;
		this.parcours = parcours;
		this.vehicule = vehicule;
		this.conducteur = conducteur;
		this.version = version;
	}

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public DateTime getDateHeureDebut() {
		return dateHeureDebut;
	}

	public void setDateHeureDebut(DateTime dateHeureDebut) {
		this.dateHeureDebut = dateHeureDebut;
	}

	public Parcours getParcours() {
		return parcours;
	}

	public void setParcours(Parcours parcours) {
		this.parcours = parcours;
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
