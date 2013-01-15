package commun;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.sql.DataSource;

import org.joda.time.DateTime;
import org.joda.time.Minutes;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

import db.ElementParcours;
import db.ElementProgramme;
import db.Ligne;
import db.Parcours;
import db.Programme;
import db.Station;

@ManagedBean(name="dbloader", eager=true)
@ApplicationScoped
public class DBLoader {

	private  double id = 1;
	private  List<Station> stations = new ArrayList<Station>();
	private  List<Ligne> lignes = new ArrayList<Ligne>();
	private  List<Parcours> parcours = new ArrayList<Parcours>();
	private  List<ElementParcours> elementsParcours = new ArrayList<ElementParcours>();
	private  List<Programme> programmes = new ArrayList<Programme>();
	private  List<ElementProgramme> elementsProgramme = new ArrayList<ElementProgramme>();
	private DataSource ds = null;

	public DBLoader() {
		super();
		init();
		
	}

	public double getId() {
		return id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public List<Station> getStations() {
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}

	public List<Ligne> getLignes() {
		return lignes;
	}

	public void setLignes(List<Ligne> lignes) {
		this.lignes = lignes;
	}

	public List<Parcours> getParcours() {
		return parcours;
	}

	public void setParcours(List<Parcours> parcours) {
		this.parcours = parcours;
	}

	public List<ElementParcours> getElementsParcours() {
		return elementsParcours;
	}

	public void setElementsParcours(List<ElementParcours> elementsParcours) {
		this.elementsParcours = elementsParcours;
	}

	public List<Programme> getProgrammes() {
		return programmes;
	}

	public void setProgrammes(List<Programme> programmes) {
		this.programmes = programmes;
	}

	public List<ElementProgramme> getElementsProgramme() {
		return elementsProgramme;
	}

	public void setElementsProgramme(List<ElementProgramme> elementsProgramme) {
		this.elementsProgramme = elementsProgramme;
	}


	private void init(){
		
		
		
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			DataSource unpooled = DataSources.unpooledDataSource("jdbc:derby://localhost:1527/sieltecdb","sieltec","sieltec");
			ds = DataSources.pooledDataSource( unpooled );		
			String query = "select * from station";
			Connection conn = ds.getConnection();
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) {
				double id = rs.getDouble("id");
				System.out.println(id + " " + rs.getString("nom"));
			}
			
			conn.close();
		} catch (Exception e) {
			
			System.out.println("Problème d'initialisation du pool de connexion : " + e);
			e.printStackTrace();
		} finally {
		}
		
		
		//initialisation des stations
		stations.add(null);
		for(int i = 1; i <= 24; i++){
			Station s = new Station(newId(), "station_" + i, "longitude_" + i, "latitude_" + i, 0);
			stations.add(s);
		}
		
	
		Ligne l = new Ligne(newId(), "bleu", 0);
		lignes.add(l);		
		Parcours p = new Parcours(newId(), "bleu_aller", l, 0);
		parcours.add(p);
		Programme prog = new Programme(newId(), new DateTime(2013, 1, 5, 8, 0), p, null, null, 0);
		programmes.add(prog);
		ElementParcours ep = new ElementParcours(newId(), p, stations.get(1), stations.get(2), Minutes.minutes(22), Minutes.minutes(3), 0);
		elementsParcours.add(ep);
		ep = new ElementParcours(newId(), p, stations.get(2), stations.get(3), Minutes.minutes(28), Minutes.minutes(2), 0);
		elementsParcours.add(ep);
		ep = new ElementParcours(newId(), p, stations.get(3), stations.get(4), Minutes.minutes(45), Minutes.minutes(0), 0);
		elementsParcours.add(ep);
		
		
		l = new Ligne(newId(), "rouge", 0);
		lignes.add(l);
		p = new Parcours(newId(), "rouge_aller", l, 0);
		parcours.add(p);
		prog = new Programme(newId(), new DateTime(2013, 1, 5, 8, 5), p, null, null, 0);
		programmes.add(prog);
		ep = new ElementParcours(newId(), p, stations.get(1), stations.get(5), Minutes.minutes(5), Minutes.minutes(2), 0);
		elementsParcours.add(ep);
		ep = new ElementParcours(newId(), p, stations.get(5), stations.get(6), Minutes.minutes(5), Minutes.minutes(2), 0);
		elementsParcours.add(ep);
		ep = new ElementParcours(newId(), p, stations.get(6), stations.get(7), Minutes.minutes(13), Minutes.minutes(2), 0);
		elementsParcours.add(ep);
		ep = new ElementParcours(newId(), p, stations.get(7), stations.get(8), Minutes.minutes(26), Minutes.minutes(3), 0);
		elementsParcours.add(ep);
		ep = new ElementParcours(newId(), p, stations.get(8), stations.get(9), Minutes.minutes(7), Minutes.minutes(0), 0);
		elementsParcours.add(ep);
	

