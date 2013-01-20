package dao;

import java.util.List;

import db.Station;

public interface IStationDao {
	
	
	public Long insert(Station station);
	
	public Long delete(Station station);

	public List<Station> findAll();

	public Station findByName(String name);
	
}
