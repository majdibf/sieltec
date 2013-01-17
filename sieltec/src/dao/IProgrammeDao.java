package dao;

import java.util.List;

import db.Programme;

public interface IProgrammeDao {

	public double insert(Programme programme);

	public double delete(Programme programme);

	public List<Programme> findAll();

}
