package formbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import service.IManagementService;

@ManagedBean
public class PlanBean {
	@ManagedProperty(value = "#{managementService}")
	IManagementService managementService;

	private String namePage = "Plan des lignes";

	public PlanBean() {
		super();
		System.out.println("PlanBean instanciated");
	}

	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}

	public String getNamePage() {
		return namePage;
	}

	public void setNamePage(String namePage) {
		this.namePage = namePage;
	}

}
