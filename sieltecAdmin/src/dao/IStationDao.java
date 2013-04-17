package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import db.Station;

public interface IStationDao {

	public Long insert(Station station);

	public Long delete(Station station);

	public List<Station> findAll();

	public Station findByName(String name);

	public HashMap<Long, Station> findByListId(List<Long> list);

	public Station findById(Long stationDepId);

	public void update(Station s);

}
