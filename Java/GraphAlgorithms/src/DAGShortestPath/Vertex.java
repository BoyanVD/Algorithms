package DAGShortestPath;

import java.util.LinkedList;
import java.util.List;

public class Vertex<T> implements Comparable<Vertex<T>> {
    private T data;
    private double distance;
    private Vertex<T> predecessor;
    private List<Edge<T>> ancestors;
    private boolean isVisited;

    Vertex(T data) {
        this.data = data;
        this.distance = Double.MAX_VALUE;
        this.ancestors = new LinkedList<>();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Vertex<T> getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Vertex<T> predecessor) {
        this.predecessor = predecessor;
    }

    public List<Edge<T>> getAncestors() {
        return ancestors;
    }

    public void setAncestors(List<Edge<T>> ancestors) {
        this.ancestors = ancestors;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    @Override
    public int compareTo(Vertex<T> other) {
        return Double.compare(this.distance, other.getDistance());
    }
}
