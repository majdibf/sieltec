package formbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import service.IManagementService;

@ManagedBean
public class AlerteBean {
	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;

	private String namePage = "Recevoir des alertes trafic concernant vos lignes";

	public AlerteBean() {
		super();
		System.out.println("AlerteBen instanciated");
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
