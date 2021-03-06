
public interface BinarySearchTreeI<K, V> {
	
	public void insert(K k, V v);
	public V get(K k);
	public K lowestCommonAncestor(K x, K y);
	
}
