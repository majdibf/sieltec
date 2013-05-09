package dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.joda.time.DateTime;

import commun.DBLoader;

import dao.IEvenementDao;
import dao.IStationDao;
import db.Evenement;
import db.Ligne;
import db.Station;

@ManagedBean(name = "evenementDao", eager = true)
@ApplicationScoped
public class EvenementDao implements IEvenementDao, Serializable {

	@ManagedProperty(value = "#{dbloader}")
	private DBLoader dbLoader;

	public EvenementDao() {
		super();
		System.out.println("EvenementDao instanciated");
	}

	public DBLoader getDbLoader() {
		return dbLoader;
	}

	public void setDbLoader(DBLoader dbLoader) {
		this.dbLoader = dbLoader;
	}

	@Override
	public Long insert(Evenement evenement) {
		Timestamp d = new Timestamp(evenement.getDateHeure().getMillis());
		
		String query = "insert into sieltec.Evenement(id_programme,id_station,id_type_evenement,date_heure,version) values(" + evenement.getProgrammeId() + "," + evenement.getStationId() + "," + evenement.getTypeEvenementId() + ",'" + d + "'," + evenement.getVersion()+")";
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		Long id = null;
		
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
	
	
	@Override
	public Long delete(Evenement Evenement) {
		// TODO Auto-generated method stub
		return 0l;
	}

	@Override
	public List<Evenement> findAll() {

		List<Evenement> evenements = new ArrayList<Evenement>();
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;

		try {
			conn = dbLoader.getDs().getConnection();
			statement = conn.createStatement();

			String query = "select * from evenement";

			System.out.println("trying to execute :\n" + query);
			rs = statement.executeQuery(query);
			System.out.println("query executed successfuly :\n" + query);

			Evenement e;
			while (rs.next()) {
				Long id = rs.getLong("id");
				Long programmeId = rs.getLong("id_programme");
				Long stationId = rs.getLong("id_station");
				Long typeEvenementId = rs.getLong("id_type_evenement");
				DateTime date_heure = new DateTime(rs.getTimestamp("DATE_HEURE").getTime());
				int version = rs.getInt("version");
				e = new Evenement(id, programmeId, stationId, typeEvenementId, date_heure, version);
				evenements.add(e);
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

		return evenements;
	}

	@Override
	public Evenement find(Long idProgramme, Long idStation, Long idTypeEvenement) {
		Evenement evenement=null ;
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;

		try {
			conn = dbLoader.getDs().getConnection();
			statement = conn.createStatement();

			String query = "select * from evenement where(id_programme="+idProgramme+" and id_station="+idStation+"and id_type_evenement=" + idTypeEvenement+")";

			System.out.println("trying to execute :\n" + query);
			rs = statement.executeQuery(query);
			System.out.println("query executed successfuly :\n" + query);

			while (rs.next()) {
				Long id = rs.getLong("id");
				Long programmeId = rs.getLong("id_programme");
				Long stationId = rs.getLong("id_station");
				Long typeEvenementId = rs.getLong("id_type_evenement");
				DateTime date_heure = new DateTime(rs.getTimestamp("DATE_HEURE").getTime());
				int version = rs.getInt("version");
				evenement = new Evenement(id, programmeId, stationId, typeEvenementId, date_heure, version);
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

		return evenement;
	}

	}



