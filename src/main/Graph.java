package main;

import java.util.Map;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;


public class Graph {
	private final Map <Node, List<Edge>> DAG;
	
	public Graph() {
		DAG = new HashMap<Node, List<Edge>>();
	}
	
	/**
	 * add in the hash table the key newNode and creates 
	 * an empty list of edges
	 * @param newNode with the feature index and range
	 */
	public void addNode(Node newNode) {
	    DAG.putIfAbsent(newNode, new ArrayList<Edge>());
	}
	
	/**
	 * @param parent defines the key of the hash
	 * @param child node that is going to be saved inside object edge
	 * @param directed saves
	 */
	public void addEdge(Node parent, Node child, boolean directed) {
		Edge newEdge = new Edge(child, directed); 
	    DAG.get(parent).add(newEdge);
	}
	
	/*
	 * public void removeNode(Node a) { DAG.values().stream().forEach(e ->
	 * e.remove(a)); DAG.remove(a); }
	 */
	
	@Override
	public boolean equals(Object obj) {
		//same position in memory
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		//different class
		if (getClass() != obj.getClass())
			return false;
		//cast to Graph class
		Graph other = (Graph) obj;
		return Objects.equals(DAG, other.DAG);
	}
	
	/**
	 * 
	 * @return Returns the full graph
	 */
	public Map<Node, List<Edge>> getDAG() {
		return DAG;
	}

	@Override
	public int hashCode() {
		return Objects.hash(DAG);
	}
	
	@Override
	public String toString() {
		String listS = new String("Graph \n");
			
		for (Node N: DAG.keySet()){
			listS += N.toString() + "=" + DAG.get(N).toString() + "\n";
		} 
		
		return listS;
	}
}
