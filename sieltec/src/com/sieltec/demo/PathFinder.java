package com.sieltec.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.spi.DirStateFactory.Result;

import org.joda.time.DateTime;
import org.joda.time.Minutes;

import com.sieltec.db.ElementParcours;
import com.sieltec.db.ElementProgramme;
import com.sieltec.db.Ligne;
import com.sieltec.db.Parcours;
import com.sieltec.db.Programme;
import com.sieltec.db.Station;

public class PathFinder {
	private  double id = 1;
	private  List<Station> stations = new ArrayList<Station>();
	private  List<Ligne> lignes = new ArrayList<Ligne>();
	private  List<Parcours> parcours = new ArrayList<Parcours>();
	private  List<ElementParcours> elementsParcours = new ArrayList<ElementParcours>();
	private  List<Programme> programmes = new ArrayList<Programme>();
	private  List<ElementProgramme> elementsProgramme = new ArrayList<ElementProgramme>();
	
	
	private  double newId(){
		return id++;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String startStation = "1";
		String endStation = "24";
		if(args.length == 2){
			startStation = args[0];
			endStation = args[1];
		}

		PathFinder finder = new PathFinder();
		List<ElementProgramme> bestPath = finder.findBestPath(finder.stations.get(Integer.parseInt(startStation)), finder.stations.get(Integer.parseInt(endStation)), new DateTime(2013, 01, 05, 07, 52), finder.elementsProgramme);
		
		
		for(ElementProgramme elemResultat : bestPath){
			System.out.println(elemResultat);
		}
		
		System.out.println("end");
	}
	
	
	
	public PathFinder() {
		init();
	}

	private void init(){
		
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
	
	public List<ElementProgramme> findBestPath(Station staDep, Station staArr, DateTime dateHeurDepart, List<ElementProgramme> elementsProgramme){
		List<ElementProgramme> result = new ArrayList<ElementProgramme>();
		
		Map<Station, DateTime> initialStationsMap = new HashMap<Station, DateTime>();
		Map<Station, DateTime> finalStationsMap = new HashMap<Station, DateTime>();
		Map<Station, ElementProgramme> stationsPredecessorsMap = new HashMap<Station, ElementProgramme>();
		
		for(Station station : stations){
			if(station == null){
				continue;
			}
			if(station.getId() == staDep.getId()){
				initialStationsMap.put(station, dateHeurDepart);
			} else {
				initialStationsMap.put(station, dateHeurDepart.plus(Minutes.MAX_VALUE));
			}
		}
		
		List<ElementProgramme> candidats = null;
		while(!initialStationsMap.isEmpty()){
		
			Station minStation = getMinStation(initialStationsMap);
			DateTime minTime = initialStationsMap.get(minStation);
			finalStationsMap.put(minStation, minTime);
			initialStationsMap.remove(minStation);
			candidats = getCandidats(minStation, dateHeurDepart, elementsProgramme);
			updateVoisins(candidats, initialStationsMap, stationsPredecessorsMap);
			
		}
		
		Station sta = staArr;
		boolean stop = false;
		while(!stop && !sta.equals(staDep)){
			ElementProgramme element = stationsPredecessorsMap.get(sta);
			if(element != null){
				result.add(0,element);
				sta = element.getStationDep();
			} else {
				stop = true;
			}
		}

		System.out.println(finalStationsMap.get(staArr));
		return result;
	}
	
	
	private static void updateVoisins(List<ElementProgramme> candidats, Map<Station, DateTime> stationsMap, Map<Station, ElementProgramme> stationsPredecessorsMap){
		
		for(ElementProgramme element : candidats){
			
			Station stationArr = element.getStationArr();
			DateTime dateHeureArr = element.getDateHeureArrivee();
			DateTime oldDateTime = stationsMap.get(stationArr);
			if(oldDateTime != null && dateHeureArr.isBefore(oldDateTime)){
				stationsMap.put(stationArr, dateHeureArr);
				stationsPredecessorsMap.put(stationArr, element);
			}
			
		}
		
	}
	
	private static Station getMinStation(Map<Station, DateTime> map){
		Station result = null;
		DateTime min = null;
		Set<Station> stations = map.keySet();
		
		for(Station station : stations){
			DateTime time = map.get(station);
			if(min == null || time.isBefore(min)){
				result = station;
				min = time;
			}
		}
		
		
		return result;
	}
	
	private static List<ElementProgramme> getCandidats(Station station, DateTime dateHeure, List<ElementProgramme> elementsProgramme){
		
		List<ElementProgramme> resultat = new ArrayList<ElementProgramme>(); 
		
		for(ElementProgramme element : elementsProgramme){
			if(element.getStationDep().getId() == station.getId() && element.getDateHeureDepart().isAfter(dateHeure)){
				resultat.add(element);
			}
		}
		
		return resultat;
	}

	public List<Station> getStations() {
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}

	public List<ElementProgramme> getElementsProgramme() {
		return elementsProgramme;
	}

	public void setElementsProgramme(List<ElementProgramme> elementsProgramme) {
		this.elementsProgramme = elementsProgramme;
	}

	
	
}
