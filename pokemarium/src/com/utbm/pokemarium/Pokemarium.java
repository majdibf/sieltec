package com.utbm.pokemarium;

import static com.utbm.pokemarium.POK_Constantes.MAIN_SLEEP;
import static com.utbm.pokemarium.POK_Constantes.NB_POKEMON;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.utbm.pokemarium.POK_Constantes.ZONE;


public class Pokemarium {
	
	private static Random randomizer = new Random();
	static int  nbLigne=0;
	static int nbColonne=0;

	public static void main(String[] args) {
	
		
		HashMap<String, Case> map = new HashMap<>();
		
		try{
			File fileXml=new File("data/map.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fileXml);
			
			doc.getDocumentElement().normalize();
			
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			nbLigne=Integer.parseInt(doc.getDocumentElement().getAttributeNode("nbLigne").getValue());
			nbColonne=Integer.parseInt(doc.getDocumentElement().getAttributeNode("nbColonne").getValue());

			System.out.println("nbLigne = "+ nbLigne);
			System.out.println("nbColonne = "+ nbColonne);
			
			
			NodeList nodeListLignes = doc.getElementsByTagName("ligne");
			
			System.out.println("----------------------------");
			
			for (int i = 0; i < nodeListLignes.getLength(); i++) {
				Node currentNodeLigne = nodeListLignes.item(i);
		
				if(currentNodeLigne.getNodeType()==Node.ELEMENT_NODE){
					
					if (currentNodeLigne.hasChildNodes()){
						 NodeList nodeListCases=currentNodeLigne.getChildNodes();
						 
						for (int j = 0; j < nodeListCases.getLength(); j++) {
							Node currentNodeCase = nodeListCases.item(j);
							if (currentNodeCase.getNodeType() == Node.ELEMENT_NODE) {

								NamedNodeMap nodeAttributs = currentNodeCase.getAttributes();
								Node nodeZone = nodeAttributs.getNamedItem("zone");
								Node nodeLigne = nodeAttributs.getNamedItem("ligne");
								Node nodeColonne = nodeAttributs.getNamedItem("colonne");

								String key = nodeLigne.getNodeValue() + ":" + nodeColonne.getNodeValue();

								Case c = new Case(key);
								c.setZone(nodeZone.getNodeValue());
								map.put(key, new Case(key));
							}

						}
					}

				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		List<Pokemon> pokList = new ArrayList<>();
		List<String> keyList = new ArrayList<>();
		
		for(int i = 1; i <= NB_POKEMON; i++){
			Pokemon pokemon = new PokemonTerre("pokTerre" + i, map);
			pokList.add(pokemon);

			boolean isNewKey = false;
			String key = null;
			
			while ( !isNewKey ) {
				int x = randomizer.nextInt(nbLigne) + 1;
				int y = randomizer.nextInt(nbColonne) + 1;
				key = "" + x + ":" + y;
				if(!keyList.contains(key) && map.get(key).getZone() != ZONE.VIDE){
					isNewKey=true;
				}else{
					isNewKey=false;
				}			
			}
			
				keyList.add(key);
				pokemon.setCaseKey(key);
				map.get(key).setOccupant(pokemon);
				System.out.println(pokemon.getName() + " dans " + key);
		}

		for(int i = 1; i <= NB_POKEMON; i++){
			Pokemon pokemon = new PokemonHerbe("pokHerbe" + i, map);
			pokList.add(pokemon);

			boolean isNewKey = false;
			String key = null;
					
			while ( !isNewKey ) {
				int x = randomizer.nextInt(nbLigne) + 1;
				int y = randomizer.nextInt(nbColonne) + 1;
				key = "" + x + ":" + y;
				if(!keyList.contains(key) && map.get(key).getZone() != ZONE.VIDE){
					isNewKey=true;
				}else{
					isNewKey=false;
				}			
			}
			
				keyList.add(key);
				pokemon.setCaseKey(key);
				map.get(key).setOccupant(pokemon);
				System.out.println(pokemon.getName() + " dans " + key);
		}

		for(int i = 1; i <= NB_POKEMON; i++){
			Pokemon pokemon = new PokemonEau("pokEau" + i, map);
			pokList.add(pokemon);

			boolean isNewKey = false;
			String key = null;
					
			while ( !isNewKey ) {
				int x = randomizer.nextInt(nbLigne) + 1;
				int y = randomizer.nextInt(nbColonne) + 1;
				key = "" + x + ":" + y;
				if(!keyList.contains(key) && map.get(key).getZone() != ZONE.VIDE){
					isNewKey=true;
				}else{
					isNewKey=false;
				}			
			}
			
				keyList.add(key);
				pokemon.setCaseKey(key);
				map.get(key).setOccupant(pokemon);
				System.out.println(pokemon.getName() + " dans " + key);
		}

		for(int i = 1; i <= NB_POKEMON; i++){
			Pokemon pokemon = new PokemonFeu("pokFeu" + i, map);
			pokList.add(pokemon);

			boolean isNewKey = false;
			String key = null;
					
			while ( !isNewKey ) {
				int x = randomizer.nextInt(nbLigne) + 1;
				int y = randomizer.nextInt(nbColonne) + 1;
				key = "" + x + ":" + y;
				if(!keyList.contains(key) && map.get(key).getZone() != ZONE.VIDE){
					isNewKey=true;
				}else{
					isNewKey=false;
				}			
			}
			
				keyList.add(key);
				pokemon.setCaseKey(key);
				map.get(key).setOccupant(pokemon);
				System.out.println(pokemon.getName() + " dans " + key);
		}
		
		
		
		for(Pokemon pokemon : pokList){
			pokemon.start();
		}

		
		while(pokList.size() > 1){
			try{
				List<Pokemon> tmpPokList = new ArrayList<>();
				for(Pokemon pokemon : pokList){
					if(pokemon.getPointsVie() <= 0){
//						Case uneCase = map.get(pokemon.getCaseKey());
//						System.out.println("Main : " + pokemon.getName() + " mort, la case " + uneCase.getKey() + " libérée");
//						uneCase.setOccupant(null);
					} else {
						tmpPokList.add(pokemon);
					}
				}
				pokList = tmpPokList;
				System.err.println("\t\t\t\t\t\t\t\t\t\t\t\tMain : Il reste " + pokList.size() + " pokemons");
				Thread.sleep(MAIN_SLEEP);
			} catch (Exception e){
				System.out.println("ERROR");
			}
		}
		Pokemon pokemonVainqueur = pokList.get(0);
		pokemonVainqueur.stop();
		pokList.remove(0);
		System.out.println("Le champion est :" + pokemonVainqueur.getName() + " : " + pokemonVainqueur.getPointsVie());
		
	}

}
