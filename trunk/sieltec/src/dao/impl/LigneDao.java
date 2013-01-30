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

import dao.ILigneDao;
import db.Ligne;

@ManagedBean(name = "LigneDao", eager = true)
@ApplicationScoped
public class LigneDao implements ILigneDao {

	@ManagedProperty(value = "#{dbloader}")
	private DBLoader dbLoader;

	@Override
	public Long insert(Ligne ligne) {
		// TODO Auto-generated method stub
		return 0l;
	}

	@Override
	public Long delete(Ligne ligne) {
		// TODO Auto-generated method stub
		return 0l;
	}

	@Override
	public List<Ligne> findAll() {
		List<Ligne> lignes = new ArrayList<Ligne>();
		try {
			Connection ds = dbLoader.getDs().getConnection();
			Statement statement = ds.createStatement();

			String query = "select * from ligne";

			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				Long id = rs.getLong("id");
				String nom = rs.getString("nom");
				int version = rs.getInt("version");

				Ligne l = new Ligne(id, nom, version);

				lignes.add(l);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			String error = "erreur de connexion à la base de données";
			System.out.println(error + this.getClass().getName());
		}

		return lignes;
	}
	
	
	public DBLoader getDbLoader() {
		return dbLoader;
	}

	public void setDbLoader(DBLoader dbLoader) {
		this.dbLoader = dbLoader;
	}


}
