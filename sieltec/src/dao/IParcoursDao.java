package dao;

import java.util.List;

import db.Parcours;

public interface IParcoursDao {

	public double insert(Parcours parcours);

	public double delete(Parcours parcours);

	public List<Parcours> findAll();

}
