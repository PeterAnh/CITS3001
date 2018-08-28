/* Test class for KnapsackImp
 * Student name: Hoang Tuan Anh
 * Student ID: 21749914 
 * The University of Western Australia
 */
public class test {
	public static void main (String args[])
	{
		int value[] = new int[] {14,26,10,32,9,32,11,30,26,19
								,17,6,50,32,49,43,28,5,19,46,25
								,36,31,13,38,40,16,32,16,31};
		int weight[]  = new int[] {5,2,4,3,23,12,20,31,39,31,44
								  ,20,40,11,41,21,33,28,5,11,19
								  ,10,33,10,26,42,16,15,18,24};
		int capacity = 183;
		
		int value2[] = new int[] {9,2,17,4,21,34,46,33,41,16,
								  43,40,35,45,35,22,46,47,35,7};
		int weight2[] = new int[] {48,8,29,34,39,39,17,23,36,24,
									  31,15,44,10,44,3,30,38,49,43};
		int capacity2 = 138;
		
		KnapsackImp K = new KnapsackImp();
		
		int result  = K.fractionalKnapsack(weight, value, capacity) ;
		System.out.print("Fractional knapsack: " + result + "\n");
		
		int result2 = K.discreteKnapsack(weight, value, capacity);
		System.out.println("Discrete knapsack: " + result2 + "\n");
		
		int result3  = K.fractionalKnapsack(weight2, value2, capacity2) ;
		System.out.print("Fractional knapsack: " + result3 + "\n");
		
		int result4 = K.discreteKnapsack(weight2, value2, capacity2);
		System.out.println("Discrete knapsack: " + result4);
	}
}
