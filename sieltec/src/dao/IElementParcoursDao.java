package dao;

import java.util.List;

import db.ElementParcours;

public interface IElementParcoursDao {

	
	public Long insert(ElementParcours ep);
	
	public Long delete(ElementParcours ep);

	public List<ElementParcours> findAll();

}
