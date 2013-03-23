package dao;

import java.util.List;

import db.Vehicule;

public interface IVehiculeDao {

	public Long insert(Vehicule vehicule);

	public Long delete(Vehicule vehicule);

	public List<Vehicule> findAll();

}
