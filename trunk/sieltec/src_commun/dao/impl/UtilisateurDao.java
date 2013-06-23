package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commun.DBLoader;
import dao.IUtilisateurDao;
import db.Ligne;
import db.Utilisateur;


@ManagedBean(name = "utilisateurDao", eager = true)
@ApplicationScoped

public class UtilisateurDao implements IUtilisateurDao{
	
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@ManagedProperty(value = "#{dbloader}")
	private DBLoader dbLoader;

	public DBLoader getDbLoader() {
		return dbLoader;
	}

	public void setDbLoader(DBLoader dbLoader) {
		this.dbLoader = dbLoader;
	}


	@Override
	public Utilisateur findUtilisateurByLoginAndPassword(String login, String password ) {
		String query = "select * from sieltec.utilisateur where login='"+login+"'and password='"+password+"'";
		Utilisateur utilisateur = null;
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			conn = dbLoader.getDs().getConnection();
			statement = conn.createStatement();			
			
			logger.trace("query = "+query);
			
			
			logger.trace("trying to execute :\n" + query);
			rs = statement.executeQuery(query);
			logger.trace("query executed successfuly :\n" + query);

			if (rs.next()) {
			    long id = rs.getLong(1);
			    String nom=rs.getString("nom");
			    String prenom=rs.getString("prenom");
			    String log=rs.getString("login");
			    String pwd=rs.getString("password");
			    
			    utilisateur=new Utilisateur(id, nom, prenom, log, pwd);
			} else {
			    // do what you have to do
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			String error = "erreur de connexion à la base de données";
			logger.trace(error + this.getClass().getName());
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
			}
			try {
				statement.close();
			} catch (Exception e) {
			}
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
		
		return utilisateur;
	}
	
	
	
}
