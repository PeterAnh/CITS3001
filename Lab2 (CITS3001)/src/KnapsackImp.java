/* CITS3001 Lab 2
 * Implementation of Fractional and Discrete Knapsack problems
 * Student name: Hoang Tuan Anh
 * Student ID: 21749914 
 * The University of Western Australia
 * Resources used in this lab:
 * Knapsack Problem by GeeksforGeeks: https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
 * 0-1 Knapsack Problem (Dynamic Programming) by CSDojo: https://www.youtube.com/watch?v=xOlhR_2QCXY
 */
import java.util.Arrays;
import java.util.Comparator;

public class KnapsackImp implements Knapsack {

	@Override
	public int fractionalKnapsack(int[] weights, int[] values, int capacity) {
		//Implement fractional knapsack problem
		int numItems = weights.length;
		int totalWeight = 0;
		int totalValue  = 0;
		
		Item[] itemList = new Item[numItems];
		
		for(int i = 0; i < numItems; i++)
		{
			
			itemList[i] = new Item(weights[i], values[i]);
		}
		
		Arrays.sort(itemList, new Comparator<Item>() {
			@Override
			public int compare(Item a, Item b) {
				//Sort each item based on their weight/value ratio
				if(a.getRatio() > b.getRatio())
				{
					return -1;
				}
				else if(a.getRatio() < b.getRatio())
				{
					return 1;
				}
				return 0;
			}
			
		});
		
		for(int i = 0; i < numItems; i++)
		{
			if(totalWeight >= capacity) //To escape the loop
			{
				break;
			}
			if(itemList[i].getWeight() + totalWeight < capacity) {
				totalValue  = totalValue + itemList[i].getValue();
				totalWeight = totalWeight + itemList[i].getWeight();
			} else {
				int remainCapacity = capacity - totalWeight;

				//long itemRatio = itemList[i].getRatio();
				double remainValue = (remainCapacity * itemList[i].getValue()) / itemList[i].getWeight();
				totalValue = totalValue + (int) remainValue;
				totalWeight = totalWeight + itemList[i].getWeight();
			}
		}
		return totalValue;
		
	}
	
	//This class generates items with weights and values to be sorted.
	private class Item {
		
		int weight, value = 0;
		
		public Item(int w, int v) {
			this.weight = w;
			this.value  = v;
		}
		
		public double getRatio() {
			/* Cast double to value and weight
			 * in order to avoid the weight/value ratio is truncated
			 * because integer division ignores any floating part.
			 */
			return (double) this.value/ (double) this.weight;
		}
		
		public int getWeight() {
			return this.weight;
		}
		
		public int getValue() {
			return this.value;
		}
	}

	@Override
	public int discreteKnapsack(int[] weights, int[] values, int capacity) {
		//Implement discrete knapsack problem
		int index = values.length;
		int result = knapsackDynamic(weights, values, capacity, index);
		return result;
		
	}
		
	private static int knapsackDynamic(int[] weight, int[] value, int capacity, int index)
	{
		int[][] knapsackTable = new int[index+1][capacity+1];
		
		for(int i = 0; i <= index; i++)
		{
			for(int j = 0; j <= capacity; j++)
			{
				if(i == 0 || j == 0)
				{
					knapsackTable[i][j] = 0;
				} else if (weight[i-1] <= j)
				{
					knapsackTable[i][j] = Math.max(value[i-1] + knapsackTable[i-1][j-weight[i-1]],
															knapsackTable[i-1][j]);
				} else {
					knapsackTable[i][j] = knapsackTable[i-1][j];
				}
			}
		}
		
		return knapsackTable[index][capacity];
	}
	
	private static int knapsackRecursion (int[] weight, int[] value, int capacity, int index)
	{
		int result = 0;
		if(index == 0 || capacity == 0)
		{
			result = 0;
		} else if(weight[index-1] > capacity)
		{
			result = knapsackRecursion(weight, value, capacity, index-1);
		} else 
		{
			int value1 = knapsackRecursion(weight, value, capacity, index-1);
			int value2 = knapsackRecursion(weight, value, capacity-weight[index-1], index-1) + value[index-1];
			result = Math.max(value1, value2);
		}
		return result;
	}
}
