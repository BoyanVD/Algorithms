package CycleDetection;

import java.util.LinkedList;
import java.util.List;

public class Vertex<T> {
    private T data;
    private boolean isAbsolutelyVisited;
    private boolean isPartlyVisited;
    private List<Vertex<T>> neighbours;

    public Vertex(T data) {
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

    public boolean isAbsolutelyVisited() {
        return isAbsolutelyVisited;
    }

    public void setAbsolutelyVisited(boolean absolutelyVisited) {
        isAbsolutelyVisited = absolutelyVisited;
    }

    public boolean isPartlyVisited() {
        return isPartlyVisited;
    }

    public void setPartlyVisited(boolean partlyVisited) {
        isPartlyVisited = partlyVisited;
    }

    public List<Vertex<T>> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<Vertex<T>> neighbours) {
        this.neighbours = neighbours;
    }

    @Override
    public String toString() {
        return this.data.toString();
    }
}
