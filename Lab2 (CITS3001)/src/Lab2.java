import java.util.Arrays;
import java.util.Comparator;

public class Lab2 implements Knapsack {

	@Override
	public int fractionalKnapsack(int[] weights, int[] values, int capacity) {
		// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub
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
			if(itemList[i].getWeight() + totalWeight < capacity) {
				totalValue  = totalValue + itemList[i].getValue();
				totalWeight = totalWeight + itemList[i].getWeight();
			} else {
				int remainCapacity = capacity - totalWeight;
				totalValue = (int) (totalValue + remainCapacity * itemList[i].getRatio());
			}
		}
		System.out.print("Total value: " + totalValue + "\nTotal weight: " + totalWeight);
		return totalValue;
		
	}
	
	class Item {
		
		int weight, value = 0;
		
		public Item(int w, int v) {
			this.weight = w;
			this.value  = v;
		}
		
		public long getRatio() {
			return this.value/this.weight;
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
		int index = values.length;
		
		knapsack k = new knapsack();
		return k.knapsackDynamic(weights, values, capacity, index);

	}
	
	class knapsack
	{
		/*
		public int knapsackValue(int[] w, int[] v, int c, int n)
		{
			int result = 0;
			if(n == 0 || c == 0)
			{
				result = 0;
			} else if(w[n-1] > c)
			{
				result = knapsackValue(w, v, c, n-1);
			} else 
			{
				int value1 = knapsackValue(w, v, c, n-1);
				int value2 = knapsackValue(w, v, c-w[n-1], n-1) + v[n-1];
				result = Math.max(value1, value2);
			}
			return result;
		}
		*/
		public int knapsackDynamic(int[] w, int[] v, int c, int n)
		{
			int[][] knapsackTable = new int[n+1][c+1];
			
			for(int i = 0; i <= n; i++)
			{
				for(int j = 0; j <= c; j++)
				{
					if(i == 0 || j == 0)
					{
						knapsackTable[i][j] = 0;
					} else if (w[i-1] <= j)
					{
						knapsackTable[i][j] = Math.max(v[i-1] + knapsackTable[i-1][j-w[i-1]],
																knapsackTable[i-1][j]);
					} else {
						knapsackTable[i][j] = knapsackTable[i-1][j];
					}
				}
			}
			
			return knapsackTable[n][c];
		}
	}
	

	public static void main (String args[])
	{
		int val[] = new int[] {60, 100, 120};
		int wt[]  = new int[] {10, 20 , 30};
		int W = 50;
		
		Lab2 L = new Lab2();
		L.fractionalKnapsack(wt, val, W);
		//System.out.println(L.discreteKnapsack(wt, val, W));
	}

}
