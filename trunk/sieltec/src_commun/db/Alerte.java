package db;

public class Alerte {

	private Long id;
	private String nom;
	private String Description;
	private Long parcoursId;
	
	
	public Alerte(Long id, String nom, String description, Long parcoursId) {
		super();
		this.id = id;
		this.nom = nom;
		Description = description;
		this.parcoursId = parcoursId;
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
	
	public Long getParcoursId() {
		return parcoursId;
	}
	
	public void setParcoursId(Long parcoursId) {
		this.parcoursId = parcoursId;
	}
	
}