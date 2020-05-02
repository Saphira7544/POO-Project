package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Vector;

public class Main {

	public static void main(String[] args) {
		// Exits if arguments are not correctly input
		if(args.length != 3) { 
			System.err.println("Necessary arguments: TrainFile TestFile Score(LL or MDL)");
			System.exit(-1);
		}
		
		File TrainFile = new File(args[0]);
			
		try {	
			TrainSet traindata = new TrainSet(TrainFile);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Vector<Integer> aux = new Vector<Integer>(); 
		aux.add(0);
		aux.add(2);
		Node<Vector<Integer>> node1 = new Node<Vector<Integer>>(aux);
		
		Vector<Integer> aux1 = new Vector<Integer>(); 
		aux1.add(3);
		aux1.add(2);
		Node<Vector<Integer>> node2 = new Node<Vector<Integer>>(aux1);
		
		Vector<Integer> aux2 = new Vector<Integer>(); 
		aux2.add(6);
		aux2.add(2);
		Node<Vector<Integer>> node3 = new Node<Vector<Integer>>(aux2);
		
		Vector<Integer> aux3 = new Vector<Integer>(); 
		aux3.add(1);
		aux3.add(2);
		Node<Vector<Integer>> node4 = new Node<Vector<Integer>>(aux3);
		
		Vector<Integer> aux4 = new Vector<Integer>(); 
		aux4.add(5);
		aux4.add(10);
		Node<Vector<Integer>> node5 = new Node<Vector<Integer>>(aux4);
		
		Edge<Vector<Integer>> edge1 = new Edge<Vector<Integer>>(node1,node5, false);
		Edge<Vector<Integer>> edge2 = new Edge<Vector<Integer>>(node1,node4, false);
		Edge<Vector<Integer>> edge3 = new Edge<Vector<Integer>>(node2,node3, false);
		Edge<Vector<Integer>> edge4 = new Edge<Vector<Integer>>(node5,node4, false);

		
		LinkedList<Edge<Vector<Integer>>> allEdges = new LinkedList<Edge<Vector<Integer>>>();
		
		allEdges.add(edge1);
		allEdges.add(edge2);
		allEdges.add(edge3);
		allEdges.add(edge4);
		
		Graph<Vector<Integer>> g = new Graph<Vector<Integer>>(allEdges);
		
		
		System.out.println(g);
	}

	

}
