/** 1 1 2 3 5 8 13 21 */

package fibonacciPkg;

public class FibonacciMain 
{
	public static void main(String[] args) 
	{
		TheFiboProcessor fP = new TheFiboProcessor();
		System.out.println(fP.getFiboValue(7));
	}
}
