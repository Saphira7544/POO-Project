package main;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Tree extends Graph{
	
	private Map <Node, List<Edge>> DAG;
	Set<Node> keys = DAG.keySet();
	
	public void applyPrim() {
		DAG = super.getDAG();
		
		
		//List<Edge> edges = new ArrayList<Edge>();
		Iterator<Map.Entry<Node,List<Edge>>> itr = DAG.entrySet().iterator();
		Map.Entry<Node,List<Edge>> entry;
		
		//while there are nodes that were not visited
		while (isDisconnected()) {
			
			while(itr.hasNext()){
				entry = itr.next();
				//getMaximum(entry.getValue());
			}
		}
				
		
	}
	private boolean isDisconnected() {
		
	    for (Node key : keys) {
	        if (!key.isVisited()) {
	            return true;
	        }
	    }
	    return false;
	}
}
