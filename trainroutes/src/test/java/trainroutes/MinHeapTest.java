package trainroutes;

import junit.framework.Assert;

import org.junit.Test;

public class MinHeapTest {

	@Test
	public void testIndices() {
		MinHeap<String> heap = new MinHeap<String>();
		Assert.assertEquals(1, heap.left(0));
		Assert.assertEquals(3, heap.left(1));
		Assert.assertEquals(5, heap.left(2));
		Assert.assertEquals(7, heap.left(3));
		Assert.assertEquals(9, heap.left(4));
		Assert.assertEquals(11, heap.left(5));
		
		Assert.assertEquals(2, heap.right(0));
		Assert.assertEquals(4, heap.right(1));
		Assert.assertEquals(6, heap.right(2));
		Assert.assertEquals(8, heap.right(3));
		Assert.assertEquals(10, heap.right(4));

		
		Assert.assertEquals(-1, heap.parent(0));
		Assert.assertEquals(0, heap.parent(1));
		Assert.assertEquals(0, heap.parent(2));
		Assert.assertEquals(1, heap.parent(3));
		Assert.assertEquals(1, heap.parent(4));
		Assert.assertEquals(2, heap.parent(5));
		Assert.assertEquals(2, heap.parent(6));
	}
	
	@Test
	public void testExchange() {
		MinHeap<String> minHeap = new MinHeap<String>();
		minHeap.heapArr.add(new HeapNode<String>("A", 12));
		minHeap.indexMap.put("A", 0);
		minHeap.heapArr.add(new HeapNode<String>("B", 10));
		minHeap.indexMap.put("B", 1);
		
		minHeap.exchange(0, 1);
		Assert.assertEquals("A", minHeap.heapArr.get(1).data);
		Assert.assertEquals(12, minHeap.heapArr.get(1).val);
		Assert.assertEquals(new Integer(1), minHeap.indexMap.get("A"));
		
		Assert.assertEquals("B", minHeap.heapArr.get(0).data);
		Assert.assertEquals(10, minHeap.heapArr.get(0).val);
		Assert.assertEquals(new Integer(0), minHeap.indexMap.get("B"));
		
	}
	
	@Test
	public void testAdd() {
		MinHeap<String> heap = new MinHeap<String>();
		
		//add & check
		heap.add("A", 15);
		heap.add("B", 10);
		Assert.assertEquals(10, heap.heapArr.get(0).val);
		Assert.assertEquals("B", heap.heapArr.get(0).data);
		Assert.assertEquals(15, heap.heapArr.get(1).val);
		Assert.assertEquals("A", heap.heapArr.get(1).data);
		
		//add & check
		heap.add("C", 6);
		Assert.assertEquals(6, heap.heapArr.get(0).val);
		Assert.assertEquals("C", heap.heapArr.get(0).data);
		Assert.assertEquals(15, heap.heapArr.get(1).val);
		Assert.assertEquals("A", heap.heapArr.get(1).data);
		Assert.assertEquals(10, heap.heapArr.get(2).val);
		Assert.assertEquals("B", heap.heapArr.get(2).data);
		
		//add & check
		heap.add("D", 13);
		Assert.assertEquals(13, heap.heapArr.get(1).val);
		Assert.assertEquals("D", heap.heapArr.get(1).data);
		Assert.assertEquals(6, heap.heapArr.get(0).val);
		Assert.assertEquals("C", heap.heapArr.get(0).data);
		Assert.assertEquals(10, heap.heapArr.get(2).val);
		Assert.assertEquals("B", heap.heapArr.get(2).data);
		Assert.assertEquals(15, heap.heapArr.get(3).val);
		Assert.assertEquals("A", heap.heapArr.get(3).data);
		
		//add & check
		heap.add("E", 12);
		Assert.assertEquals(12, heap.heapArr.get(1).val);
		Assert.assertEquals("E", heap.heapArr.get(1).data);
		Assert.assertEquals(6, heap.heapArr.get(0).val);
		Assert.assertEquals("C", heap.heapArr.get(0).data);
		Assert.assertEquals(10, heap.heapArr.get(2).val);
		Assert.assertEquals("B", heap.heapArr.get(2).data);
		Assert.assertEquals(15, heap.heapArr.get(3).val);
		Assert.assertEquals("A", heap.heapArr.get(3).data);
		Assert.assertEquals(13, heap.heapArr.get(4).val);
		Assert.assertEquals("D", heap.heapArr.get(4).data);
		
	}
	
	@Test
	public void testUpdate() {
		MinHeap<String> heap = new MinHeap<String>();
		heap.add("A", 15);
		heap.add("B", 10);
		
		//update lower value
		heap.update("A", 6);
		Assert.assertEquals(6, heap.heapArr.get(0).val);
		Assert.assertEquals("A", heap.heapArr.get(0).data);
		
		//update higher value - should have no effect
		heap.update("A", 10);
		Assert.assertEquals(6, heap.heapArr.get(0).val);
		Assert.assertEquals("A", heap.heapArr.get(0).data);
		
		//update by calling add fn
		heap.add("B", 3);
		Assert.assertEquals(3, heap.heapArr.get(0).val);
		Assert.assertEquals("B", heap.heapArr.get(0).data);
	}
	
	@Test
	public void testExtractMin() {
		MinHeap<String> heap = new MinHeap<String>();
		heap.add("A", 15);
		heap.add("B", 10);
		heap.add("C", 6);
		heap.add("D", 13);
		heap.add("E", 12);
		
		HeapNode<String> minNode = heap.extractMin();
		Assert.assertEquals("C", minNode.data);
		Assert.assertEquals(4, heap.heapSize);
		
		minNode = heap.extractMin();
		Assert.assertEquals("B", minNode.data);
		Assert.assertEquals(3, heap.heapSize);
		
		minNode = heap.extractMin();
		Assert.assertEquals("E", minNode.data);
		Assert.assertEquals(2, heap.heapSize);
		
		minNode = heap.extractMin();
		Assert.assertEquals("D", minNode.data);
		Assert.assertEquals(1, heap.heapSize);
		
		minNode = heap.extractMin();
		Assert.assertEquals("A", minNode.data);
		Assert.assertEquals(0, heap.heapSize);
	}
 }
