package dao;

import java.util.List;

import db.Ligne;

public interface ILigneDao {
	public Long insert(Ligne ligne);

	public Long delete(Ligne ligne);

	public List<Ligne> findAll();
	
	public List<Ligne> findLignesByIdStation(float startStation);

	public Ligne findByName(String nomLigne);

	public Ligne findLignesById(Long ligneId);

	public void update(Ligne l);
					   
}
