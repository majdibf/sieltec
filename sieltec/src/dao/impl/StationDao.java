package dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.sun.xml.internal.bind.v2.TODO;

import commun.DBLoader;

import dao.IStationDao;
import db.Station;

@ManagedBean(name="stationDao", eager=true)
@ApplicationScoped
public class StationDao implements IStationDao, Serializable {
	
	@ManagedProperty(value="#{dbloader}")
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
		try {
			Connection ds = dbLoader.getDs().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	return null;
	}

	public DBLoader getDbLoader() {
		return dbLoader;
	}

	public void setDbLoader(DBLoader dbLoader) {
		this.dbLoader = dbLoader;
	}


}
