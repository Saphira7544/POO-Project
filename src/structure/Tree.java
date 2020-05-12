package structure;

import files.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Tree extends Graph{
	
	Node root;
	private Map <Node, List<Edge>> auxDAG;
	TrainSet TrainData;
	
	public Tree( Map<Node, List<Edge>> auxDAG, Node classNode ) {
		super();
		super.classNode = classNode;
		this.auxDAG = auxDAG;
	}

	public void applyPrim() {
		
		Map.Entry<Node,List<Edge>> firstEntry = auxDAG.entrySet().stream().findFirst().get();
		Set<Node> keys = auxDAG.keySet();
		
		
		root = firstEntry.getKey();
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
		this.TrainData = super.TrainData;
		// Initialise nodes' counts
		Set<Node> keys = DAG.keySet();
		double Nlinha = 0.5;
		int s = classNode.getRange();
		
		calcThetaC();
		
		//Runs every node as parent
		for (Node key : keys) {
			
			// Class node now belongs in the keys, we need to stop the loop when it finds the class node
			if(key.getIndex() == -1) {	// since the class node has index -1, it's always in the end
				break;
			}	
			
			if(key.equals(root)) {
				key.theta = new double [key.getIndex()+1][key.getIndex()+1][key.getRange()+1][s+1];
				
				for( int k = 0; k < key.getRange()+1; k++ ) {
					for( int c = 0; c <= s; c++ ) {
						int Nijkc = key.Nijkc[key.getIndex()][key.getIndex()][k][c];							
						int Nijc_K = classNode.Nc[c];//key.Nijc[j][c];
						
						key.theta[key.getIndex()][key.getIndex()][k][c] = (Nijkc + Nlinha) / (Nijc_K + (key.getRange()+1)*Nlinha);	
					}
				}					
			}
			
			//Runs every child of the parent node
			for(int i = 0; i < DAG.get(key).size(); i++) {
				
				Node child = DAG.get(key).get(i).getChild();
				// Initialises the size of theta in the parent node
				//				 nr. of possible children   range of parent   child range		  class range
				key.theta = new double [TrainData.get_n()+1][key.getRange()+1][child.getRange()+1][s+1];
				
				int qi = key.getRange(); // Parent's range
				int ri = child.getRange();	// Child's range
		
				for( int j = 0; j < qi+1; j++ ) { 
					
					for( int k = 0; k < ri+1; k++ ) {
	
						for( int c = 0; c <= s; c++ ) {
		
							int Nijkc = key.Nijkc[child.getIndex()][j][k][c];							
							int Nijc_K = key.Nijc[j][c];

							key.theta[child.getIndex()][j][k][c] = (Nijkc + Nlinha) / (Nijc_K + (ri+1)*Nlinha);								
							//System.out.println("Theta["+(child.getIndex()+1)+"]["+(j+1)+"]["+(k+1)+"]["+(c+1)+"] = "+ key.theta[child.getIndex()][j][k][c]);
							//System.out.println("	" + (key.getIndex()+1) + "  N [" + (child.getIndex()+1) + "][" + (j+1) + "][" + (k+1) + "][" + (c+1) + "] = " + Nijkc);
							//System.out.println( "	NijcK [" + (j+1) + "][" + (c+1) + "] = " + Nijc_K);
							//System.out.println("	" +  ri);
						
						
						}						
					}
				} 
			}
		}
	}
	private void calcThetaC() {
		int N = TrainData.get_N();
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
