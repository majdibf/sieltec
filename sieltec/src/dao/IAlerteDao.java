package dao;

import java.util.List;

import db.Alerte;

public interface IAlerteDao {

	public List<Alerte> findAll();

	public void insert(Alerte a);

}
