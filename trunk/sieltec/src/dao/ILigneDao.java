package dao;

import java.util.List;

import db.Ligne;

public interface ILigneDao {
	public Long insert(Ligne ligne);

	public Long delete(Ligne ligne);

	public List<Ligne> findAll();

}
