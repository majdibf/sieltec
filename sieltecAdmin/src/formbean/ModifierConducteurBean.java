package formbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import db.Conducteur;
import db.ElementParcours;
import db.Ligne;
import db.Parcours;

import service.IManagementService;

@ManagedBean
public class ModifierConducteurBean {

	//input
	private Long id;
	private String nom;
	private String prenom;
	private String contact;
	
	//output
	
	
	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;
	
	@ManagedProperty(value = "#{detailConducteurBean}")
	private DetailConducteurBean detailConducteurBean;

	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}

	public DetailConducteurBean getDetailConducteurBean() {
		return detailConducteurBean;
	}

	public void setDetailConducteurBean(DetailConducteurBean detailConducteurBean) {
		this.detailConducteurBean = detailConducteurBean;
	}
	
	
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String initialiser(){
		Conducteur c=detailConducteurBean.getConducteur();
		
		this.id=c.getId();
		this.nom=c.getNom();
		this.prenom=c.getPrenom();
		this.contact=c.getContact();
		
		return "modifier_conducteur";
	}
	
	public String update(){
		Conducteur c =new Conducteur(detailConducteurBean.getConducteur().getId() , nom , prenom , contact , detailConducteurBean.getConducteur().getVersion());
		managementService.updateConducteur(c);
		return "liste_conducteurs";
	}
	
	public String retour(){
		return "detail_conducteur";
	}

}
