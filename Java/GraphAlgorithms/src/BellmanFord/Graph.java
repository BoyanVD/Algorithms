package BellmanFord;

import java.util.List;

public class Graph<T> {
    private List<Vertex<T>> vertices;
    private List<Edge<T>> edges;

    Graph(List<Vertex<T>> vertices, List<Edge<T>> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    public void addVertex(Vertex<T> vertex) {
        this.vertices.add(vertex);
    }

    public void addEdge(Edge<T> edge) {
        this.edges.add(edge);
    }

    public List<Vertex<T>> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertex<T>> vertices) {
        this.vertices = vertices;
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge<T>> edges) {
        this.edges = edges;
    }
}
