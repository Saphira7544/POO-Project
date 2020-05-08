package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Set;

public class Main {
	
	public static Graph graph = new Graph(); 
	
	
	public static void main(String[] args) {
		
		
		// Exits if arguments are not correctly input
		if(args.length != 3) { 
			System.err.println("Necessary arguments: TrainFile TestFile Score(LL or MDL)");
			System.exit(-1);
		}
		
		File TrainFile = new File(args[0]);
		
		TrainSet TrainData = null;
		try {	
		//Graph g = new Graph();
			TrainData = new TrainSet(TrainFile);
			// update in the main the graph and nodes created with the information from the Train File
			graph = TrainData.getGraph(); 
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ScoreModel scoreModel = null;
		if ("LL".equals(args[2])) {
			scoreModel = new LL_model();
		} else if ("MDL".equals(args[2])) {
			scoreModel = new MDL_model();
		} else {
			System.err.println("Third argument must be LL or MDL to pick Score Model.");
			System.exit(-1);
		}
		
		graph.setTrainData( TrainData );
		graph.updateNodeCounts();
		graph.createAllEdges();
		
		/*Set<Node> keys1 = graph.getDAG().keySet();
		
		for (Node key : keys1){	
			
			int qi = key.getRange();	
			
			for(int a = 0; a < graph.getDAG().get(key).size(); a++) {
				int ri = graph.getDAG().get(key).get(a).getChild().getRange();
				System.out.println(qi);
				for( int j = 0; j < qi+1; j++ ) { 
					
					for( int k = 0; k < ri+1; k++ ) {
	
						for( int c = 0; c <= TrainData.classRange; c++ ) {
							int i = graph.getDAG().get(key).get(a).getChild().getIndex();
							int Nijkc = key.Nijkc[i][j][k][c];
							System.out.println((key.getIndex()+1) + "  N [" + (i+1) + "][" + (j+1) + "][" + (k+1) + "][" + (c+1) + "] = " + Nijkc);
				
						}		
					}	
				}
			}	
		}*/
			

		graph.setAllWeights(scoreModel);	
		graph.createCompleteGraph();
		System.out.println(graph);
		
		/*
		Tree t = new Tree(graph.getDAG());
		System.out.println(graph);
		t.applyPrim();
		System.out.println(graph);
		*/
	}	
}
