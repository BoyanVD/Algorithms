package Dijkstra;

import java.util.PriorityQueue;
import java.util.Stack;

public class Dijkstra<T> {

    private PriorityQueue<Vertex<T>> priorityQueue;

    public Dijkstra() {
        this.priorityQueue = new PriorityQueue<>();
    }

    public void dijkstra(Vertex<T> root) {
        if (root == null) {
            throw new DijkstraException("Root vertex value is null !");
        }

        root.setDistance(0);
        this.priorityQueue.add(root);

        while(!this.priorityQueue.isEmpty()) {
            Vertex<T> current = this.priorityQueue.poll();
            for (Edge<T> edge : current.getAncestors()) {
                Vertex<T> vertex = edge.getEnd();
                double calculatedDistance = current.getDistance() + edge.getWeight();

                if (calculatedDistance < vertex.getDistance()) {
                    this.priorityQueue.remove(vertex);
                    vertex.setDistance(calculatedDistance);
                    vertex.setPredecessor(current);
                    this.priorityQueue.add(vertex);
                }
            }
        }
    }

    public Stack<Vertex<T>> getPathStack(Vertex<T> target) {
        if (target == null) {
            throw new DijkstraException("Target vertex value is null !");
        }

        Vertex<T> vertex = target;
        Stack<Vertex<T>> stack = new Stack<>();
        while(vertex != null) {
            stack.push(vertex);
            vertex = vertex.getPredecessor();
        }
        return stack;
    }
}
