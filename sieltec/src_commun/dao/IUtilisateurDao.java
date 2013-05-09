package dao;

import db.Utilisateur;

public interface IUtilisateurDao {
	
	public Utilisateur findUtilisateurByLoginAndPassword(String login, String password);

}
