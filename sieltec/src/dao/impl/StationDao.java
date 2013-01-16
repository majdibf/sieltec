package dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.sun.xml.internal.bind.v2.TODO;

import commun.DBLoader;

import dao.IStationDao;
import db.Station;

@ManagedBean(name = "stationDao", eager = true)
@ApplicationScoped
public class StationDao implements IStationDao, Serializable {

	@ManagedProperty(value = "#{dbloader}")
	private DBLoader dbLoader;

	public StationDao() {
		super();
		System.out.println("ManagementService instanciated");
	}

	@Override
	public double insert(Station station) {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public double delete(Station station) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Station> findAll() {

		List<Station> stations = new ArrayList<Station>();

		try {
			Connection ds = dbLoader.getDs().getConnection();
			Statement statement = ds.createStatement();

			String query = "select * from station";

			ResultSet rs = statement.executeQuery(query);

			Station s;

			while (rs.next()) {
				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				String longitude = rs.getString("longitude");
				String latitude = rs.getString("latitude");
				int version = rs.getInt("version");
				s = new Station(id, nom, longitude, latitude, version);
				stations.add(s);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			String error = "erreur de connexion � la base de donn�es";
			System.out.println(error+this.getClass().getName());
		}

		return stations;
	}

	public DBLoader getDbLoader() {
		return dbLoader;
	}

	public void setDbLoader(DBLoader dbLoader) {
		this.dbLoader = dbLoader;
	}

}
