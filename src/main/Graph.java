package main;

import java.util.Map;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public class Graph<T> {
	private final Map <Node<T>, List<Node<T>>> DAG;
	private final List<Edge<T>> allEdges;
	
	public Graph(LinkedList<Edge<T>> allEdges) {
		DAG = new HashMap<Node<T>, List<Node<T>>>();
		
		this.allEdges = allEdges;
		
		for(Edge<T> E: allEdges) {
			//check if parent is equal to child
			if(!E.getParent().equals(E.getChild())) {
				//created the list of Nodes that define the connected nodes
				DAG.putIfAbsent(E.getParent(), new ArrayList<Node<T>>());

				//check if already on the graph
				if(!DAG.get(E.getParent()).contains(E.getChild())) {
				
					//add the child to the list of connections after Linked List is created
					DAG.get(E.getParent()).add(E.getChild());
			
				}
			}
		}
	}
	
	public void applyKruskal() {
		
	}

	//getters
	public Map<Node<T>, List<Node<T>>> getDAG() {
		return DAG;
	}

	public List<Edge<T>> getAllEdges() {
		return allEdges;
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
		Graph<?> other = (Graph<?>) obj;
		return Objects.equals(DAG, other.DAG);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(DAG);
	}
	
	@Override
	public String toString() {
		String listS = new String("Graph \n");
			
		for (Node<T> N: DAG.keySet()){
			listS += N.toString() + "=" + DAG.get(N).toString() + "\n";
		} 
		
		return listS;
	}

	
}
