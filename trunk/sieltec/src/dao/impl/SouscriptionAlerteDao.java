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

import commun.DBLoader;

import dao.ISouscriptionAlerteDao;
import db.SouscriptionAlerte;
import db.Ligne;

@ManagedBean(name = "souscriptionAlerteDao", eager = true)
@ApplicationScoped

public class SouscriptionAlerteDao implements ISouscriptionAlerteDao {
	
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
			
			System.out.println("query = "+query);
			
			System.out.println("trying to execute :\n" + query);
			statement.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
			rs=statement.getGeneratedKeys();

			if (rs.next()) {
			    id = rs.getLong(1);
			} else {
			    // do what you have to do
			}
			 
			System.out.println("query executed successfuly :\n" + query);
	
			
		} catch (SQLException e) {
			e.printStackTrace();
			String error = "erreur de connexion à la base de données";
			System.out.println(error + this.getClass().getName());
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
	
}
