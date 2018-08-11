import java.util.Random;

public class Lab1 {
	
	public int[] insertSort(int[] array)
	{
		int L = array.length;
		
		for(int index = 1; index < L; index++)
		{
			int key = array[index];
			int movingIndex = index - 1;
			
			while(movingIndex >=0 && array[movingIndex] > key)
			{
				array[movingIndex + 1] = array[movingIndex];
				movingIndex = movingIndex - 1;
			}
			
			array[movingIndex+1] = key;
			
		}
		return array;
	}
	
	public int[] mergeSort(int[] array)
	{
		MergeSort m = new MergeSort();
		m.sort(array, 0, array.length-1);
		return array;
	}
	
	class MergeSort {
		public void sort(int[] array, int left, int right)
		{
			if(left <  right)
			{
				int middle = (left + right) / 2;
				
				sort(array, left, middle);
				sort(array, middle+1, right);
				
				merge(array, left, middle, right);
			}
		}
		
		public void merge(int[] array, int left, int middle, int right) 
		{
			int size1 = middle - left + 1;
			int size2 = right -  middle;
			
			int[] temp1 = new int[size1];
			int[] temp2 = new int[size2];
			
			for(int i = 0; i < size1; i++)
			{
				temp1[i] = array[left + i];
			}
			
			for(int i = 0; i < size2; i++)
			{
				temp2[i] = array[middle + 1 + i];
			}
			
			int first  = 0;
			int second = 0;
			
			int k = left;
			
			while(first < size1 && second < size2)
			{
				if(temp1[first] <= temp2[second])
				{
					array[k] = temp1[first];
					first++;
				}
				else
				{
					array[k] = temp2[second];
					second++;

				}
				k++;
			}
			
			while(first < size1)
			{
				array[k] = temp1[first];
				first++;
				k++;
			}
			while(second < size2)
			{
				array[k] = temp2[second];
				second++;
				k++;
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] test = new int[100000];
		
		Random r = new Random();
		
		for(int i = 0; i < test.length; i++)
		{
			test[i] = r.nextInt(100000) + 1;
		}
		
		Lab1 L = new Lab1();
		
		long startTime = System.nanoTime();
		int[] result   = L.insertSort(test);
		long endTime   = System.nanoTime();
		long timeElapsedInsert = startTime - endTime;
		
		startTime = System.nanoTime();
		int[] result2  = L.mergeSort(test);
		endTime = System.nanoTime();
		long timeElapsedMerge = startTime - endTime;
	
		/*for(int i = 0; i < result.length; i++)
		{
			System.out.println(result[i]);
		}
		*/
		System.out.println("Time elapsed: " + timeElapsedInsert/1000000 +" ms");
		/*for(int i = 0; i < result2.length; i++)
		{
			System.out.println(result2[i]);
		}
		*/
		System.out.println("Time elapsed: " + timeElapsedMerge/1000000 +" ms");
	}

}
