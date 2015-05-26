package trees;

import java.util.ArrayList;

public class BTreeNode<T extends Comparable<T>> 
{	
	ArrayList<T> key;	// an array which stores keys of the node
	ArrayList<BTreeNode<T>> child;		
	int degree;
	int number;
	boolean leaf;
	
	public BTreeNode(int degree, boolean leaf)
	{		
		key = new ArrayList<T>();
		child = new ArrayList<BTreeNode<T>>();
		this.degree = degree;
		this.leaf = leaf;
		number = 0;
	}

	
	public void traverse()
	{
		int i;
		for(i=0;i < number;i++)
		{
			if(leaf == false)
				child.get(i).traverse();
			System.out.print(" " + key.get(i));
		}
		
		if(leaf == false)
			child.get(i).traverse();
	}
	
	public BTreeNode<T> search(T k)
	{
		int i = 0;
	    while (i < number && k.compareTo(key.get(i)) > 0)
	        i++;
	    
	    if (key.get(i).compareTo(k) == 0)
	        return this;
	 
	    // If key is not found here and this is a leaf node
	    if (leaf == true)
	        return null;
	 
	    // Go to the appropriate child
	    return child.get(i).search(k);
	    
	}
	
	public void splitChild(int i, BTreeNode<T> node)
	{
		BTreeNode<T> tmpNode = new BTreeNode<T>(node.degree, node.leaf);
		tmpNode.number = degree-1;
		
		for(int j=0;j<degree-1;j++)
		{
			tmpNode.key.set(j, node.key.get(j+degree));
		}
		
		 if (node.leaf == false)
		 {
		    for (int j = 0; j < degree; j++)
		      tmpNode.child.set(j, node.child.get(j+degree));
		 }
		
		 node.number = degree - 1;
		 
		 for (int j = number; j >= i+1; j--)
			 child.set(j+1, child.get(j));
		
		 child.set(i+1, tmpNode);
		 
		 for (int j = number-1; j >= i; j--)
		     key.set(j+1, key.get(j));
		 
		 key.set(i, node.key.get(degree-1));
		 
		 number++;		 
	}
	
	public void add(T k)
	{
		int i = number - 1;
	}
	
	
}
