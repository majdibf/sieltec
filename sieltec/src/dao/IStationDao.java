package dao;

import java.util.List;

import db.Station;

public interface IStationDao {
	
	
	public double insert(Station station);
	
	public double delete(Station station);

	public List<Station> findAll();
	
}
