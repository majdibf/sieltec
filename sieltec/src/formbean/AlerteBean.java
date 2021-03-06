package formbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openfaces.util.Faces;

import service.IManagementService;
import db.Ligne;
import db.SouscriptionAlerte;

@ManagedBean
public class AlerteBean {
	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;

	private String namePage = "Recevoir des alertes concernant vos lignes";
	
	//input
	private String adresseMail;
	private String ligne="rouge";

	private Logger logger = LogManager.getLogger(this.getClass().getName());
		

	
	public AlerteBean() {
		super();
		logger.debug("AlerteBen instanciated");
	}
	
	public String getAdresseMail() {
		return adresseMail;
	}

	public void setAdresseMail(String adresseMail) {
		this.adresseMail = adresseMail;
	}

	public String getLigne() {
		return ligne;
	}


	public void setLigne(String ligne) {
		this.ligne = ligne;
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
	
	// auto completion
	public List<String> getSuggestedLignes() {
		List<String> suggestedLignes = new ArrayList<String>();
		List<Ligne> lignes = new ArrayList<Ligne>();
		lignes = managementService.getAllLignes();

		String typedValue = Faces.var("searchString", String.class);
		if (typedValue != null) {

			for (Ligne l : lignes) {
				String ligneForComparison = l.getNom().toLowerCase();
				String typedValueForComparison = typedValue.toLowerCase();
				if (ligneForComparison.startsWith(typedValueForComparison))
					suggestedLignes.add(l.getNom());
			}
		} else {
			for (int i = 0; i < lignes.size(); i++) {

				Ligne l = lignes.get(i);
				suggestedLignes.add(l.getNom());

			}
		}
		return suggestedLignes;
	}

	public String saveSouscription(){
		Ligne l = managementService.findLigneByName(ligne);
		managementService.insertSouscriptionAlerte(new SouscriptionAlerte(null, adresseMail, l.getId()));
		return "alerte";
	}
	
	
}
