package db;

public class Ligne {

	private Long id;
	private String nom;
	private int version;

	public Ligne(Long id, String nom, int version) {
		super();
		this.id = id;
		this.nom = nom;
		this.version = version;
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

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
