package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
import dao.IVehiculeDao;
import db.Parcours;
import db.Vehicule;

@ManagedBean(name="vehiculeDao", eager=true)
@ApplicationScoped

public class VehiculeDao implements IVehiculeDao {

	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@ManagedProperty(value="#{dbloader}")
	private DBLoader dbLoader;

	@Override
	public Long insert(Vehicule vehicule) {
		String query = "insert into sieltec.Vehicule(immatriculation,version) values('"+vehicule.getImmatriculation()+"',"+vehicule.getVersion()+")";
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		long id = 0;
		
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
		
		return id;
	
	}

	@Override
	public boolean delete(Vehicule v) {
		boolean result = false;
		String query = "delete from sieltec.vehicule where id= "+v.getId()+"and version= "+v.getVersion();
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
			String error = "erreur de connexion à la base de données";
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
	public List<Vehicule> findAll() {
		List<Vehicule> vehicules= new ArrayList<>();
		Connection conn=null;
		Statement statement=null;
		ResultSet rs=null;
		try{
			conn=dbLoader.getDs().getConnection();
			statement=conn.createStatement();
			
			String query="select * from vehicule";
			rs= statement.executeQuery(query);
			
			Vehicule v;
			while(rs.next()){
				Long id=rs.getLong("id");
				String immatriculation=rs.getString("immatriculation");
				int version=rs.getInt("version");
				v=new Vehicule(id, immatriculation, version);
				
				vehicules.add(v);
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
		
		
		return vehicules;
	}

	public DBLoader getDbLoader() {
		return dbLoader;
	}

	public void setDbLoader(DBLoader dbLoader) {
		this.dbLoader = dbLoader;
	}

	@Override
	public Vehicule findById(Long vehiculeId) {
		Vehicule result = null;
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			conn = dbLoader.getDs().getConnection();
			String query = "select * from vehicule where id = ?";
			statement = conn.prepareStatement(query);
			statement.setLong(1, vehiculeId);
			
			logger.trace("trying to execute :\n" + query);
			logger.trace(vehiculeId);
			rs = statement.executeQuery();
			logger.trace("query executed successfuly :\n" + query);
			logger.trace(vehiculeId);

			if (rs.next()) {
				Long id = rs.getLong("ID");
				String immatriculation = rs.getString("immatriculation");
				int version = rs.getInt("VERSION");
				result = new Vehicule(id, immatriculation, version);
			} else {
				// pas de resultat
			}

		} catch (SQLException e) {
			e.printStackTrace();
			String error = "erreur de connexion à la base de données";
			logger.trace(error+this.getClass().getName());
		} finally {
			try {rs.close();} catch (Exception e){}
			try {statement.close();} catch (Exception e){}
			try {conn.close();} catch (Exception e){}
		}
		
		return result;	
		
	}

	@Override
	public Vehicule findByImmatriculation(String vehicule) {

		Vehicule result = null;
		Connection conn = null;
		ResultSet rs = null;
		Statement statement=null;
		try {
			conn = dbLoader.getDs().getConnection();
			statement = conn.createStatement();
			String query = "select * from vehicule where Immatriculation ='"+vehicule+"'";
			
			logger.trace("trying to execute :\n" + query);

			rs = statement.executeQuery(query);
			logger.trace("query executed successfuly :\n" + query);
		
			if (rs.next()) {
				Long id = rs.getLong("ID");
				String immatriculation = rs.getString("immatriculation");
				int version = rs.getInt("VERSION");
				result = new Vehicule(id, immatriculation, version);
			} else {
				// pas de resultat
			}

		} catch (SQLException e) {
			e.printStackTrace();
			String error = "erreur de connexion à la base de données";
			logger.trace(error+this.getClass().getName());
		} finally {
			try {rs.close();} catch (Exception e){}
			try {statement.close();} catch (Exception e){}
			try {conn.close();} catch (Exception e){}
		}
		
		return result;	
	}

	@Override
	public boolean update(Vehicule v) {
		boolean result = false;
		String query = "update sieltec.vehicule set immatriculation ='"+v.getImmatriculation()+"',version= "+(v.getVersion()+1)+" where id= "+v.getId()+"and version= "+v.getVersion();
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		long id = 0;
		
		try {
			conn = dbLoader.getDs().getConnection();
			statement = conn.createStatement();
			
			logger.trace("query = "+query);
			
			logger.trace("trying to execute :\n" + query);
			int rowsUpdated = statement.executeUpdate(query);
			 
			logger.trace("query executed successfuly :\n" + query);
	
			result = rowsUpdated > 0;
			
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

		return result;
	}

}
