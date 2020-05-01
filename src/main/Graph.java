package main;

import java.util.Map;
import java.util.Objects;
import java.util.List;

import java.util.HashMap;


public class Graph<T> {
	private Map <Node, List<Edge>> DAG;
	private T trainSet;
	
	public Graph(T trainSet) {
		DAG = new HashMap<Node, List<Edge>>();
		this.trainSet = trainSet;
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
		Graph<?> other = (Graph<?>) obj;
		return Objects.equals(DAG, other.DAG);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(DAG);
	}
	
	@Override
    public String toString() {
        return DAG.toString();
    }

	public Map<Node, List<Edge>> getDAG() {
		return DAG;
	}

	public void setDAG(Map<Node, List<Edge>> dAG) {
		DAG = dAG;
	}
}
