package dao;

import java.util.List;

import org.joda.time.DateTime;

import db.Programme;

public interface IProgrammeDao {

	public Long insert(Programme programme);

	public boolean delete(Programme programme);

	public List<Programme> findAll();
	
	public List<Programme> findByDateAndIdParcours(DateTime date,long idParcours);
	public List<Programme> findByDate(DateTime date);

	public Programme findById(Long idProgramme);

	public boolean update(Programme p);

}
