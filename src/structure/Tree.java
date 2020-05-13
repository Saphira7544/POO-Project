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
	/**
	 * Function that calls all the functions that create the tree
	 * @param TrainData : contains the entire data from the train file
	 */
	public void doTree(TrainSet TrainData) {
		this.TrainData = TrainData;
		applyPrim();
		createTAN(TrainData.getClassRange());
		calcThetas();
	}
	
	/**
	 * 
	 */
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
		// Initialises the Theta_C for the Class Node
		classNode.theta_c = new double [s+1];	
		
		// Adds the Class Node to the tree
		super.addNode(classNode);
		
		// Creates an Edge between every node
		for (Node key : keys) {
			super.addEdge(classNode, key, -1);
		}
	}
	
	/**
	 * Function that computes all the thetas for the final tree in all the possible situations
	 * The situations include the root and all the sets of parent/child, also calls the function
	 * that computes the Theta_C for the class node.
	 * Stores every Theta in the parent node, same as done before for computing the Ns.
	 */
	public void calcThetas() {

		// Initialise nodes' counts
		Set<Node> keys = super.DAG.keySet();
		double Nlinha = 0.5;
		int s = classNode.getRange();
		calcThetaC();
		
		//Runs every node as parent
		for (Node key : keys) {
			// Initialises the size of theta in the parent node
			//			 	nr. of possible children    range of parent    child range	    class range
			key.theta = new double [TrainData.get_n()+1][key.getRange()+5][key.getRange()+5][s+1];
			// Class node now belongs in the keys, we need to stop the loop when it finds the class node
			if(key.getIndex() == -1) {	// since the class node has index -1, it's always in the end
				break;
			}	
			
			// if the current node is the root
			if(key.equals(root)) {				
				for( int k = 0; k < key.getRange()+1; k++ ) {
					for( int c = 0; c <= s; c++ ) {
						// Since it has no father Nijkc is the same as just Nkc
						double Nijkc = key.Nijc[k][c];				
						// Since it has no father Nijc is the same as calculating just Nc
						double Nijc_K = classNode.Nc[c];
						/* Puts the theta for the root in position [root.index][root.index] since it has no
						father and here we're not accounting for children*/
						key.theta[key.getIndex()][key.getIndex()][k][c] = (Nijkc + Nlinha) / (Nijc_K + (key.getRange()+1)*Nlinha);	
						System.out.println("Theta["+(key.getIndex()+1)+"]["+(key.getIndex()+1)+"]["+(k+1)+"]["+(c+1)+"] = " + key.theta[key.getIndex()][key.getIndex()][k][c]);
					}
				}					
			}
			
			//Runs every child of the parent node
			for(int i = 0; i < DAG.get(key).size(); i++) {
				
				Node child = DAG.get(key).get(i).getChild();
								
				int qi = key.getRange(); // Parent's range
				double ri = child.getRange();	// Child's range
		
				for( int j = 0; j < qi+1; j++ ) { // Parent's range
					
					for( int k = 0; k < ri+1; k++ ) { // Child's range
	
						for( int c = 0; c <= s; c++ ) { // Class range
		
							double Nijkc = key.Nijkc[child.getIndex()][j][k][c];							
							double Nijc_K = key.Nijc[j][c];
							//
							key.theta[child.getIndex()][j][k][c] = (double)(Nijkc + Nlinha) / (double)(Nijc_K + (double)(ri+1)*(double)Nlinha);								
							System.out.println("Theta["+(child.getIndex()+1)+"]["+(j+1)+"]["+(k+1)+"]["+(c+1)+"] = " + key.theta[child.getIndex()][j][k][c]);

						}						
					}
				} 
			}
		}
	}
	/**
	 * Function that computes the Theta_C value for the Class Node
	 */
	private void calcThetaC() {
		double N = TrainData.get_N();
		double Nlinha = 0.5;
		double s = classNode.getRange()+1;
		
	 	for ( int c = 0; c < s; c++ ){
	 		double Nc = classNode.Nc[c];
	 		// Updates the Theta_C in the Class Node
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
	
	/**
	 * Getter for the root of the tree
	 * @return : Returns the node that is the root of the tree
	 */
	public Node getRoot() {
		return root;
	}
}
