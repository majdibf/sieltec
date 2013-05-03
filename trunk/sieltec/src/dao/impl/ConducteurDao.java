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

import dao.IConducteurDao;
import db.Conducteur;

@ManagedBean(name = "conducteurDao", eager = true)
@ApplicationScoped
public class ConducteurDao implements IConducteurDao {

	@ManagedProperty(value = "#{dbloader}")
	private DBLoader dbLoader;

	@Override
	public Long insert(Conducteur conducteur) {
		// TODO Auto-generated method stub
		return 0l;
	}

	@Override
	public Long delete(Conducteur station) {
		// TODO Auto-generated method stub
		return 0l;
	}

	@Override
	public List<Conducteur> findAll() {
		List<Conducteur> conducteurs = new ArrayList<Conducteur>();
		ResultSet rs =null;
		Statement statement =null;
		Connection conn=null;
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


		return conducteurs;
	}

	public DBLoader getDbLoader() {
		return dbLoader;
	}

	public void setDbLoader(DBLoader dbLoader) {
		this.dbLoader = dbLoader;
	}

}
