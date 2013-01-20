package dao;

import java.util.List;

import db.Programme;

public interface IProgrammeDao {

	public Long insert(Programme programme);

	public Long delete(Programme programme);

	public List<Programme> findAll();

}
