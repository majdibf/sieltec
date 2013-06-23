package dao.impl;

import java.sql.Connection;
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

import dao.ISouscriptionAlerteDao;
import db.Alerte;
import db.SouscriptionAlerte;
import db.Ligne;

@ManagedBean(name = "souscriptionAlerteDao", eager = true)
@ApplicationScoped

public class SouscriptionAlerteDao implements ISouscriptionAlerteDao {
	
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
	public Long insert(SouscriptionAlerte souscription) {
		
		String query = "insert into sieltec.SouscriptionAlerte(adresse_mail,id_ligne) values('"+souscription.getAdresseMail()+"',"+souscription.getLigneId()+")";
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
		
		return id;
	}

	@Override
	public List<SouscriptionAlerte> findByIdLigne() {
		List<SouscriptionAlerte> souscriptions = new ArrayList<SouscriptionAlerte>();
		ResultSet rs=null;
		Statement statement=null;
		Connection conn = null;
		try {
			conn = dbLoader.getDs().getConnection();
			statement = conn.createStatement();

			String query = "select * from sieltec.souscriptionalerte";
			rs = statement.executeQuery(query);
			SouscriptionAlerte souscrip;

			while (rs.next()) {
				Long id = rs.getLong("id");
				String adresseMail = rs.getString("adresse_mail");
				Long idLigne = rs.getLong("id_ligne");

				souscrip = new SouscriptionAlerte(id, adresseMail, idLigne);
				souscriptions.add(souscrip);
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

		return souscriptions;

	}

	
}
