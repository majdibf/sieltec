package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Minutes;

import commun.DBLoader;

import dao.IElementParcoursDao;
import db.Conducteur;
import db.ElementParcours;
import db.Parcours;
import db.Programme;
import db.Station;
import db.Vehicule;

@ManagedBean(name = "elementParcoursDao", eager = true)
@ApplicationScoped
public class ElementParcoursDao implements IElementParcoursDao {

	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@ManagedProperty(value = "#{dbloader}")
	private DBLoader dbLoader;

	@Override
	public Long insert(ElementParcours ep) {
		return 0l;
	}

	@Override
	public Long delete(ElementParcours ep) {
		// TODO Auto-generated method stub
		return 0l;
	}

	@Override
	public List<ElementParcours> findAll() {
		List<ElementParcours> elementParcours = new ArrayList<ElementParcours>();
		Connection conn=null;
		Statement statement=null;
		ResultSet rs =null;
		try {
			conn = dbLoader.getDs().getConnection();
			statement = conn.createStatement();

			String query = "select * from element_parcours";

			rs = statement.executeQuery(query);

			ElementParcours ep;

			while (rs.next()) {

				Long id = rs.getLong("id");
				Long parcoursId = rs.getLong("ID_PARCOURS");
				Long stationDepId = rs.getLong("ID_STATION_DEP");
				Long stationArrId = rs.getLong("ID_STATION_ARR");

				int d = rs.getInt("duree");
				Minutes duree = Minutes.minutes(d);

				d = rs.getInt("duree_arret");
				Minutes dureeArret = Minutes.minutes(d);
				int version = rs.getInt("version");

				ep = new ElementParcours(id, parcoursId, stationDepId, stationArrId, duree, dureeArret, version);
				elementParcours.add(ep);
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

		return elementParcours;
	}

	public DBLoader getDbLoader() {
		return dbLoader;
	}

	public void setDbLoader(DBLoader dbLoader) {
		this.dbLoader = dbLoader;
	}

	@Override
	public List<ElementParcours> findByIdParcours(long idParcours) {
		List<ElementParcours> elementParcours=new ArrayList<ElementParcours>();
		
		String query = "select * from element_parcours where id_parcours="+idParcours;
		ElementParcours ep = null;
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

			while (rs.next()) {
				Long id = rs.getLong("id");
				Long parcoursId = rs.getLong("ID_PARCOURS");
				Long stationDepId = rs.getLong("ID_STATION_DEP");
				Long stationArrId = rs.getLong("ID_STATION_ARR");

				int d = rs.getInt("duree");
				Minutes duree = Minutes.minutes(d);

				d = rs.getInt("duree_arret");
				Minutes dureeArret = Minutes.minutes(d);
				int version = rs.getInt("version");

				ep = new ElementParcours(id, parcoursId, stationDepId, stationArrId, duree, dureeArret, version);
				elementParcours.add(ep);

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
		return elementParcours;
	
	}
}
