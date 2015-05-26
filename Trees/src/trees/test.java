package trees;

class test
{
	final int MAX = 4;
	final int MIN = 2;

class BTNode // B-Tree node
{
	int count;
	int key[] = new int[MAX];
	BTNode child[] = new BTNode[MAX+1];

}

BTNode root = new BTNode();

class Ref			 // This class creates an object reference
{
	int m; 
}					 // and is used to retain/save index values
// of current node between method calls.

/*
* New key is inserted into an appropriate node.
* No node has key equal to new key (duplicate keys are not allowed.
*/
void insertTree( int val )
{
	Ref i = new Ref();
	BTNode c = new BTNode();
	BTNode node = new BTNode();
	boolean pushup;
	pushup = pushDown( val, root, i, c );
	if ( pushup )
	{
		node.count = 1;
		node.key[1] = i.m;
		node.child[0] = root;
		node.child[1] = c;
		root = node;	
	}
}
/*
* New key is inserted into subtree to which current node points.
* If pushup becomes true, then height of the tree grows.
*/
boolean pushDown( int val, BTNode node, Ref p, BTNode c )
{
	Ref k = new Ref();
	if ( node == null )
	{
		p.m = val;
		c = null;
		return true;
	
	}
	else
	{
		if ( searchNode( val, node, k ) )
			System.out.println("Key already exists");
			
		if ( pushDown( val, node.child[k.m], p, c ) )
		{
			if ( node.count < MAX )
			{
				pushIn( p.m, c, node, k.m );
				return false;
			}
			else
			{
				split( p.m, c, node, k.m, p, c );
				return true;
			}
		}
		
		return false;
	
	}

}
/*
* Search through a B-Tree for a target key in the node: val
* Outputs target node and its position (pos) in the node
*/
BTNode searchTree( int val, BTNode root, Ref pos )
{
	if ( root == null )
		return null ;
		
	else
	{
		if ( searchNode( val, root, pos ) )
			return root;
		else
			return searchTree( val, root.child[pos.m], pos );
	}
}
/*
* This method determines if the target key is present in
* the current node, or not. Seraches keys in the current node;
* returns position of the target, or child on which to continue search.
*/
boolean searchNode( int val, BTNode node, Ref pos )
{
	if ( val < node.key[1] )
	{
		pos.m = 0 ;
		return false ;
	}
	else
	{
		pos.m = node.count ;
		
		while ( ( val < node.key[pos.m] ) && pos.m > 1 )
			(pos.m)--;
		
		if ( val == node.key[pos.m] )
			return true;
		else
			return false;
	}
}
/*
* Inserts the key into a node, if there is room

* for the insertion
*/
void pushIn( int val, BTNode c, BTNode node, int k )
{
	int i;
	for ( i = node.count; i > k ; i-- )
	{
		node.key[i + 1] = node.key[i];
		node.child[i + 1] = node.child[i];	
	}
	
	node.key[k + 1] = val ;
	node.child[k + 1] = c ;
	node.count++ ;
}
/*
* Splits a full node into current node and new right child
* with median.
*/

void split( int val, BTNode c, BTNode node, int k, Ref y, BTNode newnode )
{
	int i, mid; // mid is median
	
	if ( k <= MIN )
		mid = MIN;
	else
		mid = MIN + 1;
	
	newnode = new BTNode();
	
	for ( i = mid+1; i <= MAX; i++ )
	{
		newnode.key[i-mid] = node.key[i];
		newnode.child[i-mid] = node.child[i];
	}
	newnode.count = MAX - mid;
	node.count = mid;
	
	if ( k <= MIN )
		pushIn ( val, c, node, k );
	else
		pushIn ( val, c, newnode, k-mid ) ;
	
	y.m = node.key[node.count];
	newnode.child[0] = node.child[node.count] ;
	node.count-- ;
}

// calls display( )
void displayTree()
{
	display( root );
}
// displays the B-Tree

void display( BTNode root )
{
	int i;
	if ( root != null )
	{
			for ( i = 0; i < root.count; i++ )
			{
				display( root.child[i] );
				System.out.print( root.key[i+1] + " " );
			}
			
			display( root.child[i] );
	}
}


}

