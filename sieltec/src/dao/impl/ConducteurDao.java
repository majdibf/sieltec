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
	public double insert(Conducteur conducteur) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double delete(Conducteur station) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Conducteur> findAll() {
		List<Conducteur> conducteurs = new ArrayList<Conducteur>();
		try {
			Connection ds = dbLoader.getDs().getConnection();
			Statement statement = ds.createStatement();

			String query = "select * from conducteur";

			ResultSet rs = statement.executeQuery(query);

			Conducteur c;

			while (rs.next()) {
				int id = rs.getInt("id");
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
