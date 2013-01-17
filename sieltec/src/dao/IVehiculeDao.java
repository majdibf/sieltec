package dao;

import java.util.List;

import db.Vehicule;

public interface IVehiculeDao {

	public double insert(Vehicule vehicule);

	public double delete(Vehicule vehicule);

	public List<Vehicule> findAll();

}
