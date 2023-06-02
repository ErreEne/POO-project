package ACO;

import java.util.PriorityQueue;

public class SortedPriorityQueue<T extends Comparable<T>> {
    private final PriorityQueue<T> queue;

    public SortedPriorityQueue() {
        queue = new PriorityQueue<>();
    }

    public void enqueue(T item) {
        queue.offer(item);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty.");
        }
        return queue.poll();
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty.");
        }
        return queue.peek();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }
}
