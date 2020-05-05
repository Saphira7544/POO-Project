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
	private final Map <Node, List<Edge>> DAG;
	
	List<Instance> Instances = TrainData.getInstances();
			//new ArrayList<>();
	
	//Instance Instance = TrainData.getInstances();

	
	public Graph() {
		DAG = new HashMap<Node, List<Edge>>();
	}
	
	public void setTrainData(TrainSet traindata) {
		this.TrainData = traindata;
	}
	
	/**
	 * add in the hash table the key newNode and creates 
	 * an empty list of edges
	 * @param newNode with the feature index and range
	 */
	public void addNode(Node newNode) {
	    DAG.putIfAbsent(newNode, new ArrayList<Edge>());
	}
	
	/**
	 * @param parent defines the key of the hash
	 * @param child node that is going to be saved inside object edge
	 * @param directed saves
	 */
	public void addEdge(Node parent, Node child, boolean directed) {
		Edge newEdge = new Edge(child, directed); 
	    DAG.get(parent).add(newEdge);
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
	
	

public void updateNodeCounts()  {
	
	
	int nrInstances = TrainData.get_N(); 
	int nrXs = TrainData.get_n(); 

	// Next lines will initialize the variable Node.counts of every Node in this.nodeArray
	
	int nrclass = 3; //get from isabel
	
	/*int[][][][] a = new int[3][2][2][2];
	a[1][1][1][1] = TrainData.classRange;
	a[2][1][1][1] = 2;*/
	
	
	// Initialize nodes' counts
	Set<Node> keys = DAG.keySet();
	
	for (Node key : keys) {
	        
	
		for (int j = 0; j < nrXs; j++) {
			
			// iniciate all possible values of Nijkc for each son
			key.setNijkc(new int[nrXs][TrainData.getRange(j)][key.getRange()][nrclass]);
			
			
		}
		//key.setNijkc(a);
		//System.out.println(key + " " + key.getNijkc()[1][1][1][1]);
	}
		
	
	
	int[] Inst = new int[TrainData.get_n()];
	int C = 0;

	
	for (int k = 0; k < TrainData.get_N(); k++) {
	
		Inst = Instances.get(k).getArray();
		C = Instances.get(k).getClassVariable(); 
		
		updateNodeCountsFromPattern(Inst , C );
		
	}
}


private void updateNodeCountsFromPattern( int[] Inst, int C) {

	int nrXs = TrainData.get_n();
	
	int index = -1;
	
	Set<Node> keys = DAG.keySet();
	
	for (Node key : keys) {
	    
		
		int[][][][] aux = new int[nrXs][][][];
		
		index++;
		
			for (int j = 0; j < nrXs; j++) {
				
				if (index == j) { // case in which Xi has no parent. We will store this case in the position where node Xi is the parent of itself
	
					aux[index][0][ Inst[index] ][C]++;
					
					continue;
				}
				
				aux[j][ Inst[j] ][ Inst[index] ][C]++;
				
			}
			
			key.setNijkc(aux);
		
	}

	/*outputNode.cCounts[newPattern.getOutput()]++;

	for (int i = 0; i < nrXs; i++) { // each Xi
		for (int p = 0; p < nrXs; p++) { // each possible parent node

			
			
			DAG.get(i).Nijkc[p][ valor de Xj ][Inst[p]][C]++;
		}

		//DAG.get(i).Nijc[ valor de Xi ][valor de C]++;
	}*/
}
}
