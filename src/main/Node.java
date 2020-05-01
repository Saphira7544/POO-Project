package main;

import java.util.*;

public class Node{
	//index of feature from X1,..,Xn with n = total number of features
	private int i;
	//number of value Xi can take
	private int ri;
	
	
	//linked list of edges connected to a certain Node
	private LinkedList<Edge> connections = new LinkedList<Edge>();

	public Node(int i, int ri){
		this.i = i;
		this.ri = ri;
	}
	//getter function
	public void getEdge(Edge newEdge) {

	}
	//setter function
	public void setEdge() {
		
	}
}
