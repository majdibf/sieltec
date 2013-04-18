package dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import commun.DBLoader;

import dao.IStationDao;
import db.Station;

@ManagedBean(name = "stationDao", eager = true)
@ApplicationScoped
public class StationDao implements IStationDao, Serializable {

	@ManagedProperty(value = "#{dbloader}")
	private DBLoader dbLoader;

	public StationDao() {
		super();
		System.out.println("ManagementService instanciated");
	}

	public DBLoader getDbLoader() {
		return dbLoader;
	}

	public void setDbLoader(DBLoader dbLoader) {
		this.dbLoader = dbLoader;
	}

	@Override
	public Long insert(Station station) {
		String query = "insert into sieltec.Station(nom,longitude,latitude,version) values('"+station.getNom()+"','"+station.getLatitude()+"','"+station.getLongitude()+"',"+station.getVersion()+")";
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
	public Long delete(Station station) {
		// TODO Auto-generated method stub
		return 0l;
	}

	@Override
	public List<Station> findAll() {

		List<Station> stations = new ArrayList<Station>();
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;

		try {
			conn = dbLoader.getDs().getConnection();
			statement = conn.createStatement();

			String query = "select * from station";

			System.out.println("trying to execute :\n" + query);
			rs = statement.executeQuery(query);
			System.out.println("query executed successfuly :\n" + query);

			Station s;
			while (rs.next()) {
				Long id = rs.getLong("id");
				String nom = rs.getString("nom");
				String longitude = rs.getString("longitude");
				String latitude = rs.getString("latitude");
				int version = rs.getInt("version");
				s = new Station(id, nom, longitude, latitude, version);
				stations.add(s);
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

		return stations;
	}

	@Override
	public Station findByName(String name) {
		Station result = null;
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;

		try {
			conn = dbLoader.getDs().getConnection();
			statement = conn.createStatement();

			String query = "select * from station where lower(nom) = lower('"
					+ name + "')";

			System.out.println("trying to execute :\n" + query);
			rs = statement.executeQuery(query);
			System.out.println("query executed successfuly :\n" + query);

			if (rs.next()) {
				Long id = rs.getLong("id");
				String nom = rs.getString("nom");
				String longitude = rs.getString("longitude");
				String latitude = rs.getString("latitude");
				int version = rs.getInt("version");
				result = new Station(id, nom, longitude, latitude, version);
			} else {
				// pas de resultat
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

		return result;
	}

	@Override
	public HashMap<Long, Station> findByListId(List<Long> idList) {

		HashMap<Long, Station>stations=new HashMap<Long, Station>();

		
		String query = "select * from station where id IN()";
		Station st = null;
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;

		String inClause = "";
		
		if(idList==null || idList.isEmpty()){
			return stations;
		}
		
		for(Long id : idList){
			inClause = inClause + id + ",";
		}
		inClause = inClause.substring(0, inClause.length() - 1);
		query=query.replace("()", "(" + inClause + ")");
		
		
		try {
			conn = dbLoader.getDs().getConnection();
			statement = conn.createStatement();
			
			System.out.println("query = "+query);
			
			System.out.println("trying to execute :\n" + query);
			rs = statement.executeQuery(query);
			System.out.println("query executed successfuly :\n" + query);

			while (rs.next()) {
				Long id = rs.getLong("id");
				String nom = rs.getString("nom");
				String longitude = rs.getString("longitude");
				String latitude = rs.getString("latitude");
				int version = rs.getInt("version");
				st = new Station(id, nom, longitude, latitude, version);
				stations.put(id, st);
				
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

		return stations;
	}

	@Override
	public Station findById(Long stationDepId) {
		Station result = null;
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;

		try {
			conn = dbLoader.getDs().getConnection();
			statement = conn.createStatement();

			String query = "select * from station where id ="+ stationDepId;

			System.out.println("trying to execute :\n" + query);
			rs = statement.executeQuery(query);
			System.out.println("query executed successfuly :\n" + query);

			if (rs.next()) {
				Long id = rs.getLong("id");
				String nom = rs.getString("nom");
				String longitude = rs.getString("longitude");
				String latitude = rs.getString("latitude");
				int version = rs.getInt("version");
				result = new Station(id, nom, longitude, latitude, version);
			} else {
				// pas de resultat
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

		return result;

	}

	@Override
	public boolean update(Station s) {
		boolean result = false;
		String query = "update sieltec.station set nom ='"+s.getNom()+"',longitude='"+s.getLongitude()+"',latitude='"+s.getLatitude()+"',version= "+(s.getVersion()+1)+" where id= "+s.getId()+"and version= "+s.getVersion();
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			conn = dbLoader.getDs().getConnection();
			statement = conn.createStatement();
			
			System.out.println("query = "+query);
			
			System.out.println("trying to execute :\n" + query);
			int rowsUpdated =statement.executeUpdate(query);
			 
			System.out.println("query executed successfuly :\n" + query);
	
			result = rowsUpdated > 0;

			
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
		return result;
		
	}
}
