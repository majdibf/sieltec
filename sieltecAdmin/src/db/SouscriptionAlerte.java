package db;

public class SouscriptionAlerte {

	private Long id;
	private String adresseMail;
	private Long ligneId;

	public SouscriptionAlerte(Long id, String adresseMail, Long ligneId) {
		super();
		this.id = id;
		this.adresseMail = adresseMail;
		this.ligneId = ligneId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdresseMail() {
		return adresseMail;
	}

	public void setAdresseMail(String adresseMail) {
		this.adresseMail = adresseMail;
	}

	public Long getLigneId() {
		return ligneId;
	}

	public void setLigneId(Long ligneId) {
		this.ligneId = ligneId;
	}

}
