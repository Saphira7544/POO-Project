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
	
	/**
	 * Function that calculates the thetas 
	 */
	public void calcThetas() {
		
		// Initialise nodes' counts
		Set<Node> keys = DAG.keySet();
		double Nlinha = 0.5;
		int s = TrainData.getClassRange();
		
		//Runs every node as parent
		for (Node key : keys) {
			//Runs every child of the parent node
			for(int i = 0; i < DAG.get(key).size(); i++) {
				Node child = DAG.get(key).get(i).getChild();
				
				// Initialises the theta in the child node
				child.theta = new double [key.getRange()][child.getRange()][s];
				
				
				int qi = key.getRange(); // Parent's range
				int ri = DAG.get(key).get(i).getChild().getRange();	// Child's range
	
				for( int j = 0; j < qi+1; j++ ) { 
					
					for( int k = 0; k < ri+1; k++ ) {
	
						for( int c = 0; c <= s; c++ ) {
							
							int Nijkc = child.Nijkc[child.getRange()][j][k][c];
							int Nijc_K = child.Nijc[j][c];
							child.theta[j][k][c] = (Nijkc + Nlinha) / (Nijc_K + ri*Nlinha);
						
							
						}
					}
				}
			}
		}
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// COLOCAR AQUI O UPDATE DO THETA_C
		// PARA O CLASS NODE CRIADO PARA SER
		// PAI DE TODA A TREE
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		/* 
	 	int N = TrainData.get_N();
	 	for ( int c = 0; c <= s; c++ ){
	 		int Nc = ClassNode.Nc[c];
			classNode.theta_c = ( Nc + Nlinha )/( N + s*Nlinha);
		}
		*/
	}
	
}
