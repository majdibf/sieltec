package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.Parcours;
import db.Station;

public interface IParcoursDao {

	public Long insert(Parcours parcours);

	public Long delete(Parcours parcours);

	public List<Parcours> findAll();
	
	public Parcours find(Long parcoursId);
	
	public HashMap<Long, Parcours> findByIdList(List<Long> list);
	
	public List<Parcours> findParcoursByIdLigne(long idLigne);

	public Parcours findByNameParcours(String nom);


}
