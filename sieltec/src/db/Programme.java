package db;

import org.joda.time.DateTime;

public class Programme {
	private Long id;
	private DateTime dateHeureDebut;
	private Long parcoursId;
	private Long vehiculeId;
	private Long conducteurId;
	private int version;
	public Programme(Long id, DateTime dateHeureDebut, Long parcoursId,
			Long vehiculeId, Long conducteurId, int version) {
		super();
		this.id = id;
		this.dateHeureDebut = dateHeureDebut;
		this.parcoursId = parcoursId;
		this.vehiculeId = vehiculeId;
		this.conducteurId = conducteurId;
		this.version = version;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public DateTime getDateHeureDebut() {
		return dateHeureDebut;
	}
	public void setDateHeureDebut(DateTime dateHeureDebut) {
		this.dateHeureDebut = dateHeureDebut;
	}
	public Long getParcoursId() {
		return parcoursId;
	}
	public void setParcoursId(Long parcoursId) {
		this.parcoursId = parcoursId;
	}
	public Long getVehiculeId() {
		return vehiculeId;
	}
	public void setVehiculeId(Long vehiculeId) {
		this.vehiculeId = vehiculeId;
	}
	public Long getConducteurId() {
		return conducteurId;
	}
	public void setConducteurId(Long conducteurId) {
		this.conducteurId = conducteurId;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}


}
