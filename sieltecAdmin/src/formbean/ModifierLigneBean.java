package formbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import db.Conducteur;
import db.Ligne;

import service.IManagementService;

@ManagedBean
public class ModifierLigneBean {

	//input
	private Long id;
	private String nom;
	
	//output
	
	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;
	
	@ManagedProperty(value = "#{detailLigneBean}")
	private DetailLigneBean detailLigneBean;

	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
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

	public DetailLigneBean getDetailLigneBean() {
		return detailLigneBean;
	}

	public void setDetailLigneBean(DetailLigneBean detailLigneBean) {
		this.detailLigneBean = detailLigneBean;
	}

	
	public String initialiser(){
		Ligne l=detailLigneBean.getLigne();
		
		this.id=l.getId();
		this.nom=l.getNom();
		
		return "modifier_ligne";
	}

	
	public String update(){
		Ligne l =new Ligne(detailLigneBean.getLigne().getId(), nom ,detailLigneBean.getLigne().getVersion());
		managementService.updateLigne(l);
		

		return "liste_lignes";
	}
	
	public String retour(){
		return "detail_ligne";
	}

	
}
