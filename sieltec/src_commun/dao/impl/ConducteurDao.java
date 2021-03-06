package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commun.DBLoader;

import dao.IConducteurDao;
import db.Conducteur;
import db.Parcours;

@ManagedBean(name = "conducteurDao", eager = true)
@ApplicationScoped
public class ConducteurDao implements IConducteurDao {

	private Logger logger = LogManager.getLogger(this.getClass().getName());

	@ManagedProperty(value = "#{dbloader}")
	private DBLoader dbLoader;

	@Override
	public Long insert(Conducteur conducteur) {
		String query = "insert into sieltec.Conducteur(nom,prenom,contact,version) values('"+conducteur.getNom()+"','"+conducteur.getPrenom()+"','"+conducteur.getContact()+"',"+conducteur.getVersion()+")";
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		long id = 0;
		
		try {
			conn = dbLoader.getDs().getConnection();
			statement = conn.createStatement();
			
			logger.trace("query = "+query);
			
			logger.trace("trying to execute :\n" + query);
			statement.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
			rs=statement.getGeneratedKeys();

			if (rs.next()) {
			    id = rs.getLong(1);
			} else {
			    // do what you have to do
			}
			 
			logger.trace("query executed successfuly :\n" + query);
	
			
		} catch (SQLException e) {
			e.printStackTrace();
			String error = "erreur de connexion � la base de donn�es";
			logger.error(error + this.getClass().getName());
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
		
		return id;
	}

	@Override
	public boolean delete(Conducteur c) {
		
		boolean result = false;
		String query = "delete from sieltec.Conducteur where id= "+c.getId()+"and version= "+c.getVersion();
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			conn = dbLoader.getDs().getConnection();
			statement = conn.createStatement();
			
			logger.trace("query = "+query);
			
			logger.trace("trying to execute :\n" + query);
			int rowsUpdated =statement.executeUpdate(query);
			 
			logger.trace("query executed successfuly :\n" + query);
			
			result = rowsUpdated > 0;

			
		} catch (SQLException e) {
			e.printStackTrace();
			String error = "erreur de connexion � la base de donn�es";
			logger.error(error + this.getClass().getName());
			result=false;
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
		return result;
	
	}

	@Override
	public List<Conducteur> findAll() {
		List<Conducteur> conducteurs = new ArrayList<Conducteur>();
		ResultSet rs=null;
		Statement statement=null;
		Connection conn = null;
		try {
			conn = dbLoader.getDs().getConnection();
			statement = conn.createStatement();

			String query = "select * from conducteur";

			rs = statement.executeQuery(query);

			Conducteur c;

			while (rs.next()) {
				Long id = rs.getLong("id");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String contact = rs.getString("contact");

				int version = rs.getInt("version");
				c = new Conducteur(id, nom, prenom, contact, version);
				conducteurs.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			String error = "erreur de connexion � la base de donn�es";
			logger.error(error + this.getClass().getName());
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

		return conducteurs;
	}

	public DBLoader getDbLoader() {
		return dbLoader;
	}

	public void setDbLoader(DBLoader dbLoader) {
		this.dbLoader = dbLoader;
	}

	@Override
	public Conducteur findById(Long conducteurId) {
		Conducteur result = null;
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			conn = dbLoader.getDs().getConnection();
			String query = "select * from conducteur where id = ?";
			statement = conn.prepareStatement(query);
			statement.setLong(1, conducteurId);
			
			logger.trace("trying to execute :\n" + query);
			logger.trace(conducteurId);
			rs = statement.executeQuery();
			logger.trace("query executed successfuly :\n" + query);
			logger.trace(conducteurId);

			if (rs.next()) {
				Long id = rs.getLong("ID");
				String nom = rs.getString("NOM");
				String prenom = rs.getString("PRENOM");
				String contact = rs.getString("CONTACT");
				int version = rs.getInt("VERSION");
				result = new Conducteur(id, nom, prenom, contact, version);
			} else {
				// pas de resultat
			}

		} catch (SQLException e) {
			e.printStackTrace();
			String error = "erreur de connexion � la base de donn�es";
			logger.error(error+this.getClass().getName());
		} finally {
			try {rs.close();} catch (Exception e){}
			try {statement.close();} catch (Exception e){}
			try {conn.close();} catch (Exception e){}
		}
		
		return result;	
		
	}

	@Override
	public Conducteur findByName(String nom, String prenom) {
		Conducteur result = null;
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			conn = dbLoader.getDs().getConnection();
			String query = "select * from conducteur where nom='"+nom+"' and prenom='"+prenom+"'";
			statement = conn.createStatement();
			
			logger.trace("trying to execute :\n" + query);
			rs = statement.executeQuery(query);
			logger.trace("query executed successfuly :\n" + query);
			
			if (rs.next()) {
				Long id = rs.getLong("ID");
				String nomC = rs.getString("NOM");
				String prenomC = rs.getString("PRENOM");
				String contact = rs.getString("CONTACT");
				int version = rs.getInt("VERSION");
				result = new Conducteur(id, nomC, prenomC, contact, version);
			} else {
				// pas de resultat
			}

		} catch (SQLException e) {
			e.printStackTrace();
			String error = "erreur de connexion � la base de donn�es";
			logger.error(error+this.getClass().getName());
		} finally {
			try {rs.close();} catch (Exception e){}
			try {statement.close();} catch (Exception e){}
			try {conn.close();} catch (Exception e){}
		}
		
		return result;	
	}

	@Override
	public boolean update(Conducteur c) {
		boolean result = false;
		String query = "update sieltec.Conducteur set nom ='"+c.getNom()+"',prenom='"+c.getPrenom()+"',contact='"+c.getContact()+"',version= "+(c.getVersion()+1)+" where id= "+c.getId()+"and version= "+c.getVersion();
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		long id = 0;
		
		try {
			conn = dbLoader.getDs().getConnection();
			statement = conn.createStatement();
			
			logger.trace("query = "+query);
			
			logger.trace("trying to execute :\n" + query);
			int rowsUpdated =statement.executeUpdate(query);
			 
			logger.trace("query executed successfuly :\n" + query);
			
			result = rowsUpdated > 0;

			
		} catch (SQLException e) {
			e.printStackTrace();
			String error = "erreur de connexion � la base de donn�es";
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
		return result;
		
	}

}
