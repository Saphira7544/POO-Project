package main;

import java.util.Objects;

public class Node{
	
	private final String key;	
	private final int range;
	private final int index;
	private boolean isVisited = false;

	int[][][][] Nijkc;
	int[][] Nijc;
	int[] Nc;
	int[] cCounts;
	
	
	public int[][][][] getNijkc() {
		return Nijkc;
	}	
	public int[][] getNijc() {
		return Nijc;
	}	

	double [][][] theta;
	
	public int[] getNc() {
		return Nc;
	}
	double [] theta_c;

	public Node(String key, int range, int index){
		this.key = key;
		this.range = range;
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	public String getKey() {
		return key;
	}
	
	public int getRange() {
		return range;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		return Objects.equals(key, other.key) & Objects.equals(range, other.range);
	}
		
	@Override
	public String toString() {
		return key;
	}

}
	

	
