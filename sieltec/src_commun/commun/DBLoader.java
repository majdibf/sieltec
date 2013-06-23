package commun;



import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

	private Logger logger = LogManager.getLogger(this.getClass().getName());

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
		} catch (Exception e) {
			logger.error("Problème d'initialisation du pool de connexion : " + e);
			e.printStackTrace();
		} finally {
			// todo : ???
		}
				
	}

	
	
	public DataSource getDs() {
		return ds;
	}

	public void setDs(DataSource ds) {
		this.ds = ds;
	}

}
