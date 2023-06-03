package DSS;

import java.util.PriorityQueue;
import java.util.Random;

public class SortedPriorityQueue<T extends Comparable<T>> {
    private final PriorityQueue<T> queue;

    public SortedPriorityQueue() {
        queue = new PriorityQueue<>();
    }

    public void insertQueue(T event) {
        queue.offer(event); // Insert into the priority queue
    }

    public T getFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority queue is empty.");
        }
        return queue.poll(); // Returns the Event at the top of the current queue and removes it
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /*
     * public record Event(String name, int timestamp, String type) implements
     * Comparable<Event> {
     * 
     * @Override
     * public int compareTo(Event other) {
     * // Compare based on the values
     * return Integer.compare(this.timestamp, other.timestamp);
     * }
     * }
     */
}
