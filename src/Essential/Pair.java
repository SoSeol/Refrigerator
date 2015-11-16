package Essential;

/**
 * Replicates C++ std::pair
 * @author a
 *
 * @param <T1> std::pair's first
 * @param <T2> std::pair's second
 * 
 */

public class Pair<T1,T2>
{
	public T1 first;
	public T2 second;
	
	public Pair() { first = null; second = null; }
	public Pair(T1 newFirst, T2 newSecond) { first = newFirst; second = newSecond; }
}
