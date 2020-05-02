package main;

import java.util.Objects;

public class Edge<T> {
	private final Node<T> child;
	private final Node<T> parent;
	//TRUE if there is a child and a parent FALSE if undirected graph
	private boolean directed;
	//saves the weight between two nodes based on LL or MDL
	private float weight;
	
	public Edge(Node<T> parent, Node<T> child, boolean directed) {
		this.child = child;
		this.parent = parent;
		this.directed = directed;
	}

	@Override
	public int hashCode() {
		return Objects.hash(child, directed, parent, weight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge<?> other = (Edge<?>) obj;
		//alfaij = alfaji 
		return ( (Objects.equals(child, other.child) && Objects.equals(parent, other.parent)) ||
			     (Objects.equals(child, other.parent) && Objects.equals(parent, other.child)) )
				&& Float.floatToIntBits(weight) == Float.floatToIntBits(other.weight);
	}


	public Node<T> getChild() {
		return child;
	}

	public Node<T> getParent() {
		return parent;
	}

	public boolean isDirected() {
		return directed;
	}

	public void setDirected(boolean directed) {
		this.directed = directed;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Edge [child=" + child + ", parent=" + parent + ", directed=" + directed + ", weight=" + weight + "]";
	}
	
	
	
}
