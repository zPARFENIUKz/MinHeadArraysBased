import java.util.*;
import java.util.stream.Stream;

public class MinHeap {
    private final int[] m_heapArray;
    private final int m_capacity;
    private int m_heapSize;

    MinHeap(final int capacity) throws IndexOutOfBoundsException{
        if (capacity <= 0) throw new IndexOutOfBoundsException("capaticy variable have to be greater than 0");
        m_heapArray = new int[capacity];
        m_capacity = capacity;
        m_heapSize = 0;
    }

    public int getMin() throws NoSuchElementException{
        if (!(m_heapSize > 0)) throw new NoSuchElementException("There's no elements in MinHeap");
        return m_heapArray[0];
    }

    public int extractMin() throws NoSuchElementException{
        if (!(m_heapSize > 0)) throw new NoSuchElementException("There's no elements in MinHeap");
        final int res = m_heapArray[0];
        if (m_heapSize > 1){
            m_heapArray[0] = m_heapArray[m_heapSize-1];
        }
        --m_heapSize;
        minHeapify(0);
        return res;
    }

    public void decreaseKey(final int index, final int newValue) throws IndexOutOfBoundsException, IllegalArgumentException{
        if (index < 0 || index >= m_heapSize) throw new IndexOutOfBoundsException("Incorrect index was passed");
        if (newValue >= m_heapArray[index]) throw new IllegalArgumentException("newValue must be less than replacable value");
        m_heapArray[index] = newValue;
        elementLifting(index);
    }

    public void insert(final int newValue) throws OutOfMemoryError{
        if (m_heapSize + 1 <= m_capacity){
            ++m_heapSize;
            m_heapArray[m_heapSize - 1] = newValue;
            elementLifting(m_heapSize - 1);
        } else throw new OutOfMemoryError("no remainig memory");
    }

    public void delete(final int index) throws NoSuchElementException{
        if (index < 0 || index >= m_heapSize) throw new NoSuchElementException("Incorrect index, no such element in the heap");
        m_heapArray[index] = m_heapArray[0] - 1;
        elementLifting(index);
        extractMin();
    }

    public void print(){
        for (int i = 0; i < m_heapSize; ++i){
            System.out.print(m_heapArray[i] + " ");
        }
        System.out.println();
    }

    private void elementLifting(int index){
        while (index > 0 && m_heapArray[parentIndex(index)] > m_heapArray[index]){
            swap(index, parentIndex(index));
            index = parentIndex(index);
        }
    }

    private void minHeapify(final int index){
        final List<Integer> childIndexes = Stream.of(leftChildIndex(index), rightChildIndex(index)).filter(Objects::nonNull).toList();
        if (!childIndexes.isEmpty()){
            int minIndex = 0;
            for (final int i : childIndexes){
                if (m_heapArray[i] < m_heapArray[minIndex]) minIndex = i;
            }
            if (index != minIndex){
                swap(index, minIndex);
                minHeapify(minIndex);
            }
        }
    }

    private void swap(final int index1, final int index2){
        final int buf = m_heapArray[index1];
        m_heapArray[index1] = m_heapArray[index2];
        m_heapArray[index2] = buf;
    }

    private Integer parentIndex(final int index){
        return (index - 1) / 2;
    }

    private Integer leftChildIndex(final int index){
        final int res = (2 * index) + 1;
        if (res < m_heapSize) return res;
        return null;
    }
    private Integer rightChildIndex(final int index){
        final int res = (2 * index) + 2;
        if (res < m_heapSize) return res;
        return null;
    }



}
