package formbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.joda.time.DateTime;
import org.openfaces.util.Faces;

import service.IMailService;
import service.IManagementService;

import db.Alerte;
import db.Conducteur;
import db.Parcours;
import db.Programme;
import db.SouscriptionAlerte;
import db.Vehicule;

@ManagedBean
public class AjouterAlerteBean {

	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;

	@ManagedProperty(value = "#{mailService}")
	private IMailService mailService;

	// input
	private String nom;
	private String parcours;
	private String description="Bonjour chers voyageurs,nous vous informons qu un problème à eu sur le parcours <. . . . . . . . >";

	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}

	public IMailService getMailService() {
		return mailService;
	}

	public void setMailService(IMailService mailService) {
		this.mailService = mailService;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getParcours() {
		return parcours;
	}

	public void setParcours(String parcours) {
		this.parcours = parcours;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// auto completion parcours
	public List<String> getSuggestedParcours() {
		List<String> suggestedParcours = new ArrayList<String>();
		List<Parcours> parcours = new ArrayList<Parcours>();
		parcours = managementService.getAllParcours();

		String typedValue = Faces.var("searchString", String.class);
		if (typedValue != null) {

			for (Parcours p : parcours) {
				String parcoursForComparison = p.getNom().toLowerCase();
				String typedValueForComparison = typedValue.toLowerCase();
				if (parcoursForComparison.startsWith(typedValueForComparison))
					suggestedParcours.add(p.getNom());
			}
		} else {
			for (int i = 0; i < parcours.size(); i++) {

				Parcours p = parcours.get(i);
				suggestedParcours.add(p.getNom());

			}
		}
		return suggestedParcours;
	}

	public String ajouter() {

		Parcours parc = managementService.getParcoursByName(parcours);
		Alerte a = new Alerte(null, nom, description, parc.getId());
		managementService.insertAlerte(a);
		List<SouscriptionAlerte> souscriptions = managementService.getSouscriptionAlerteByIdLigne(parc.getLigneId());
		for (SouscriptionAlerte souscrip: souscriptions) {
			
			mailService.sendMail(souscrip.getAdresseMail(), nom, "<html> <body> <a>lien</a>" + description + "<body/></html>");
			
		}
		return "liste_alertes";
	}

}
