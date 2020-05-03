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
	
	public void addNode(Node newNode) {
	    DAG.putIfAbsent(newNode, new ArrayList<>());
	}
	
	/*
	 * public void removeNode(Node a) { DAG.values().stream().forEach(e ->
	 * e.remove(a)); DAG.remove(a); }
	 */
	
	public void addEdge(Node parent, Node child, boolean directed) {
		Edge newEdge = new Edge(child, directed); 
	    DAG.get(parent).add(newEdge);
	}
	
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
