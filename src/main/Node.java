package main;

import java.util.Objects;

public class Node<T>{
	
	private final T data;	

	public Node(T data){
		this.data = data;
	}

	public T getData() {
		return data;
	}

	@Override
	public int hashCode() {
		return Objects.hash(data);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node<?> other = (Node<?>) obj;
		return Objects.equals(data, other.data);
	}
		
	@Override
	public String toString() {
		return "Node [data=" + data + "]";
	}
}
