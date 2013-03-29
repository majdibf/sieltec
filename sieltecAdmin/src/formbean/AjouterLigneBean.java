package formbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import db.Ligne;
import db.Station;

import service.IManagementService;

@ManagedBean
public class AjouterLigneBean {

	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;

	private String nom;

	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String ajouter() {

		Ligne l = new Ligne(null, nom, 0);
		managementService.insertLigne(l);
		return "liste_lignes";
	}

}
