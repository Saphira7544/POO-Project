package main;

public class Edge {
	private Node child;
	private Node parent;
	//TRUE if there is a child and a parent FALSE if undirected graph
	private boolean directed;
	private float weight;
	
	public Edge(Node child, Node parent, boolean directed) {
		this.child = child;
		this.parent = parent;
		this.directed = directed;
	}
	
	public Node getNode(int nodeType) {
		if(nodeType == 0) return child;
		else if(nodeType == 1) return parent;
		return null;
	}
	
}
