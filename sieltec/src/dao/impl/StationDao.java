package dao.impl;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import dao.IStationDao;
import db.Station;

@ManagedBean(name="stationDao", eager=true)
@ApplicationScoped
public class StationDao implements IStationDao, Serializable {
	

	int compteur = 0;
	
	public StationDao() {
		super();
		System.out.println("ManagementService instanciated");
	}

	@Override
	public double insert(Station station) {
	
		return 5;
	}


}
