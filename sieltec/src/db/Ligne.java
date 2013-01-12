package db;

public class Ligne {

	private double id;
	private String nom;
	private int version;

	public Ligne(double id, String nom, int version) {
		super();
		this.id = id;
		this.nom = nom;
		this.version = version;
	}

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
