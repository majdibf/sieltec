package dao;

import java.util.List;

import db.Vehicule;

public interface IVehiculeDao {

	public Long insert(Vehicule vehicule);

	public boolean delete(Vehicule v);

	public List<Vehicule> findAll();

	public Vehicule findById(Long vehiculeId);

	public Vehicule findByImmatriculation(String vehicule);

	public boolean update(Vehicule v);

}
