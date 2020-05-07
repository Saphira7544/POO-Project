package main;

//import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Tree extends Graph{
	
	Node root;
	protected Map <Node, List<Edge>> DAG;
	
	public Tree( Map<Node, List<Edge>> DAG ) {
		super();
		super.DAG = DAG;
	}

	public void applyPrim() {
		Map <Node, List<Edge>> DAG = getDAG();
		
		Map.Entry<Node,List<Edge>> entry = DAG.entrySet().iterator().next();
		
		Node root = entry.getKey();
		Node nextNode = root;
		List<Edge> nextEdges = entry.getValue();

		root.setVisited(true);
		
		while (isDisconnected()) {
			nextNode = getMaximum(nextEdges, nextNode);
			
			nextEdges = DAG.get(nextNode);
			
			nextNode.setVisited(true);	
			
			this.addNode(nextNode);
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
		this.addEdge(parent, maximumEdge.getChild(), maximumWeigth);
		
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
	
	//while there are nodes that were not visited
			/*while (isDisconnected()) {
				Edge nextMaximum = new Edge(null, false);
				nextMaximum.setWeight(0);
				
				while(itr.hasNext()) {
					if(entry.getKey().isVisited()) {
						Edge candidate =  getMaximum(entry.getValue());
						if(candidate.getWeight() > nextMaximum.getWeight() && candidate.getChild().equals(currEdge)) {
							nextMaximum = candidate;
							nextNode = candidate.getChild();
						}
					}
					entry = itr.next();
					System.out.println(entry);
				}
				
				currEdge = nextNode;
				nextMaximum.setConnected(true);
				nextNode.setVisited(true);
				itr = DAG.entrySet().iterator();
			}*/
}
