package trees;

import java.util.LinkedList;

/**
 * Klasa zawierajaca implementacje drzewa BST
 * @author Tomasz Jopek / 220917@student.pwr.edu.pl
 *
 * @param <T> typ przechowywanych obiektow 
 */
public class Tree<T extends Comparable<T>>
{
	private Node<T> _root;				//pole prywatne korzen drzewa
	
	public Tree()
	{	
		set_root(null);			//konstruktor
	}
	
	
	public void insert(T key)
	{
		if(search(key) == null)
		{
			Node<T> toInsert = new Node<T>(key);		
			_root = insertNode(toInsert);
		}
			
	}
	
	protected Node<T> insertNode(Node<T> newNode)
	{
		Node<T> currentParent = null;
		Node<T> tmpRoot = _root;
		
		while(tmpRoot != null)
		{
			currentParent = tmpRoot;
			if(newNode.getKey().compareTo(tmpRoot.getKey()) < 0)
				tmpRoot = tmpRoot.getLeftChild();
			else
				tmpRoot = tmpRoot.getRightChild();
		}
		
		newNode.setParent(currentParent);
		
		if(currentParent == null)
			_root = newNode;
		else
		{
			if(newNode.getKey().compareTo(currentParent.getKey()) < 0)
				currentParent.setLeftChild(newNode);
			else
				currentParent.setRightChild(newNode);
		}
		
		return _root;		
	}
	
	
	public Node<T> search(T searchKey)
	{
		Node<T> tmpRoot = _root;
		while(tmpRoot != null)
		{			
			if(tmpRoot.getKey().compareTo(searchKey) != 0)
			{
				if(searchKey.compareTo(tmpRoot.getKey()) < 0)
				{
					tmpRoot = tmpRoot.getLeftChild();
				}
				else
				{
					tmpRoot = tmpRoot.getRightChild();
				}
			}
			else
			{
				break;
			}
		}
		
		return tmpRoot;		
	}
	
	public Node<T> delete(T toDelete)
	{
		Node<T> tmpNode = search(toDelete);
		
		if(tmpNode == null)
			System.out.println("Dany element nie istnieje!");
		else
		{
			if(tmpNode.getLeftChild() == null && tmpNode.getRightChild() == null)
			{
				Node<T> tmpParent = tmpNode.getParent();
				if(tmpParent.getLeftChild().getKey().compareTo(toDelete) == 0)
					tmpParent.setLeftChild(null);
				else
					tmpParent.setRightChild(null);
			}
			else if(tmpNode.getLeftChild() != null && tmpNode.getRightChild()==null || tmpNode.getRightChild()!=null && tmpNode.getLeftChild()==null)
			{
				if(tmpNode.getLeftChild()!=null)
				{
					Node<T> tmpParent = tmpNode.getParent();
					if(tmpParent.getLeftChild().getKey().compareTo(toDelete) == 0)
						tmpParent.setLeftChild(tmpNode.getLeftChild());
					else
						tmpParent.setRightChild(tmpNode.getLeftChild());
		
				}
				else if(tmpNode.getRightChild() != null)
				{
					Node<T> tmpParent = tmpNode.getParent();
					if(tmpParent.getLeftChild().getKey().compareTo(toDelete) == 0)
						tmpParent.setLeftChild(tmpNode.getRightChild());
					else
						tmpParent.setRightChild(tmpNode.getRightChild());
					
				}				
			}
			else
			{
				Node<T> successor = findSuccessor(tmpNode);
				T tmpValue = successor.getKey();
				delete(tmpValue);
				tmpNode.setKey(tmpValue);
				
			}			
		}		
		
		return tmpNode != null ? tmpNode : null;
	}
	
	
	public Node<T> findSuccessor(Node<T> node)
	{
		if(node.getRightChild() != null)
			return min(node.getRightChild());
		
		else
		{
			Node<T> tmpNode = node.getParent();
			while(tmpNode != null && !tmpNode.getRightChild().equals(node))
			{
				node = tmpNode;
				tmpNode = tmpNode.getParent();
			}
			
			return tmpNode;
		}
		
	}	
	
