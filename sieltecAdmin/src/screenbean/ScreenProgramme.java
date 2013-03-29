package screenbean;

import java.util.Date;

import org.joda.time.DateTime;

import db.Conducteur;
import db.Parcours;
import db.Vehicule;

public class ScreenProgramme {
	private Long id;
	private Date dateHeureDebut;
	private Parcours parcours;
	private Vehicule vehicule;
	private Conducteur conducteur;

	public ScreenProgramme(Long id, Date dateHeureDebut, Parcours parcours,
			Vehicule vehicule, Conducteur conducteur) {
		super();
		this.id = id;
		this.dateHeureDebut = dateHeureDebut;
		this.parcours = parcours;
		this.vehicule = vehicule;
		this.conducteur = conducteur;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateHeureDebut() {
		return dateHeureDebut;
	}

	public void setDateHeureDebut(Date dateHeureDebut) {
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

}
