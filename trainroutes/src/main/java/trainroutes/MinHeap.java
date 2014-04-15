package trainroutes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinHeap<T> {
	
	
	Map<T, Integer> indexMap = new HashMap<T, Integer>();
	List<HeapNode<T>> heapArr = new ArrayList<HeapNode<T>>();
	public int heapSize;
	
	public HeapNode extractMin() {
		HeapNode minNode = heapArr.get(0);
		
		exchange(0, heapArr.size()-1);
		indexMap.remove(heapArr.get(heapArr.size()-1));
		heapArr.remove(heapArr.size()-1);
		heapSize--;
		
		heapifyDown(0);
		return minNode;
		
	}
	
	public void add(T data, int val) {
		if(indexMap.containsKey(data)) {
			update(data, val);
			return;
		}
		
		heapArr.add(new HeapNode<T>(data, val));
		heapSize++;
		heapifyUp(heapArr.size()-1);
	}
	
	public void update(T data, int val) {
		int index = indexMap.get(data);
		if(val < heapArr.get(index).val) {
			heapArr.set(index, new HeapNode(data,val));
			heapifyUp(index);
		}
	}
	
	public void heapifyUp(int index) {
		if(index<=0) {
			return;
		}
		
		int parent = parent(index);
		if(heapArr.get(index).val <= heapArr.get(parent).val) {
			exchange(index, parent);
		}
		heapifyUp(parent);
	}
	
	public void heapifyDown(int index) {
		if(index>=heapSize) {
			return;
		}
		
		int left = left(index);
		int right = right(index);
		
		int minIndex = index;
		if(left<heapSize && heapArr.get(left).val<=heapArr.get(minIndex).val) {
			minIndex = left;
		}
		
		if(right<heapSize && heapArr.get(right).val<=heapArr.get(minIndex).val) {
			minIndex = right;
		}
		
		if(index==minIndex) {
			return;
		}
		
		exchange(index, minIndex);
		heapifyDown(minIndex);
		
	}
	
	public void exchange(int index, int index2) {
		//update index map
		T data = (T) heapArr.get(index);
		T data2 = (T) heapArr.get(index2);
		indexMap.put(data2, index);
		indexMap.put(data, index2);
		
		//update the heapArr
		HeapNode temp = heapArr.get(index);
		heapArr.set(index, heapArr.get(index2));
		heapArr.set(index2, temp);
	}
	
	public int left(int i) {
		return (2*i)+1;
	}
	
	public int right(int i) {
		return (2*i) + 2;
	}
	
	public int parent(int i) {
		double d = (double)i;		
		return (int)Math.ceil((d/2)-1);
	}
}
