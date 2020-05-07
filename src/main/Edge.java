package main;

import java.util.Objects;

public class Edge {       
	private final Node child;
	//TRUE if there is a child and a parent FALSE if undirected graph
	private boolean connected = false;
	private boolean directed;//TRUE if child and parent are set; FALSE if undirected graph
	private double weight; //saves the weight between two nodes based on LL or MDL
	
	public Edge(Node child, boolean directed) {
		this.child = child;
		this.directed = directed;
	}

	public Node getChild() {
		return child;
	}

	public boolean isDirected() {
		return directed;
	}

	public void setDirected(boolean directed) {
		this.directed = directed;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean isConnected) {
		this.connected = isConnected;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Edge [child=" + child + ", connected=" + connected + ", weight=" + weight + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(child, directed, weight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		return Objects.equals(child, other.child) && directed == other.directed;
	}
	
	
	
}
