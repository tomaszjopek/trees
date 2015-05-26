package trees;

public class Node<T extends Comparable<T>>
{	
	private Node<T> leftChild;
	private Node<T> rightChild;
	private Node<T> parent;
	private T key;
	
	public Node(T key)
	{
		this.setLeftChild(null);
		this.setRightChild(null);
		this.setParent(null);
		this.setKey(key);
	}
	
	public Node(T key, Node<T> parent)
	{
		this.setLeftChild(null);
		this.setRightChild(null);
		this.setParent(parent);
		this.setKey(key);
	}
	

	public Node<T> getLeftChild()
	{
		return leftChild;
	}

	public void setLeftChild(Node<T> leftChild) 
	{
		this.leftChild = leftChild;
	}

	public Node<T> getRightChild() 
	{
		return rightChild;
	}

	public void setRightChild(Node<T> rightChild) 
	{
		this.rightChild = rightChild;
	}

	public T getKey() 
	{
		return key;
	}

	public void setKey(T key) 
	{
		this.key = key;
	}
	
	public String toString()
	{
		if(key == null)
			return "null";
		else
			return key.toString();
	}

	public Node<T> getParent() {
		return parent;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}
	
	
}
