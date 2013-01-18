package db;

import org.joda.time.DateTime;

public class Programme {
	private double id;
	private DateTime dateHeureDebut;
	private int idParcours;
	private int idVehicule;
	private int idConducteur;
	private int version;

	public Programme(double id, DateTime dateHeureDebut, int idParcours,
			int idVehicule, int idConducteur, int version) {
		super();
		this.id = id;
		this.dateHeureDebut = dateHeureDebut;
		this.idParcours = idParcours;
		this.idVehicule = idVehicule;
		this.idConducteur = idConducteur;
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

	public int getIdParcours() {
		return idParcours;
	}

	public void setIdParcours(int idParcours) {
		this.idParcours = idParcours;
	}

	public int getIdVehicule() {
		return idVehicule;
	}

	public void setIdVehicule(int idVehicule) {
		this.idVehicule = idVehicule;
	}

	public int getIdConducteur() {
		return idConducteur;
	}

	public void setIdConducteur(int idConducteur) {
		this.idConducteur = idConducteur;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
