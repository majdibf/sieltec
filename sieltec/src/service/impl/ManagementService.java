package service.impl;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import service.IManagementService;
import dao.IStationDao;

@ManagedBean(name="managementService", eager=true)
@ApplicationScoped
public class ManagementService implements IManagementService, Serializable {
	
	@ManagedProperty(value="#{stationDao}")
	private IStationDao stationDao;

	public ManagementService() {
		super();
		System.out.println("ManagementService instanciated");
	}
	
	public IStationDao getStationDao() {
		return stationDao;
	}

	public void setStationDao(IStationDao stationDao) {
		this.stationDao = stationDao;
	}

	@Override
	public String findPath(String startStation, String endStation) {
		return stationDao.findPath(startStation, endStation);
	}


}
