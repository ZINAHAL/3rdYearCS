import static org.junit.Assert.*;

import org.junit.Test;


public class TestPack {
	
	

	@Test
	public void testInsertAndGet() {
		
		BinarySearchTree<Integer, Character> bst = new BinarySearchTree<Integer, Character>();
		bst.insert(5, 'A');
		bst.insert(3, 'B');
		bst.insert(2, 'C');
		bst.insert(4, 'D');
		bst.insert(7, 'E');
		bst.insert(6, 'F');
		bst.insert(8, 'G');
		
		assertEquals(Character.valueOf('D'), bst.get(4));
		assertEquals(Character.valueOf('C'), bst.get(2));
		assertEquals(Character.valueOf('B'), bst.get(3));
		assertEquals(Character.valueOf('A'), bst.get(5));
		assertEquals(Character.valueOf('E'), bst.get(7));
		assertEquals(Character.valueOf('F'), bst.get(6));
		assertEquals(Character.valueOf('G'), bst.get(8));
		
		assertEquals(null, bst.get(null));
		assertEquals(null, bst.get(66));
		assertEquals(null, new BinarySearchTree<Integer, Character>().get(99));
		assertEquals(null, new BinarySearchTree<Integer, Character>().get(null));
	}
	
	
	/* BST
    			5
 			/    	 \
		  3      	  7
		 /  \    	 /  \
		2   4       6    8  
	*/
	
	
	@Test
	public void testLowestCommonAncestor() {
		
		BinarySearchTree<Integer, Character> bst = new BinarySearchTree<Integer, Character>();
		bst.insert(5, 'A');
		bst.insert(3, 'B');
		bst.insert(2, 'C');
		bst.insert(4, 'D');
		bst.insert(7, 'E');
		bst.insert(6, 'F');
		bst.insert(8, 'G');
		
		assertEquals(Integer.valueOf(3), bst.lowestCommonAncestor(2, 4));
		assertEquals(Integer.valueOf(7), bst.lowestCommonAncestor(6, 8));
		assertEquals(Integer.valueOf(5), bst.lowestCommonAncestor(2, 8));
		assertEquals(Integer.valueOf(5), bst.lowestCommonAncestor(5, 8));
		
		assertEquals(null, bst.lowestCommonAncestor(11, 88)); //both keys are not present
		assertEquals(null, bst.lowestCommonAncestor(6, 88)); // one key is present
		assertEquals(null, bst.lowestCommonAncestor(null, null));
		assertEquals(null, bst.lowestCommonAncestor(null, 8));
		assertEquals(null, bst.lowestCommonAncestor(4, 4));
	}
	
	

	
}
