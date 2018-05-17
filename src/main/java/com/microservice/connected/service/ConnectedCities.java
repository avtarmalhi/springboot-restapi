package com.microservice.connected.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service("connectedService")
public class ConnectedCities {

	private static final String DELIMITER = ",";
	private static final String filename = "file/citiesList.txt";

	public static String areCitiesConnected(String origin,String destination) {
		
		String response = "No";
		if (origin == null || destination == null || origin.isEmpty() || destination.isEmpty()) {
			return null;
		} else {
			String city1 = origin;
			String city2 = destination;	
			Map<String, Set<String>> citiesToNodeMap;
			try {
				citiesToNodeMap = citiesToNodeConversion(filename);
				boolean result = areCitiesConnected(citiesToNodeMap, city1, city2);
				if (result) {
					response="Yes";
				} else {
					response="No";
				}
			} catch (Exception ex) {
				String message = ex.getMessage();
				System.err.println("Exception: " + message);
				return null;
			}
		}
		return response;
	}
	
	private static Map<String, Set<String>> citiesToNodeConversion(String filename) throws IOException {
		
		Map<String, Set<String>> cityNodesMap = new HashMap<String, Set<String>>();
		BufferedReader bufferedReader = null;
		try {
			ClassLoader classLoader = new ConnectedCities().getClass().getClassLoader();
			File file = new File(classLoader.getResource(filename).getFile());
			Reader fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String line = bufferedReader.readLine();
			while (line != null && !line.isEmpty()) {
				String[] cities = line.split(DELIMITER);
				String city1 = cities[0].trim();
				String city2 = cities[1].trim();

				Set<String> city1AdjNodes = findAdjacentNodes(cityNodesMap, city1);
				Set<String> city2AdjNodes = findAdjacentNodes(cityNodesMap, city2);
				city1AdjNodes.add(city2);
				city2AdjNodes.add(city1);

				line = bufferedReader.readLine();
			}
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}

		return cityNodesMap;
	}
	
	private static Set<String> findAdjacentNodes(Map<String, Set<String>> nodesMap, String city) {
		if (!nodesMap.containsKey(city)) {
			nodesMap.put(city, new HashSet<String>());
			}
			return nodesMap.get(city);
	}
	
	// Implemented Breadth First Search algorithm for traversing or 
	// searching a graph data structure made of nodes and edges
	// Case: Process any items in the queue, while also expanding their children, stop if the queue was empty. 
	// The general case will halt after processing the bottom level as leaf nodes have no children.
	private static boolean areCitiesConnected(Map<String, Set<String>> citiesToNodeMap, String city1, String city2) {
		boolean connFound = false;
		if (city1.equals(city2)) {
			connFound = true;
		}
		
		if (citiesToNodeMap.containsKey(city1) && citiesToNodeMap.containsKey(city2)) {
			
			Queue<String> citiesToVisit = new LinkedList<String>();
			Set<String> hasVisited = new HashSet<String>();

			citiesToVisit.add(city1);

			while (!citiesToVisit.isEmpty() && !connFound) {
				String city = citiesToVisit.poll();
				connFound = city.equals(city2);

				Set<String> nodesConnections = citiesToNodeMap.get(city);
				for (String connectedCity : nodesConnections) {
					if (!hasVisited.contains(connectedCity)) {
						citiesToVisit.add(connectedCity);
						hasVisited.add(connectedCity);
					}
				}
			}
		}

		return connFound;
	}
	
}
