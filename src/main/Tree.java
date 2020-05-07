package main;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Tree extends Graph{
	
	Node root; 	
	
	public Tree( Map<Node, List<Edge>> DAG ) {
		super();
		super.DAG = DAG;
	}

	public void applyPrim() {
		Map <Node, List<Edge>> DAG = getDAG();
		
		Iterator<Map.Entry<Node,List<Edge>>> itr = DAG.entrySet().iterator();
		Map.Entry<Node,List<Edge>> entry = DAG.entrySet().iterator().next();
		Node root = entry.getKey();
		//Set<Node> keys = DAG.keySet();
		List<Edge> currEdges = entry.getValue();
		Node currNode = root;
		Node nextNode;
		
		while(currEdges.isEmpty()) {
			currEdges = entry.getValue();
			entry = itr.next();
		}
		
		root = entry.getKey();
		currEdges = entry.getValue();
		currNode = root;
						
		//while there are nodes that were not visited
		while (isDisconnected()) {
			
			currNode.setVisited(true);
			
			System.out.println("currNode" + currNode);
			
			nextNode = getMaximum(currEdges);
			
			if(nextNode == null) {
				currEdges = DAG.get(nextNode);
				currNode = nextNode;
			}

		}
	
		while(itr.hasNext()){
			entry = itr.next();
			currEdges = entry.getValue();
			
			for(Edge edge: currEdges) {
				
				if(!edge.isConnected()) {
					super.removeEdge(edge);
				}
			}
		}
	}
	
	private Node getMaximum(List<Edge> edges) {
		
		if(edges.isEmpty()) return null;

		System.out.println("currNode" + edges);
		
		float maximumWeigth = edges.get(0).getWeight();
		Edge maximumEdge = edges.get(0);
		
		for(Edge edge: edges) {
			System.out.println("currEdge" + edge);
			
			if(edge.getWeight() > maximumWeigth && !edge.isConnected()) {
				maximumWeigth = edge.getWeight();
				maximumEdge = edge;
			}
		}
		System.out.println("Returned Node" + maximumEdge.getChild());
		maximumEdge.setConnected(true);
		return maximumEdge.getChild();
	}
	
	private boolean isDisconnected() {
		Set<Node> keys = getDAG().keySet();
		
	    for (Node key : keys) {
	        if (!key.isVisited()) {
	            return true;
	        }
	    }
	    return false;
	}
}
