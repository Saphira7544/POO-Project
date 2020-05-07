package main;


import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;



public class Graph {

	TrainSet TrainData;
	protected Map <Node, List<Edge>> DAG;
	
	/**
	 * Creates the Graph constituted by a HashMap of nodes, where each Node is pointing 
	 * to a list of all the possible edges
	 */
	public Graph() {
		this.DAG = new HashMap<Node, List<Edge>>();
	}
	
	/**
	 * Sets the train data to be used in the Graph class
	 * @param traindata : data from input argument Train File
	 */
	public void setTrainData(TrainSet traindata) {
		this.TrainData = traindata;
	}
	
	/**
	 * add in the hash table the key newNode and creates 
	 * an empty list of edges
	 * @param newNode with the feature index and range
	 */
	public void addNode(Node newNode) {
		
	    this.DAG.putIfAbsent(newNode, new ArrayList<Edge>());
	}
	
	/**
	 * @param parent defines the key of the hash
	 * @param child node that is going to be saved inside object edge
	 * @param directed saves
	 */
	public void addEdge(Node parent, Node child, double weight) {
		Edge newEdge = new Edge(child, weight); 
	    DAG.get(parent).add(newEdge);
	}
	
	public void removeEdge(Edge a) { 
		DAG.values().stream().forEach(e -> e.remove(a)); 
	}  
	  
	/*
	 * public void removeNode(Node a) { DAG.values().stream().forEach(e ->
	 * e.remove(a)); DAG.remove(a); }
	 */
	

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
		Graph other = (Graph) obj;
		return Objects.equals(DAG, other.DAG);
	}
	
	/**
	 * 
	 * @return Returns the full graph
	 */
	public Map<Node, List<Edge>> getDAG() {
		return DAG;
	}

	@Override
	public int hashCode() {
		return Objects.hash(DAG);
	}
	
	@Override
	public String toString() {
		String listS = new String("Graph \n");
			
		for (Node N: DAG.keySet()){
			listS += N.toString() + "=" + DAG.get(N).toString() + "\n";
		} 
		
		return listS;
	}
	
	/**
	 * Function that, for each node, considers the possibilities where it is the father and all 
	 * the remaining nodes are the son, and Initialises the several Ns for that case.
	 */
	public void updateNodeCounts()  {
	
		int nrXs = TrainData.get_n(); 
		int nrClass = TrainData.getClassRange();
		
		// Initialise nodes' counts
		Set<Node> keys = DAG.keySet();
		
		// Runs by each node considering it as the father
		for (Node key : keys) {
			key.Nijkc = new int[nrXs+1][][][];
		    // Runs every node considering it the son
			for (int j = 0; j < nrXs; j++) {
				// Initialise all possible values of Nijkc for each son
				
				key.Nijkc[j] = new int[TrainData.getRange(j)+1][key.getRange()+1][nrClass+1];
			}
			// Initialise all possible values of Nijc and Nc
			key.Nijc = new int[key.getRange()+1][nrClass+1];
			key.Nc= new int [nrClass+1];	
		}
		
		int[] Inst = new int[TrainData.get_n()];
		int C = 0;
		// For every line of the Train Data, increments the values of the corresponding N
		for (int k = 0; k < TrainData.get_N(); k++) {
		
			Inst = TrainData.getInstances().get(k).getArray();
			C = TrainData.getInstances().get(k).getClassVariable(); 
			
			
			
			updateNodeCountsFromInstance(Inst , C );	
			
		}
	}

	/**
	 * Function that increments the several Ns considering the given line and for every 
	 * possibility of Node with parent/son
	 * @param Inst : Instance, line of values from the Train Data
	 * @param C :  Class Variable from a certain line in the Train Data
	 */
	private void updateNodeCountsFromInstance( int[] Inst, int C) {

		int nrXs = TrainData.get_n();
		// Initialise nodes' counts
		Set<Node> keys = DAG.keySet();
		
		
		
		// Runs by each node considering it as the father
		for (Node key : keys) {
			
			key.Nc[C]++;
			
			// Runs every node considering it the son
			for (int j = 0; j < nrXs; j++) {
				
				if (key.getIndex() == j) { // case in which Xi has no parent. We will store this case in the position where node Xi is the parent of itself
					
					key.Nijkc[key.getIndex()][0][ Inst[key.getIndex()] ][C] ++;
					
					continue;
				}	
				key.Nijkc[j][ Inst[j] ][ Inst[key.getIndex()] ][C] ++;
				
			}
			key.Nijc[Inst[key.getIndex()]][C] ++;
			//System.out.println(aux2[Inst[key.getIndex()]][C]);
			//key.setNijc(aux2);		
			//key.setNijkc(aux);	
			//System.out.println(key.getNijc()[Inst[key.getIndex()]][C]);
		}	
	}
	
	public void createAllEdges() {

		Set<Node> keys1 = DAG.keySet();
		
		// Runs by each node considering it as the father
		for (Node key1 : keys1) {
			
			boolean found = false;
			Set<Node> keys2 = DAG.keySet();
			
			for (Node key2 : keys2) {
				// if it hasn't found key1, continues the for
				if (!found && !(key1.getKey()).equals(key2.getKey())) {
		            continue;
		        }
				
		        found = true;
		        addEdge(key1, key2, 0);	
			}
		}	
	}
	public void setAllWeights(ScoreModel scoreModel) {
		
		int N = TrainData.get_N();
		int s = TrainData.classRange;
		
		Iterator<Map.Entry<Node,List<Edge>>> itr = DAG.entrySet().iterator();
			
		while (itr.hasNext()) {
			
			Map.Entry<Node,List<Edge>> entry = itr.next();
			
			for(int i = 0; i < entry.getValue().size(); i++) {
							
				double weight = scoreModel.calc_weight(entry.getValue().get(i), entry.getKey(), N, s);
				entry.getValue().get(i).setWeight(weight);
			}	
		}
	}
	
	public void createCompleteGraph() {
		Edge edge;

		Set<Node> keys1 = DAG.keySet();
		Set<Node> keys2 = DAG.keySet();
		//Iterator<Map.Entry<Node,List<Edge>>> itr1 = DAG.entrySet().iterator();
		//Iterator<Map.Entry<Node,List<Edge>>> itr2 = DAG.entrySet().iterator();
		
		for (Node key1 : keys1){
			for (Node key2 : keys2){
				
				if ((key1).equals(key2)) {
					break;
				}				
				
				for(int i = 0; i < DAG.get(key2).size(); i++) {	
					
					edge = DAG.get(key2).get(i);
					if((edge.getChild()).equals(key1)) {
						
						addEdge(key1, key2, edge.getWeight());
						break;
					}
				}
			}
		}	
	}
}
