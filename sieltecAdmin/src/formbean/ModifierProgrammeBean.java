package formbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.joda.time.DateTime;
import org.openfaces.util.Faces;

import db.Conducteur;
import db.Parcours;
import db.Programme;
import db.Vehicule;

import screenbean.ScreenProgramme;
import service.IManagementService;

@ManagedBean
public class ModifierProgrammeBean {
	
	// input
	private Date date;
	private int heure;
	private int minute;
	private String parcours;
	private String vehicule;
	private String conducteur;

	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;
	
	@ManagedProperty(value = "#{detailProgrammeBean}")
	private DetailProgrammeBean detailProgrammeBean;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getHeure() {
		return heure;
	}

	public void setHeure(int heure) {
		this.heure = heure;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public String getParcours() {
		return parcours;
	}

	public void setParcours(String parcours) {
		this.parcours = parcours;
	}

	public String getVehicule() {
		return vehicule;
	}

	public void setVehicule(String vehicule) {
		this.vehicule = vehicule;
	}

	public String getConducteur() {
		return conducteur;
	}

	public void setConducteur(String conducteur) {
		this.conducteur = conducteur;
	}

	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}
	
	
	public DetailProgrammeBean getDetailProgrammeBean() {
		return detailProgrammeBean;
	}

	public void setDetailProgrammeBean(DetailProgrammeBean detailProgrammeBean) {
		this.detailProgrammeBean = detailProgrammeBean;
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

		// auto completion conducteur
		public List<String> getSuggestedConducteur() {
			List<String> suggestedConducteur = new ArrayList<String>();
			List<Conducteur> conducteurs = new ArrayList<Conducteur>();
			conducteurs = managementService.getAllConducteurs();

			String typedValue = Faces.var("searchString", String.class);
			if (typedValue != null) {

				for (Conducteur c : conducteurs) {
					String conducteurForComparison = (c.getNom() + " " + c
							.getPrenom()).toLowerCase();
					String typedValueForComparison = typedValue.toLowerCase();
					if (conducteurForComparison.startsWith(typedValueForComparison))
						suggestedConducteur.add(c.getNom() + "/" + c.getPrenom());
				}
			} else {
				for (int i = 0; i < conducteurs.size(); i++) {

					Conducteur c = conducteurs.get(i);
					suggestedConducteur.add(c.getNom() + "/" + c.getPrenom());

				}
			}
			return suggestedConducteur;
		}

		// auto completion vehicule
		public List<String> getSuggestedVehicule() {
			List<String> suggestedVehicule = new ArrayList<String>();
			List<Vehicule> vehicules = new ArrayList<Vehicule>();
			vehicules = managementService.getAllVehicules();

			String typedValue = Faces.var("searchString", String.class);
			if (typedValue != null) {

				for (Vehicule v : vehicules) {
					String vehiculeForComparison = (v.getImmatriculation())
							.toLowerCase();
					String typedValueForComparison = typedValue.toLowerCase();
					if (vehiculeForComparison.startsWith(typedValueForComparison))
						suggestedVehicule.add(v.getImmatriculation());
				}
			} else {
				for (int i = 0; i < vehicules.size(); i++) {

					Vehicule v = vehicules.get(i);
					suggestedVehicule.add(v.getImmatriculation());
				}
			}
			return suggestedVehicule;
		}

		public String initialiser(){
			ScreenProgramme sp=detailProgrammeBean.getScreenProgramme();
			this.date=sp.getDateHeureDebut();
			this.heure=sp.getDateHeureDebut().getHours();
			this.minute=sp.getDateHeureDebut().getMinutes();
			this.parcours=sp.getParcours().getNom();
			this.vehicule=sp.getVehicule().getImmatriculation();
			this.conducteur=sp.getConducteur().getNom()+"/"+sp.getConducteur().getPrenom();
			
			return "modifier_programme";
		}
		
		public String update(){
			String nom;
			String prenom;
			date.setHours(heure);
			date.setMinutes(minute);
			System.out.println(date);
			DateTime d =new DateTime(date.getTime());
			Parcours parc=managementService.getParcoursByName(parcours);
			Vehicule vehic=managementService.getVehiculeByImmatriculation(vehicule);
		
			 StringTokenizer c=new StringTokenizer(conducteur, "/");
			    	int n=c.countTokens();
			    	nom=c.nextToken();
			    	prenom=c.nextToken();  
			
			
			Conducteur cond=managementService.getConducteurByName(nom,prenom);
			
			Programme p =new Programme(detailProgrammeBean.getScreenProgramme().getId() , d , parc.getId() , vehic.getId() ,cond.getId(), detailProgrammeBean.getVersion());
			managementService.updateProgramme(p);

			return "liste_programmes";
			}
		
		public String retour(){
			return "detail_conducteur";
		}


		
	
}
