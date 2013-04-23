package formbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import service.IManagementService;

import db.Station;

@ManagedBean
@SessionScoped
public class CountryTable {
	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;
	
	private  List<Station> data = new ArrayList<Station>();
	
	
	
	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}

	public List<Station> getData() {
		return managementService.getAllStations();
	}

	public void setData(List<Station> data) {
		data = data;
	}

	
	
	
	
} 
	
