package main;

public class Main {

	public static void main(String[] args) {

		Graph g = new Graph();		
		
		Node node1 = new Node("X1", 2);
		Node node2 = new Node("X2", 2);
		Node node3 = new Node("X3", 2);
		
		g.addNode(node1);
		g.addNode(node2);
		g.addNode(node3);
		g.addEdge(node1, node2, false);
		g.addEdge(node3, node2, false);
		g.addEdge(node1, node3, false);
		
		System.out.println(g);
	}

}
