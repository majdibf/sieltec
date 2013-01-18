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

import org.joda.time.Minutes;

import com.sun.xml.internal.ws.api.PropertySet.Property;

import commun.DBLoader;

import dao.IParcoursDao;
import db.ElementParcours;
import db.Ligne;
import db.Parcours;
import db.Station;

@ManagedBean(name = "parcoursDao", eager = true)
@ApplicationScoped
public class ParcoursDao implements IParcoursDao {

	@ManagedProperty(value = "#{dbloader}")
	private DBLoader dbLoader;

	@Override
	public double insert(Parcours parcours) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double delete(Parcours parcours) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Parcours> findAll() {
		List<Parcours> parcours = new ArrayList<Parcours>();

		try {
			Connection ds = dbLoader.getDs().getConnection();
			Statement statement = ds.createStatement();

			String query = "select * from parcours";
			ResultSet rs = statement.executeQuery(query);

			Parcours p;

			while (rs.next()) {

				int id = rs.getInt("id");
				String nom = rs.getString("nom");
				int idLigne = rs.getInt("id_ligne");
				// List<ElementParcours> elementsParcours = null;
				int version = rs.getInt("version");
				p = new Parcours(id, nom, idLigne, version);

				parcours.add(p);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			String error = "erreur de connexion à la base de données";
			System.out.println(error + this.getClass().getName());
		}

		return parcours;
	}

	public DBLoader getDbLoader() {
		return dbLoader;
	}

	public void setDbLoader(DBLoader dbLoader) {
		this.dbLoader = dbLoader;
	}
}
