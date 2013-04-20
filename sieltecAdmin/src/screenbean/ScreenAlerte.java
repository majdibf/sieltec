package screenbean;

import db.Ligne;
import db.Parcours;

public class ScreenAlerte {

	private Long id;
	private String nom;
	private String Description;
	private Parcours parcours;
	private Ligne ligne;

	public ScreenAlerte(Long id, String nom, String description,
			Parcours parcours, Ligne ligne) {
		super();
		this.id = id;
		this.nom = nom;
		Description = description;
		this.parcours = parcours;
		this.ligne = ligne;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Parcours getParcours() {
		return parcours;
	}

	public void setParcours(Parcours parcours) {
		this.parcours = parcours;
	}

	public Ligne getLigne() {
		return ligne;
	}

	public void setLigne(Ligne ligne) {
		this.ligne = ligne;
	}

}
