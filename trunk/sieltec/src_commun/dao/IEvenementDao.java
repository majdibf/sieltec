package dao;

import java.util.List;

import org.joda.time.DateTime;

import db.Evenement;

public interface IEvenementDao {

	public Long insert(Evenement evenement);

	public Long delete(Evenement evenement);

	public List<Evenement> findAll();

	public Evenement find(Long idProgramme, Long idStation, Long typeEvenement);

	List<Evenement> findByDate(DateTime date);

}
