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

import commun.DBLoader;
import dao.IVehiculeDao;
import db.Parcours;
import db.Vehicule;

@ManagedBean(name="vehiculeDao", eager=true)
@ApplicationScoped

public class VehiculeDao implements IVehiculeDao {

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
	public Long delete(Vehicule vehicule) {
		// TODO Auto-generated method stub
		return 0l;
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
			
			System.out.println("trying to execute :\n" + query);
			System.out.println(vehiculeId);
			rs = statement.executeQuery();
			System.out.println("query executed successfuly :\n" + query);
			System.out.println(vehiculeId);

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
			System.out.println(error+this.getClass().getName());
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
			
			System.out.println("trying to execute :\n" + query);

			rs = statement.executeQuery(query);
			System.out.println("query executed successfuly :\n" + query);
		
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
			System.out.println(error+this.getClass().getName());
		} finally {
			try {rs.close();} catch (Exception e){}
			try {statement.close();} catch (Exception e){}
			try {conn.close();} catch (Exception e){}
		}
		
		return result;	
	}

	@Override
	public void update(Vehicule v) {
		String query = "update sieltec.vehicule set immatriculation ='"+v.getImmatriculation()+"',version= "+(v.getVersion()+1)+" where id= "+v.getId()+"and version= "+v.getVersion();
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		long id = 0;
		
		try {
			conn = dbLoader.getDs().getConnection();
			statement = conn.createStatement();
			
			System.out.println("query = "+query);
			
			System.out.println("trying to execute :\n" + query);
			statement.executeUpdate(query);
			 
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

	}

}
