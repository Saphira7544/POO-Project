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
	
	

public void updateNodeCounts(TrainSet set)  {
	
	Node node1 = new Node("X1", 2);
	
	int nrInstances = TrainData.get_N(); 
	int nrXs = TrainData.get_n(); 
	int classRange = TrainData.getClassRange();
	
	// Next lines will initialise the variable Node.counts of every Node in this.nodeArray
	// Initialise nodes' counts
	
	
	Set<Node> keys = DAG.keySet();
	for (Node key : keys) {
	            
		key.Nijkc = new int[nrXs][][][];
		
		for (int j = 0; j < classRange; j++) {
			
			// Initialise all possible values of Nijkc for each son
			DAG.get(i).Nijkc[j] = new int[TrainData.getRange(j)][TrainData.getRange(i)][classRange];
		}
		DAG.get(i).Nijc = new int[TrainData.getRange(i)][classRange];
	}
	DAG.Nc = new int[classRange];

	// Now the listOfTrainPatterns will be iterated and Node.counts will be updated
	Iterator<Instance> iterator = set.getInstance().iterator();

	Pattern newPattern;

	while (iterator.hasNext()) {
		newInstance = iterator.next();

		updateNodeCountsFromPattern(set, newInstance);
	}

}

private void updateNodeCountsFromPattern(TrainSet set, Pattern newPattern) {

	int nrXs = DAG.size();
	
	

	outputNode.cCounts[newPattern.getOutput()]++;

	for (int i = 0; i < nrXs; i++) { // each Xi
		for (int p = 0; p < nrXs; p++) { // each possible parent node

			if (p == i) { // case in which Xi has no parent. We will store this case in the position where node Xi is the parent of itself

				/*
				 * For Node Xi, increment counts' entry that corresponds to: empty parent configuration, node Xi has value pattern[i] and
				 * classNode has value output
				 */
				DAG.keySet().Nijkc[i][0][ valor de Xi][valor de C]++;
				continue;
			}
			/*
			 * For Node Xi, increment counts' entry that corresponds to: parent p has value pattern[p], node Xi has value pattern[i] and classNode
			 * has value output
			 */
			DAG.get(i).Nijkc[p][ valor de Xj ][ valor de Xi ][valor de C ]++;
		}

		DAG.get(i).Nijc[ valor de Xi ][valor de C]++;
	}
}
}
