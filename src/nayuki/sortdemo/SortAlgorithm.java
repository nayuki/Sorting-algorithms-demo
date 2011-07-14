package nayuki.sortdemo;


/**
 * A sorting algorithm, which is a sort of function object. Each subclass should have only a singleton instance, and the instance must be stateless.
 */
public abstract class SortAlgorithm {
	
	public abstract void sort(SortArray array);
	
	public abstract String getName();
	
}
