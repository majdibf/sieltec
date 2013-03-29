package dao;

import java.util.List;

import db.Conducteur;


public interface IConducteurDao {

	
	public Long insert(Conducteur conducteur);
	
	public Long delete(Conducteur station);

	public List<Conducteur> findAll();

	public Conducteur findById(Long conducteurId);

	public Conducteur findByName(String nom, String prenom);
	
}
