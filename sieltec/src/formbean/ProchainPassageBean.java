package formbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import service.IManagementService;

@ManagedBean
public class ProchainPassageBean {
	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;

	private String namePage = "Prochain passae à l'arrêt";

	public ProchainPassageBean() {
		super();
		System.out.println("ProchainPassageBean instanciated");
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
