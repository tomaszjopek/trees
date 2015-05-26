package trees;

import java.util.Collections;
import java.util.Comparator;

public class BTree<T extends Comparable<T>> 
{
	BTreeNode<T> _root;
	int degree;
	
	public BTree(int degree)
	{
		_root = null;
		this.degree = degree;
	
	}

	
	public void insert(T k)
	{
		
		if(_root == null)
		{
			_root = new BTreeNode<T>(degree, true);
			_root.key.add(k);
			_root.number++;
		}
		else
		{
			if(_root.number == 2*degree - 1)
			{
				BTreeNode<T> tmp = new BTreeNode<T>(degree, false);
				tmp.child.add(_root);
				
			}
		}
		
	}
	
	
	

	
	
	

}
