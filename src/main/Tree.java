package main;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Tree extends Graph{
	
	Node root;
	private Map <Node, List<Edge>> auxDAG;
	
	public Tree( Map<Node, List<Edge>> auxDAG, Node classNode ) {
		super();
		super.classNode = classNode;
		this.auxDAG = auxDAG;
	}

	public void applyPrim() {
		
		Map.Entry<Node,List<Edge>> firstEntry = auxDAG.entrySet().stream().findFirst().get();
		Set<Node> keys = auxDAG.keySet();
		
		
		Node root = firstEntry.getKey();
		Node parent = root;		
		Node child = root;

			
		root.setVisited(true);	
		super.addNode(root);
		
		while (isDisconnected()) {
			Edge maximumEdge = new Edge(null, 0);
			Edge candidateEdge = new Edge(null, 0);
			
			for (Node key : keys) {
				if (key.isVisited()) {
					candidateEdge = getMaximum(auxDAG.get(key), key);
					
					if (candidateEdge.getWeight() >= maximumEdge.getWeight()) {
						parent = key;
						child = candidateEdge.getChild();
						maximumEdge = candidateEdge;
	                }
	        	}
	        }
						
			child.setVisited(true);	
			maximumEdge.setConnected(true);
			
			super.addEdge(parent, child, maximumEdge.getWeight());
			super.addNode(child);
		}
	}
	
	private Edge getMaximum(List<Edge> edges, Node parent) {
		
		double maximumWeigth = 0;
		Edge maximumEdge = null;
		
		for(Edge edge: edges) {
			
			if(!edge.getChild().isVisited() && !edge.isConnected()){
				if(edge.getWeight() >= maximumWeigth) {
					maximumWeigth = edge.getWeight();
					maximumEdge = edge;
				}
			}
		}
	
		return maximumEdge;

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
	
	public void createTAN(int s) {
		
		// Initialise nodes' counts
		Set<Node> keys = DAG.keySet();
		
		classNode.theta_c = new double [s+1];	

		super.addNode(classNode);
		
		//Runs every node as parent
		for (Node key : keys) {
			super.addEdge(classNode, key, -1);
		}

	}
		
	/**
	 * Function that calculates the thetas 
	 */
	public void calcThetas() {
		
		// Initialise nodes' counts
		Set<Node> keys = DAG.keySet();
		double Nlinha = 0.5;
		int s = classNode.getRange();
		
		calcThetaC(super.TrainData.get_N());
		
		//Runs every node as parent
		for (Node key : keys) {
			//Runs every child of the parent node
			for(int i = 0; i < DAG.get(key).size(); i++) {
				Node child = DAG.get(key).get(i).getChild();
				
				// Initialises the theta in the child node
				child.theta = new double [key.getRange()+1][child.getRange()+1][s+1];
				
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
	}
	private void calcThetaC(int N) {
		
		double Nlinha = 0.5;
		int s = classNode.getRange()+1;
		
	 	for ( int c = 0; c < s; c++ ){
	 		int Nc = classNode.Nc[c];
	 		classNode.theta_c[c] = ( Nc + Nlinha )/( N + s*Nlinha);
		}
	}

	@Override
	public String toString() {
		String listS = new String("Tree \n");
			
		for (Node N: DAG.keySet()){
			listS += N.toString() + "=" + DAG.get(N).toString() + "\n";
		} 
		
		return listS;
	}
}
