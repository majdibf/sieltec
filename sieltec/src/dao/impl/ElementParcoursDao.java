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

import commun.DBLoader;

import dao.IElementParcoursDao;
import db.Conducteur;
import db.ElementParcours;
import db.Parcours;
import db.Station;

@ManagedBean(name = "elementParcoursDao", eager = true)
@ApplicationScoped
public class ElementParcoursDao implements IElementParcoursDao {

	@ManagedProperty(value = "#{dbloader}")
	private DBLoader dbLoader;

	@Override
	public double insert(ElementParcours ep) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double delete(ElementParcours ep) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ElementParcours> findAll() {
		List<ElementParcours> elementParcours = new ArrayList<ElementParcours>();

		try {
			Connection ds = dbLoader.getDs().getConnection();
			Statement statement = ds.createStatement();

			String query = "select * from elementparcours";

			ResultSet rs = statement.executeQuery(query);

			ElementParcours ep;

			while (rs.next()) {

				int id = rs.getInt("id");
				Parcours parcours = null;
				Station stationDep = null;
				Station stationArr = null;

				int d = rs.getInt("duree");
				Minutes duree = Minutes.minutes(d);

				d = rs.getInt("dureearret");
				Minutes dureeArret = Minutes.minutes(d);
				int version = rs.getInt("version");

				ep = new ElementParcours(id, parcours, stationDep, stationArr,
						duree, dureeArret, version);
				elementParcours.add(ep);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			String error = "erreur de connexion à la base de données";
			System.out.println(error + this.getClass().getName());
		}

		return elementParcours;
	}

	public DBLoader getDbLoader() {
		return dbLoader;
	}

	public void setDbLoader(DBLoader dbLoader) {
		this.dbLoader = dbLoader;
	}
}