	public Node<T> min(Node<T> node)
	{
		while(node.getLeftChild() != null)
		{
			node = node.getLeftChild();
		}
		
		return node;
	}
	
	public Node<T> max(Node<T> node)
	{
		while(node.getRightChild() != null)
		{
			node = node.getRightChild();
		}
		
		return node;
	}
	
	public void actual()
	{
		System.out.println("_______________________________");
		inOrder(_root);
		System.out.println("Leaves: " + leaves());
		System.out.println("With one child: " + oneChild());
		System.out.println("InsideNodes(with two children): " + insideNodes());
		System.out.println("_______________________________");
	}
	
	public int leaves()
	{
		return howManyLeaves(_root);
	}
	
	protected int howManyLeaves(Node<T> node)
	{	
		if(node == null)
			return 0;
		else
		{
			if(node.getLeftChild() == null && node.getRightChild() == null)
				return 1;
			else
				return howManyLeaves(node.getLeftChild()) + howManyLeaves(node.getRightChild());
		}
	}
	
	public int oneChild()
	{
		return howManyOneChilds(_root);
	}
	
	protected int howManyOneChilds(Node<T> node)
	{	
		if(node == null)
			return 0;
		else
		{
			if(node.getLeftChild() != null && node.getRightChild()==null)
				return 1 + howManyOneChilds(node.getLeftChild());
			if(node.getRightChild()!=null && node.getLeftChild()==null)
				return 1 + howManyOneChilds(node.getRightChild());
			else
			{
				return howManyOneChilds(node.getLeftChild()) + howManyOneChilds(node.getRightChild());
			}
		}
	}
	
	
	public int insideNodes()
	{
		return howManyInsideNodes(_root);
	}
	
	protected int howManyInsideNodes(Node<T> node)
	{	
		if(node == null)
			return 0;
		else
		{
			if(node.getLeftChild() != null && node.getRightChild() != null)
				return 1 + howManyInsideNodes(node.getLeftChild()) + howManyInsideNodes(node.getRightChild()) ;
			else
				return howManyInsideNodes(node.getLeftChild()) + howManyInsideNodes(node.getRightChild());
		}
	}
	
	
	
	public void inOrder(Node<T> node)
	{
		if(node == null)
			return;
		else
		{
			inOrder(node.getLeftChild());
			System.out.println(node.getKey());			
			inOrder(node.getRightChild());
			
		}
		
	}
	
	public void preOrder(Node<T> node)
	{
		if(node == null)
			return;
		else
		{
			System.out.println(node.getKey());
			preOrder(node.getLeftChild());						
			preOrder(node.getRightChild());			
		}
		
	}
	
	public void postOrder(Node<T> node)
	{
		if(node == null)
			return;
		else
		{			
			postOrder(node.getLeftChild());						
			postOrder(node.getRightChild());
			System.out.println(node.getKey());
		}
		
	}	
	
	public void byLevels(Node<T> node)
	{
		LinkedList<Node<T>> queue = new LinkedList<Node<T>>();
		
		Node<T> tmpNode = node;
		queue.add(tmpNode);
		
		while(!queue.isEmpty())
		{
			if(queue.getFirst().getLeftChild() != null)
				queue.add(queue.getFirst().getLeftChild());
			if(queue.getFirst().getRightChild() != null)
				queue.add(queue.getFirst().getRightChild());
						
			System.out.print(queue.removeFirst() + " ");
						
		}				
	}
	
    public String toString() 
    {
    	return toString(_root,0);
    }
    
   private String toString(Node<T> t,int pos) 
   {
	   String result="";
	   String spaces="                                                                                                                                                                                                     ";
	   if(t!=null)
		   result=result+toString(t.getRightChild(),pos+4)+spaces.substring(0,pos)+String.format("%s",t.getKey())+toString(t.getLeftChild(),pos+4);
	   else
		   result=result+String.format("%n");
	   
	   return result;   
   }
	
		
	public Node<T> get_root() 
	{
		return _root;
	}

	public void set_root(Node<T> _root) 
	{
		this._root = _root;
	}
}
