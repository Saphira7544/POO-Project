package main;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Tree extends Graph{
	
	Node root;
	private Map <Node, List<Edge>> auxDAG;
	
	public Tree( Map<Node, List<Edge>> auxDAG ) {
		super();
		this.auxDAG = auxDAG;
	}

	public void applyPrim() {
		
		Iterator<Map.Entry<Node,List<Edge>>> itr = auxDAG.entrySet().iterator();
		Map.Entry<Node,List<Edge>> entry = auxDAG.entrySet().iterator().next();
		
		Node root = entry.getKey();
		Node nextNode = root;
		List<Edge> nextEdges = entry.getValue();

		root.setVisited(true);
		super.addNode(root);
		
		while (isDisconnected()) {
			nextNode = getMaximum(nextEdges, nextNode);
			
			nextEdges = auxDAG.get(nextNode);
			
			nextNode.setVisited(true);	
			
			super.addNode(nextNode);
		}
	}
	
	private Node getMaximum(List<Edge> edges, Node parent) {
		
		double maximumWeigth = 0;
		Edge maximumEdge = null;
		
		for(Edge edge: edges) {
			
			if(!edge.getChild().isVisited() && !edge.isConnected()) {
				if(edge.getWeight() > maximumWeigth) {
					maximumWeigth = edge.getWeight();
					maximumEdge = edge;
				}
			}
		}
		
		maximumEdge.setConnected(true);
		super.addEdge(parent, maximumEdge.getChild(), maximumWeigth);
		
		return maximumEdge.getChild();
	}
	
	private boolean isDisconnected() {
		Set<Node> keys = auxDAG.keySet();
		
	    for (Node key : keys) {
	        if (!key.isVisited()) {
	            return true;
	        }
	    }
	    return false;
	}
}
