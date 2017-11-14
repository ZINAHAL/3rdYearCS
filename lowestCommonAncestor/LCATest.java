import static org.junit.Assert.*;

import org.junit.Test;


public class LCATest {
	
	LCA bTree; 
	

	@Test
	public void test() {
		
		int[] templateNums = {1, 2, 3, 4, 5, 6,7};
		bTree = new LCA(templateNums);
		assertNotNull(bTree);
	}

}
