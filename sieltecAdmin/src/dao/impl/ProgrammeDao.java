package dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.joda.time.DateTime;

import commun.DBLoader;

import dao.IProgrammeDao;
import db.Conducteur;
import db.Ligne;
import db.Parcours;
import db.Programme;
import db.Vehicule;

@ManagedBean(name = "programmeDao", eager = true)
@ApplicationScoped
public class ProgrammeDao implements IProgrammeDao {

	@ManagedProperty(value = "#{dbloader}")
	DBLoader dbLoader;

	@Override
	public Long insert(Programme programme) {
		Timestamp d=new Timestamp(programme.getDateHeureDebut().getMillis());
		String query = "insert into sieltec.Programme(date_heure_debut,id_parcours,id_vehicule,id_conducteur,version) values('"+d+"',"+programme.getParcoursId()+","+programme.getVehiculeId()+","+programme.getConducteurId()+","+programme.getVersion()+")";
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
			String error = "erreur de connexion � la base de donn�es";
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

		return 0l;
	}

	@Override
	public Long delete(Programme programme) {
		// TODO Auto-generated method stub
		return 0l;
	}

	@Override
	public List<Programme> findAll() {

		List<Programme> programmes = new ArrayList<>();

		try {
			Connection ds = dbLoader.getDs().getConnection();
			Statement statement = ds.createStatement();

			String query = "select * from programme";

			ResultSet rs = statement.executeQuery(query);

			Programme prog;

			while (rs.next()) {
				Long id = rs.getLong("id");

				Timestamp dateHeureDebutTS = rs.getTimestamp("DATE_HEURE_DEBUT");
				
				DateTime dateHeureDebut = new DateTime(dateHeureDebutTS.getTime());
				Long parcoursId = rs.getLong("ID_PARCOURS");
				
				Long vehicule = rs.getLong("ID_VEHICULE");
				Long conducteur = rs.getLong("ID_CONDUCTEUR");
				int version = rs.getInt("version");

				prog = new Programme(id, dateHeureDebut, parcoursId, vehicule, conducteur, version);

				programmes.add(prog);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			String error = "erreur de connexion � la base de donn�es";
			System.out.println(error + this.getClass().getName());
		}
		return programmes;
	}

	public DBLoader getDbLoader() {
		return dbLoader;
	}

	public void setDbLoader(DBLoader dbLoader) {
		this.dbLoader = dbLoader;
	}

	@Override
	public List<Programme> findByDateAndIdParcours(DateTime date,long idParcours) {
		List<Programme> programmes=new ArrayList<Programme>();
		Timestamp d=new Timestamp(date.getMillis());
		
		String query = "select * from programme where date_heure_debut >='" + d + "' and id_parcours="+idParcours;
		Programme prog = null;
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			conn = dbLoader.getDs().getConnection();
			statement = conn.createStatement();			
			
			System.out.println("query = "+query);
			
			
			System.out.println("trying to execute :\n" + query);
			rs = statement.executeQuery(query);
			System.out.println("query executed successfuly :\n" + query);

			while (rs.next()) {
				Long id = rs.getLong("id");

				Timestamp dateHeureDebutTS = rs.getTimestamp("DATE_HEURE_DEBUT");
				
				DateTime dateHeureDebut = new DateTime(dateHeureDebutTS.getTime());
				Long parcoursId = rs.getLong("ID_PARCOURS");
				
				Long vehicule = null;
				Long conducteur = null;
				int version = rs.getInt("version");

				prog = new Programme(id, dateHeureDebut, parcoursId, vehicule, conducteur, version);
				
				programmes.add(prog);

			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
			String error = "erreur de connexion � la base de donn�es";
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
		return programmes;
	}

}