		l = new Ligne(newId(), "marron", 0);
		lignes.add(l);
		p = new Parcours(newId(), "marron_retour", l, 0);
		parcours.add(p);
		prog = new Programme(newId(), new DateTime(2013, 1, 5, 8, 12), p, null, null, 0);
		programmes.add(prog);
		ep = new ElementParcours(newId(), p, stations.get(5), stations.get(2), Minutes.minutes(6), Minutes.minutes(5), 0);
		elementsParcours.add(ep);
		ep = new ElementParcours(newId(), p, stations.get(2), stations.get(14), Minutes.minutes(7), Minutes.minutes(2), 0);
		elementsParcours.add(ep);
		ep = new ElementParcours(newId(), p, stations.get(14), stations.get(15), Minutes.minutes(38), Minutes.minutes(3), 0);
		elementsParcours.add(ep);
		ep = new ElementParcours(newId(), p, stations.get(15), stations.get(16), Minutes.minutes(17), Minutes.minutes(0), 0);
		elementsParcours.add(ep);
		
	
		l = new Ligne(newId(), "violet", 0);
		lignes.add(l);
		p = new Parcours(newId(), "violet_aller", l, 0);
		parcours.add(p);
		prog = new Programme(newId(), new DateTime(2013, 1, 5, 8, 20), p, null, null, 0);
		programmes.add(prog);
		ep = new ElementParcours(newId(), p, stations.get(10), stations.get(7), Minutes.minutes(8), Minutes.minutes(2), 0);
		elementsParcours.add(ep);
		ep = new ElementParcours(newId(), p, stations.get(7), stations.get(11), Minutes.minutes(8), Minutes.minutes(2), 0);
		elementsParcours.add(ep);
		ep = new ElementParcours(newId(), p, stations.get(11), stations.get(3), Minutes.minutes(10), Minutes.minutes(2), 0);
		elementsParcours.add(ep);
		ep = new ElementParcours(newId(), p, stations.get(3), stations.get(15), Minutes.minutes(8), Minutes.minutes(3), 0);
		elementsParcours.add(ep);
		ep = new ElementParcours(newId(), p, stations.get(15), stations.get(21), Minutes.minutes(17), Minutes.minutes(2), 0);
		elementsParcours.add(ep);
		ep = new ElementParcours(newId(), p, stations.get(21), stations.get(24), Minutes.minutes(8), Minutes.minutes(0), 0);
		elementsParcours.add(ep);

	
		l = new Ligne(newId(), "vert", 0);
		lignes.add(l);
		p = new Parcours(newId(), "vert_retour", l, 0);
		parcours.add(p);
		prog = new Programme(newId(), new DateTime(2013, 1, 5, 8, 0), p, null, null, 0);
		programmes.add(prog);
		ep = new ElementParcours(newId(), p, stations.get(9), stations.get(8), Minutes.minutes(3), Minutes.minutes(2), 0);
		elementsParcours.add(ep);
		ep = new ElementParcours(newId(), p, stations.get(8), stations.get(11), Minutes.minutes(3), Minutes.minutes(2), 0);
		elementsParcours.add(ep);
		ep = new ElementParcours(newId(), p, stations.get(11), stations.get(12), Minutes.minutes(3), Minutes.minutes(2), 0);
		elementsParcours.add(ep);
		ep = new ElementParcours(newId(), p, stations.get(12), stations.get(2), Minutes.minutes(4), Minutes.minutes(2), 0);
		elementsParcours.add(ep);
		ep = new ElementParcours(newId(), p, stations.get(2), stations.get(13), Minutes.minutes(9), Minutes.minutes(2), 0);
		elementsParcours.add(ep);
		ep = new ElementParcours(newId(), p, stations.get(13), stations.get(18), Minutes.minutes(18), Minutes.minutes(2), 0);
		elementsParcours.add(ep);
		ep = new ElementParcours(newId(), p, stations.get(18), stations.get(23), Minutes.minutes(16), Minutes.minutes(0), 0);
		elementsParcours.add(ep);

	
		l = new Ligne(newId(), "noir", 0);
		lignes.add(l);
		p = new Parcours(newId(), "noir_aller", l, 0);
		parcours.add(p);
		prog = new Programme(newId(), new DateTime(2013, 1, 5, 8, 40), p, null, null, 0);
		programmes.add(prog);
		ep = new ElementParcours(newId(), p, stations.get(17), stations.get(18), Minutes.minutes(10), Minutes.minutes(2), 0);
		elementsParcours.add(ep);
		ep = new ElementParcours(newId(), p, stations.get(18), stations.get(19), Minutes.minutes(8), Minutes.minutes(2), 0);
		elementsParcours.add(ep);
		ep = new ElementParcours(newId(), p, stations.get(19), stations.get(20), Minutes.minutes(6), Minutes.minutes(2), 0);
		elementsParcours.add(ep);
		ep = new ElementParcours(newId(), p, stations.get(20), stations.get(21), Minutes.minutes(8), Minutes.minutes(5), 0);
		elementsParcours.add(ep);
		ep = new ElementParcours(newId(), p, stations.get(21), stations.get(22), Minutes.minutes(2), Minutes.minutes(0), 0);
		elementsParcours.add(ep);
		
