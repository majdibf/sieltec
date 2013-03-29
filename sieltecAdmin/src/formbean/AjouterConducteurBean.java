package formbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import db.Conducteur;
import db.Station;

import service.IManagementService;

@ManagedBean
public class AjouterConducteurBean {

	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;
	
	//input
	private String nom;
	private String prenom;
	private String contact;
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
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
public String ajouter(){
		
		Conducteur c=new Conducteur(null, nom, prenom, contact, 0);
		managementService.insertConducteur(c);
		
		return "liste_conducteurs";
	}
}
