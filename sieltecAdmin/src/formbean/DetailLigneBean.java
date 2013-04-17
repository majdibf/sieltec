package formbean;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import service.IManagementService;

import db.Ligne;

@ManagedBean(name = "detailLigneBean")
@SessionScoped
public class DetailLigneBean {
	// input
	private Ligne ligne;
	// output

	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;

	public IManagementService getManagementService() {
		return managementService;
	}

	public Ligne getLigne() {
		return ligne;
	}

	public void setLigne(Ligne ligne) {
		this.ligne = ligne;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}

	public String afficherDetailLigne() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext()
				.getRequestParameterMap();
		Long idLigne = Long.parseLong((params.get("idLigne")));
		this.ligne = managementService.getLignesById(idLigne);

		return "detail_ligne";
	}

	public String retour() {
		return "liste_lignes";
	}
}
