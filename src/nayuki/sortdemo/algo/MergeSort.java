package nayuki.sortdemo.algo;

import nayuki.sortdemo.SortAlgorithm;
import nayuki.sortdemo.SortArray;

public class MergeSort extends SortAlgorithm {

	@Override
	public void sort(SortArray array) {
		int[] tempArray = new int[array.length()];
		doMergeSort(array,0,array.length() -1,tempArray);
		array.setDone(0,array.length());
	}

	
	private static void doMergeSort(SortArray array, int left, int right, int[] tempArray) {
		if ( left < right) {
			int middle = left + ( right - left) / 2;
			doMergeSort(array, left, middle, tempArray);
			doMergeSort(array, middle + 1, right, tempArray);
			merge(array, left, middle + 1, right, tempArray);
		}
	}

	
	private static void merge(SortArray array, int left, int right, int rightEnd, int[] tempArray) {
		
		int leftEnd = right - 1;
		int k = left;
		int num = rightEnd - left + 1;
 
		while(left <= leftEnd && right <= rightEnd){
			
			// puts the lowest in the array
			if(array.compare(left, right) <= 0)
				tempArray[k++] = array.getValue(left++);
			else
				tempArray[k++] = array.getValue(right++);
		}
		
		while(left <= leftEnd)  
			tempArray[k++] = array.getValue(left++);

		while(right <= rightEnd) 
			tempArray[k++] = array.getValue(right++);

		// Copy tempArray back
		for(int i = 0; i < num; i++, rightEnd--)
			array.setValue(rightEnd,tempArray[rightEnd]);
	}
    
	@Override
	public String getName() {
		return "Merge sort";
	}
 }
