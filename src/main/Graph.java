package main;


import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class Graph {
	
	
	private final Map <Node, List<Edge>> DAG;

	
	public Graph() {
		DAG = new HashMap<Node, List<Edge>>();
	}
	
	public void addNode(Node newNode) {
	    DAG.putIfAbsent(newNode, new ArrayList<>());
	}
	
	/*
	 * public void removeNode(Node a) { DAG.values().stream().forEach(e ->
	 * e.remove(a)); DAG.remove(a); }
	 */
	
	public void addEdge(Node parent, Node child, boolean directed) {
		Edge newEdge = new Edge(child, directed); 
	    DAG.get(parent).add(newEdge);
	}
	
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
	
	int nrInstances = 5; //get from isabel

	int nrXs = DAG.size(); //get from isabel

	// Next lines will initialize the variable Node.counts of every Node in this.nodeArray
	
	int nrclass = 2; //get from isabel
	// Initialize nodes' counts
	
	
	Set<Node> keys = DAG.keySet();
	for (Node key : keys) {
	            
		key.Nijkc = new int[nrXs][][][];
		
		for (int j = 0; j < nrclass; j++) {
			
			// iniciate all possible values of Nijkc for each son
			DAG.get(i).Nijkc[j] = new int[range de j][range de i][classRange];
		}
		DAG.get(i).Nijc = new int[range de i][classRange];
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
