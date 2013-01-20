package dao;

import java.util.List;

import db.Parcours;

public interface IParcoursDao {

	public Long insert(Parcours parcours);

	public Long delete(Parcours parcours);

	public List<Parcours> findAll();
	
	public Parcours find(Long parcoursId);

}
