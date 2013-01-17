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

import commun.DBLoader;
import dao.IVehiculeDao;
import db.Vehicule;

@ManagedBean(name="vehiculeDao", eager=true)
@ApplicationScoped

public class VehiculeDao implements IVehiculeDao {

	@ManagedProperty(value="#{dbloader}")
	private DBLoader dbLoader;

	@Override
	public double insert(Vehicule vehicule) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double delete(Vehicule vehicule) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Vehicule> findAll() {
		List<Vehicule> vehicules= new ArrayList<>();
		
		try{
			Connection ds=dbLoader.getDs().getConnection();
			Statement statement=ds.createStatement();
			
			String query="select * from vehicule";
			ResultSet rs= statement.executeQuery(query);
			
			Vehicule v;
			while(rs.next()){
				int id=rs.getInt("id");
				String immatriculation=rs.getString("immatriculation");
				int version=rs.getInt("version");
				v=new Vehicule(id, immatriculation, version);
				
				vehicules.add(v);
			}
		
		}catch(SQLException e){
			e.printStackTrace();
			String error = "erreur de connexion à la base de données";
			System.out.println(error + this.getClass().getName());
			
		}
		return vehicules;
	}
	
	public DBLoader getDbLoader() {
		return dbLoader;
	}

	public void setDbLoader(DBLoader dbLoader) {
		this.dbLoader = dbLoader;
	}
}
