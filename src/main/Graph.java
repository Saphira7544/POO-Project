package main;

public class Graph {
	
}

public void updateNodeCounts(TrainSet set)  {
	
	int nrInstances = 5; //get from isabel

	int nrXs = 4; //get from isabel

	// Next lines will initialize the variable Node.counts of every Node in this.nodeArray
	
	int nrclass = 2; //get from isabel
	// Initialize nodes' counts
	
	for (int i = 0; i < nrXs; i++) {
		// for each parent node i
		nodeArray.get(i).counts = new int[nrXs][][][];
		for (int j = 0; j < nrclass; j++) {
			
			// iniciate all possible values of Nijkc for each son
			nodeArray.get(i).Nijkc[j] = new int[range de j][range de i][classRange];
		}
		nodeArray.get(i).Nijc = new int[range de i][classRange];
	}
	nodeArray.Nc = new int[classRange];

	// Now the listOfTrainPatterns will be iterated and Node.counts will be updated
	Iterator<Instance> iterator = set.getInstance().iterator();

	Pattern newPattern;

	while (iterator.hasNext()) {
		newInstance = iterator.next();

		updateNodeCountsFromPattern(set, newInstance);
	}

}

private void updateNodeCountsFromPattern(TrainSet set, Pattern newPattern) {

	int nrXs = nodeArray.size();

	outputNode.cCounts[newPattern.getOutput()]++;

	for (int i = 0; i < nrXs; i++) { // each Xi
		for (int p = 0; p < nrXs; p++) { // each possible parent node

			if (p == i) { // case in which Xi has no parent. We will store this case in the position where node Xi is the parent of itself

				/*
				 * For Node Xi, increment counts' entry that corresponds to: empty parent configuration, node Xi has value pattern[i] and
				 * classNode has value output
				 */
				nodeArray.get(i).Nijkc[i][0][ valor de Xi][valor de C]++;
				continue;
			}
			/*
			 * For Node Xi, increment counts' entry that corresponds to: parent p has value pattern[p], node Xi has value pattern[i] and classNode
			 * has value output
			 */
			nodeArray.get(i).Nijkc[p][ valor de Xj ][ valor de Xi ][valor de C ]++;
		}

		nodeArray.get(i).Nijc[ valor de Xi ][valor de C]++;
	}
}


	
}

