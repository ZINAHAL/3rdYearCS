
public class BinarySearchTree<Key extends Comparable<Key>, Value> implements BinarySearchTreeI<Key, Value> {
	
	//This class doesn't have a construct.
	
	private class Node {
		
		private Key key;
		private Value data;
		private Node left, right;
		
		public Node(Key key, Value data, Node left, Node right) {
			
			this.key = key;
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}
	
	private Node root = null;
	

	public void insert(Key k, Value v) {
		
		root = insert(root, k, v);
	}
	
	
	private Node insert(Node parent, Key k, Value v) {
		
		if(parent == null) {
			return new Node(k, v, null, null);
		}
		
		int comparison = k.compareTo(parent.key);
		if(comparison == -1) {
			
			parent.left = insert(parent.left, k, v);
		}
		
		if(comparison == 1) {
			
			parent.right = insert(parent.right, k, v);
		}
		
		if(comparison == 0) {
			
			parent.data = v;
		}
		
		return parent;
	}
	

	public Value get(Key k) {
		
		return get(root, k);
	}
	
	
	private Value get(Node parent, Key k) {
		
		if(parent == null || k == null) {
			return null;
		}
		
		int comparison = k.compareTo(parent.key);
		if(comparison == -1) {
			
			return get(parent.left, k);
		}
		
		if(comparison == 1) {
			
			return get(parent.right, k);
		}
		
		return parent.data;
	}
	

	public Key lowestCommonAncestor(Key x, Key y) {
		
		return lowestCommonAncestor(root, x, y);
	}
	
	
	private Key lowestCommonAncestor(Node parent, Key x, Key y)
	{
		//No lowestCommonAncestor
		if(parent == null || get(x) == null || get(y) == null || x == y)
		{
			return null;
		}
		
		int comp_parent_x = x.compareTo(parent.key);
		int comp_parent_y = y.compareTo(parent.key);
		
		//if x and y less than parent's key, then lowestCommonAncestor lies on left subtree
		if((comp_parent_x == -1) && (comp_parent_y == -1))
		{
			return lowestCommonAncestor(parent.left, x, y);
		}
		
		//if x and y greater than parent's key, then lowestCommonAncestor lies on right subtree
		if((comp_parent_x == 1) && (comp_parent_y == 1))
		{
			return lowestCommonAncestor(parent.right, x, y);
		}
		
		//otherwise if x or y greater than or equals to the parent's key then the parent is the lowestCommonAncestor
		return parent.key;
	}
	

}
