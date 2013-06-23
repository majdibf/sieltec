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

import dao.IAlerteDao;
import db.Alerte;


@ManagedBean(name = "alerteDao", eager = true)
@ApplicationScoped
public class AlerteDao implements IAlerteDao {
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
	public List<Alerte> findAll() {
		List<Alerte> alertes = new ArrayList<Alerte>();
		ResultSet rs = null;
		Statement statement = null;
		Connection conn = null;
		try {
			conn = dbLoader.getDs().getConnection();
			statement = conn.createStatement();

			String query = "select * from sieltec.alerte";

			rs = statement.executeQuery(query);

			Alerte a;

			while (rs.next()) {
				Long id = rs.getLong("id");
				String nom = rs.getString("nom");
				String description = rs.getString("description");
				Long idParcours = rs.getLong("id_parcours");

				a = new Alerte(id, nom, description, idParcours);
				alertes.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			String error = "erreur de connexion à la base de données";
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

		return alertes;

	}

	@Override
	public void insert(Alerte a) {
		ResultSet rs = null;
		Statement statement = null;
		Connection conn = null;
		try {
			conn = dbLoader.getDs().getConnection();
			statement = conn.createStatement();

			String query = "insert into sieltec.alerte(nom,description,id_parcours) values ('"
					+ a.getNom()
					+ "','"
					+ a.getDescription()
					+ "',"
					+ a.getParcoursId() + ")";

			statement.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
			String error = "erreur de connexion à la base de données";
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

	}

}
