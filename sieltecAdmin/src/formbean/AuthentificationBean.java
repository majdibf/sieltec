package formbean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.*;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Date;

import org.joda.time.DateTime;
import org.openfaces.util.Faces;

import db.ElementProgramme;
import db.Parcours;
import db.Programme;
import db.Station;
import db.Utilisateur;

import screenbean.ElementItineraire;
import service.IManagementService;

@ManagedBean
public class AuthentificationBean {
	@ManagedProperty(value = "#{managementService}")
	private IManagementService managementService;

	// input
	private String login = "user1";
	private String password = "user1";
	
	//output
	private String errorMessage;
	

	public AuthentificationBean() {
		super();
		System.out.println("AuthentificationBean instanciated");
	}

	public IManagementService getManagementService() {
		return managementService;
	}

	public void setManagementService(IManagementService managementService) {
		this.managementService = managementService;
	}


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String connexion(){
		String nomPage;
		Utilisateur utilisateur = managementService.getUtilisateurByUserNameAndPassword(login,password);
		if(utilisateur != null){
			 nomPage="accueil";
			 FacesContext facesContext = FacesContext.getCurrentInstance();
			 HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
			 session.setAttribute("userConnected", utilisateur);
			 
		}else{
			nomPage="authentification";
			errorMessage="Valeur incorrecte de nom d'utilisateur ou/et de mot de passe.";
				
		}
	
		return nomPage;
	}
	
	
	public String logout(){
		String nomPage;
			 nomPage="authentification";
			 FacesContext facesContext = FacesContext.getCurrentInstance();
			 //HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false).removeAttribute("userConnected");
			 
			  ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
			  
				
		
	
		return nomPage;
	}
	
	
	
}
