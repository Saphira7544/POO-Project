package main;

import java.util.Objects;


public class Node{
	
	private final String label;	
	private final int range;

	int[][][][] Nijkc;
	
	
	private int[][] Nijc;
	private int[] cCounts;

	public Node(String key, int range){
		this.label = key;
		this.range = range;
	}

	public String getKey() {
		return label;
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
		return Objects.equals(label, other.label) & Objects.equals(range, other.range);
	}
		
	@Override
	public String toString() {
		return label;
	}

}
	

	
