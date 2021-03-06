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

import dao.ILigneDao;
import db.Ligne;
import db.Parcours;

@ManagedBean(name = "ligneDao", eager = true)
@ApplicationScoped
public class LigneDao implements ILigneDao {

	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@ManagedProperty(value = "#{dbloader}")
	private DBLoader dbLoader;

	@Override
	public Long insert(Ligne ligne) {
		String query = "insert into sieltec.Ligne(nom,version) values('"+ligne.getNom()+"',"+ligne.getVersion()+")";
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		Long id = null;
		
		try {
			conn = dbLoader.getDs().getConnection();
			statement = conn.createStatement();
			
			logger.trace("query = "+query);
			
			logger.trace("trying to execute :\n" + query);
			statement.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
			rs=statement.getGeneratedKeys();

			if (rs.next()) {
			    id = rs.getLong(1);
			} else {
			    // do what you have to do
			}
			 
			logger.trace("query executed successfuly :\n" + query);
	
			
		} catch (SQLException e) {
			e.printStackTrace();
			String error = "erreur de connexion � la base de donn�es";
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
		
		return id;
	}

	@Override
	public boolean delete(Ligne l) {
		boolean result = false;
		String query = "delete from sieltec.Ligne where id= "+l.getId()+"and version= "+l.getVersion();
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			conn = dbLoader.getDs().getConnection();
			statement = conn.createStatement();
			
			logger.trace("query = "+query);
			
			logger.trace("trying to execute :\n" + query);
			int rowsUpdated =statement.executeUpdate(query);
			 
			logger.trace("query executed successfuly :\n" + query);
			
			result = rowsUpdated > 0;

			
		} catch (SQLException e) {
			e.printStackTrace();
			String error = "erreur de connexion � la base de donn�es";
			logger.trace(error + this.getClass().getName());
			result=false;
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
		return result;

	}

	@Override
	public List<Ligne> findAll() {
		List<Ligne> lignes = new ArrayList<Ligne>();
		Connection conn=null;
		Statement statement=null;
		ResultSet rs =null;
		try {
			conn = dbLoader.getDs().getConnection();
			statement = conn.createStatement();

			String query = "select * from ligne";

			rs = statement.executeQuery(query);

			while (rs.next()) {
				Long id = rs.getLong("id");
				String nom = rs.getString("nom");
				int version = rs.getInt("version");

				Ligne l = new Ligne(id, nom, version);

				lignes.add(l);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			String error = "erreur de connexion � la base de donn�es";
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
		

		return lignes;
	}
	
	
	public DBLoader getDbLoader() {
		return dbLoader;
	}

	public void setDbLoader(DBLoader dbLoader) {
		this.dbLoader = dbLoader;
	}

	@Override
	public List<Ligne> findLignesByIdStation(float idStartStation) {
		List<Ligne> lignes=new ArrayList<Ligne>();
		
		String query = "select distinct l.id,l.nom,l.version from ligne l, parcours p, element_parcours ep, station s where (l.id=p.id_ligne and p.id= ep.id_parcours and (s.id=ep.id_station_dep or s.id=ep.id_station_arr) and s.id="+idStartStation+")";
		Ligne ligne = null;
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
				Long id = rs.getLong("ID");
				String nom = rs.getString("NOM");
				int version = rs.getInt("VERSION");
				ligne = new Ligne(id, nom, version);
				lignes.add(ligne);
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
			String error = "erreur de connexion � la base de donn�es";
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
		
		return lignes;
	}

	@Override
	public Ligne findByName(String nomLigne) {
		
		String query = "select * from sieltec.ligne where nom='"+nomLigne+"'";
		Ligne ligne = null;
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
				Long id = rs.getLong("ID");
				String nom = rs.getString("NOM");
				int version = rs.getInt("VERSION");
				ligne = new Ligne(id, nom, version);
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
			String error = "erreur de connexion � la base de donn�es";
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
		
		return ligne;
	}

	@Override
	public Ligne findLignesById(Long ligneId) {
		String query = "select * from sieltec.ligne where id="+ligneId;
		Ligne ligne = null;
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
				Long id = rs.getLong("ID");
				String nom = rs.getString("NOM");
				int version = rs.getInt("VERSION");
				ligne = new Ligne(id, nom, version);
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
			String error = "erreur de connexion � la base de donn�es";
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
		
		return ligne;
	}

	@Override
	public boolean update(Ligne l) {
		boolean result = false;
		String query = "update sieltec.Ligne set nom ='"+l.getNom()+"',version= "+(l.getVersion()+1)+" where id= "+l.getId()+"and version= "+l.getVersion();
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			conn = dbLoader.getDs().getConnection();
			statement = conn.createStatement();
			
			logger.trace("query = "+query);
			
			logger.trace("trying to execute :\n" + query);
			int rowsUpdated =statement.executeUpdate(query);
			 
			logger.trace("query executed successfuly :\n" + query);
	
			result = rowsUpdated > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			String error = "erreur de connexion � la base de donn�es";
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
		return result;

	}


}
