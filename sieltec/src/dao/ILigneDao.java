package dao;

import java.util.List;

import db.Ligne;

public interface ILigneDao {
	public double insert(Ligne ligne);

	public double delete(Ligne ligne);

	public List<Ligne> findAll();

}
