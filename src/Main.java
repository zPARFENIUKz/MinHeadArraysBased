public class Main {
    public static void main(String[] args){
        MinHeap heap = new MinHeap(10);
        heap.insert(2);
        heap.print();
        heap.insert(3);
        heap.print();
        heap.insert(4);
        heap.print();
        System.out.println("extractMin execution: Min = " + heap.extractMin());
        heap.print();
        heap.decreaseKey(1, 1);
        heap.print();
        heap.delete(1);
        heap.print();
    }
}