		for(Programme progr : programmes){
			elementsProgramme.addAll(executeProgramme(progr, elementsParcours));
		}
		
	}

	
	public double newId(){
		return id++;
	}
	
	private static List<ElementProgramme> executeProgramme(Programme prog, List<ElementParcours> allElementsParcours){
		List<ElementProgramme> result = new ArrayList<ElementProgramme>();
		Parcours parcours = prog.getParcours();
		List<ElementParcours> elementsParcours = new ArrayList<ElementParcours>();
		for(ElementParcours elemParc : allElementsParcours){
			if(elemParc.getParcours().getId() == parcours.getId()){
				elementsParcours.add(elemParc);
			}
		}
		
		elementsParcours = trierElementsParcours(elementsParcours);
		
		DateTime dateHeureDepart = prog.getDateHeureDebut();
		for(ElementParcours elemPar : elementsParcours){
			ElementProgramme elPr = new ElementProgramme(elemPar.getStationDep(), elemPar.getStationArr(), dateHeureDepart, dateHeureDepart.plusMinutes(elemPar.getDuree().getMinutes()), elemPar.getParcours());
			dateHeureDepart = elPr.getDateHeureArrivee().plusMinutes(elemPar.getDureeArret().getMinutes());
			result.add(elPr);
		}
		
		return result;
	}
	
	private static List<ElementParcours> trierElementsParcours(List<ElementParcours> elemParcours){
		List<ElementParcours> result = new ArrayList<ElementParcours>();
		
		ElementParcours firstEP =  elemParcours.remove(0);
		ElementParcours lastEP =  firstEP;
		result.add(firstEP);
		while(!elemParcours.isEmpty()){
			ElementParcours ep = elemParcours.remove(0);
			if(ep.getStationArr().getId() == firstEP.getStationDep().getId()){
				result.add(0, ep);
				firstEP = ep;
				continue;
			}
			if(ep.getStationDep().getId() == lastEP.getStationArr().getId()){
				result.add(ep);
				lastEP = ep;
				continue;
			}
			elemParcours.add(ep);
		}
		
		return result;
	}

	public DataSource getDs() {
		return ds;
	}

	public void setDs(DataSource ds) {
		this.ds = ds;
	}


	
	
}
