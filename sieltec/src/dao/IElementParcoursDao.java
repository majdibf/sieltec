package dao;

import java.util.List;

import db.ElementParcours;

public interface IElementParcoursDao {

	
public double insert(ElementParcours ep);
	
	public double delete(ElementParcours ep);

	public List<ElementParcours> findAll();

}
