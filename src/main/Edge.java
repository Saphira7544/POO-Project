package main;

import java.util.Objects;

public class Edge {       
	private final Node child;
	//TRUE if there is a child and a parent FALSE if undirected graph
	private boolean connected = false;
	private double weight; //saves the weight between two nodes based on LL or MDL
	
	public Edge(Node child, double weight) {
		this.child = child;
		this.weight = weight;
	}

	public Node getChild() {
		return child;
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
		return Objects.hash(child, connected, weight);
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
		return Objects.equals(child, other.child) && connected == other.connected
				&& Double.doubleToLongBits(weight) == Double.doubleToLongBits(other.weight);
	}

	
	
	
}
