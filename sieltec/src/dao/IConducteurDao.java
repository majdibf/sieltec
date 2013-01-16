package dao;

import java.util.List;

import db.Conducteur;


public interface IConducteurDao {

	
	public double insert(Conducteur conducteur);
	
	public double delete(Conducteur station);

	public List<Conducteur> findAll();
	
}
