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
	public Long insert(Parcours parcours) {
		// TODO Auto-generated method stub
		return 0l;
	}

	@Override
	public Long delete(Parcours parcours) {
		// TODO Auto-generated method stub
		return 0l;
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

				Long id = rs.getLong("id");
				String nom = rs.getString("nom");
				Long ligneId = rs.getLong("ID_LIGNE");
				int version = rs.getInt("version");
				p = new Parcours(id, nom, ligneId, version);

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

	@Override
	public Parcours find(Long parcoursId) {

		Parcours result = null;
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
		try {
			conn = dbLoader.getDs().getConnection();
			String query = "select * from parcours where id = ?";
			statement = conn.prepareStatement(query);
			statement.setLong(1, parcoursId);
			
			System.out.println("trying to execute :\n" + query);
			System.out.println(parcoursId);
			rs = statement.executeQuery();
			System.out.println("query executed successfuly :\n" + query);
			System.out.println(parcoursId);

			if (rs.next()) {
				Long id = rs.getLong("ID");
				String nom = rs.getString("NOM");
				Long ligneId = rs.getLong("ID_LIGNE");
				int version = rs.getInt("VERSION");
				result = new Parcours(id, nom, ligneId, version);
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
		
		return result;	}
}
