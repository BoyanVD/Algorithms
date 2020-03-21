package TopologicalSort;

import java.util.LinkedList;
import java.util.List;

public class Vertex<T> {
    private T data;
    private List<Vertex<T>> neighbours;
    private boolean isVisited;

    Vertex(T data) {
        this.data = data;
        this.neighbours = new LinkedList<>();
    }

    public void addNeighbour(Vertex<T> neighbour) {
        this.neighbours.add(neighbour);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<Vertex<T>> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<Vertex<T>> neighbours) {
        this.neighbours = neighbours;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    @Override
    public String toString() {
        return this.data.toString();
    }
}
