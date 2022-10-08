import java.util.NoSuchElementException;

public class MinHeap {
    private final int[] m_heapArray;
    private int m_heapSize;
    private final int m_capacity;

    MinHeap(final int capacity) throws IllegalArgumentException{
        if (capacity <= 0) throw new IllegalArgumentException("capacity must be greater than 0");
        m_heapArray = new int[capacity];
        m_heapSize = 0;
        m_capacity = capacity;
    }

    // getters
    public int[] getHeapArray() { return m_heapArray; }
    public int getHeapSize() { return m_heapSize; }
    public int getCapacity() { return m_capacity; }

    // In this DS must be following methods:
    // getMin()
    // extractMin()
    // decreaseKey(final int index, final int newValue)
    // insert(final int newValue)
    // delete(final int index)
    // print()

    public int getMin() throws NoSuchElementException{
        if (m_heapSize == 0) throw new NoSuchElementException("There's no elements in the heap");
        return m_heapArray[0];
    }
    public int extractMin() throws NoSuchElementException{
        final int min = getMin();
        if (m_heapSize == 1){
            --m_heapSize;
        } else {
            m_heapArray[0] = m_heapArray[m_heapSize - 1];
            minHeapify(0);
            --m_heapSize;
        }
        return min;
    }
    public void decreaseKey(final int keyIndex, final int newValue) throws IllegalArgumentException, NoSuchElementException{
        if (keyIndex < 0) throw new IllegalArgumentException("keyIndex must be greater or equal than 0");
        if (keyIndex >= m_heapSize) throw new NoSuchElementException("There's no element by index keyIndex");
        if (newValue >= m_heapArray[keyIndex]) throw new IllegalArgumentException("newValue must be less than current value of element by index keyIndex");
        if (keyIndex == 0){
            m_heapArray[0] =  newValue;
        } else {
            m_heapArray[keyIndex] = newValue;
            elementLifting(keyIndex);
        }
    }
    public void insert(final int newValue) throws OutOfMemoryError{
        if (m_heapSize == m_capacity) throw new OutOfMemoryError("There's no more allocated memory for this heap to add another one element");
        ++m_heapSize;
        m_heapArray[m_heapSize - 1] = newValue;
        elementLifting(m_heapSize - 1);
    }
    public void delete(final int elementIndex) throws NoSuchElementException, IllegalArgumentException{
        if (elementIndex < 0) throw new IllegalArgumentException("elementIndex cannot be less than 0");
        if (elementIndex >= m_heapSize) throw new NoSuchElementException("There's no element in the heap by index elementIndex");
        if (elementIndex == 0) extractMin();
        else{
            m_heapArray[elementIndex] = getMin() - 1;
            elementLifting(elementIndex);
            extractMin();
        }
    }

    public void print(){
        for (int i = 0; i < m_heapSize; ++i){
            System.out.print(m_heapArray[i] + " ");
        }
        System.out.println();
    }

    private void elementLifting(final int elementIndex){
        if (elementIndex > 0){
            final int parentIndex = getParentIndex(elementIndex);
            if (m_heapArray[parentIndex] > m_heapArray[elementIndex]){
                swapElements(parentIndex, elementIndex);
                elementLifting(parentIndex);
            }
        }
    }

    private void minHeapify(final int elementIndex){
        final int leftChildIndex = getLeftChildIndex(elementIndex);
        final int rightChildIndex = getRightChildIndex(elementIndex);

        int minIndex = elementIndex;
        if (leftChildIndex < m_heapSize){
            if (m_heapArray[leftChildIndex] < m_heapArray[minIndex]){
                minIndex = leftChildIndex;
            }
        }
        if (rightChildIndex < m_heapSize){
            if (m_heapArray[rightChildIndex] < m_heapArray[minIndex]){
                minIndex = rightChildIndex;
            }
        }
        if (elementIndex != minIndex){
            swapElements(elementIndex, minIndex);
            minHeapify(minIndex);
        }
    }

    private int getLeftChildIndex(final int elementIndex){
        return (elementIndex * 2) + 1;
    }
    private int getRightChildIndex(final int elementIndex){
        return (elementIndex * 2) + 2;
    }
    private int getParentIndex(final int elementIndex) { return (elementIndex - 1) / 2; }

    private void swapElements(final int index1, final int index2){
        final int buf = m_heapArray[index1];
        m_heapArray[index1] = m_heapArray[index2];
        m_heapArray[index2] = buf;
    }

}
