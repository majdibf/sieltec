package db;

import java.util.Date;

import org.joda.time.DateTime;

public class Evenement {
	private Long id;
	private Long programmeId;
	private Long stationId;
	private Long typeEvenementId;
	private DateTime dateHeure;
	private int version;

	public Evenement(Long id, Long programmeId, Long stationId,
			Long typeEvenementId, DateTime dateHeure, int version) {
		super();
		this.id = id;
		this.programmeId = programmeId;
		this.stationId = stationId;
		this.typeEvenementId = typeEvenementId;
		this.dateHeure = dateHeure;
		this.version = version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProgrammeId() {
		return programmeId;
	}

	public void setProgrammeId(Long programmeId) {
		this.programmeId = programmeId;
	}

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public Long getTypeEvenementId() {
		return typeEvenementId;
	}

	public void setTypeEvenementId(Long typeEvenementId) {
		this.typeEvenementId = typeEvenementId;
	}

	public DateTime getDateHeure() {
		return dateHeure;
	}

	public void setDateHeure(DateTime dateHeure) {
		this.dateHeure = dateHeure;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
