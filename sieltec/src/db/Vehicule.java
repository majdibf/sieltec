package db;

public class Vehicule {
	private double id;
	private String immatriculation;
	private int version;

	public Vehicule(double id, String immatriculation, int version) {
		super();
		this.id = id;
		this.immatriculation = immatriculation;
		this.version = version;
	}

	public double getId() {
		return id;
	}

	public void setId(double id) {
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
