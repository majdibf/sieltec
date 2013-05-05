package dao;

import java.util.List;

import db.Evenement;

public interface IEvenementDao {

	public Long insert(Evenement evenement);

	public Long delete(Evenement evenement);

	public List<Evenement> findAll();

}
