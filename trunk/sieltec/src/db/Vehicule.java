package db;

public class Vehicule {
	private Long id;
	private String immatriculation;
	private int version;

	public Vehicule(Long id, String immatriculation, int version) {
		super();
		this.id = id;
		this.immatriculation = immatriculation;
		this.version = version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
