package main;

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
		
		Map.Entry<Node,List<Edge>> firstEntry = auxDAG.entrySet().stream().findFirst().get();
		
		Node root = firstEntry.getKey();
		List<Edge> nextEdges = firstEntry.getValue();
		
		Node nextNode = root;

		root.setVisited(true);
		super.addNode(root);
		
		while (isDisconnected()) {
			System.out.println(nextNode + "" + nextEdges);
			
			nextNode = getMaximum(nextEdges, nextNode);
			if(nextNode == null) break;
			
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
		
		if(maximumEdge != null) {
			maximumEdge.setConnected(true);
			super.addEdge(parent, maximumEdge.getChild(), maximumWeigth);
			
			return maximumEdge.getChild();
		}
		return null;
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
