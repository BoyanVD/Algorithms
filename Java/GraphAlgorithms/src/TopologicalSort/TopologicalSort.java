package TopologicalSort;

import java.util.List;
import java.util.Stack;

public class TopologicalSort<T> {

    private Stack<Vertex<T>> sortedGraphVertexes;

    public TopologicalSort() {
        this.sortedGraphVertexes = new Stack<>();
    }

    public void topologicalSort(List<Vertex<T>> graph) {
        for (Vertex<T> vertex : graph) {
            if(!vertex.isVisited()) {
                sort(vertex);
            }
        }
    }

    private void sort(Vertex<T> current) {
        current.setVisited(true);

        for (Vertex<T> vertex : current.getNeighbours()) {
            if (!vertex.isVisited()) {
                sort(vertex);
            }
        }

        this.sortedGraphVertexes.push(current);
    }

    public Stack<Vertex<T>> getSortedGraphVertexesStack() {
        return this.sortedGraphVertexes;
    }

    public void printTopologicalSort() {
        Stack<Vertex<T>> helperStack = new Stack<>();
        int counter = 1;
        while(!this.sortedGraphVertexes.isEmpty()) {
            Vertex<T> current = this.sortedGraphVertexes.pop();
            System.out.println("Vertex number " + counter + " is : " + current.toString());
            helperStack.push(current);
        }
        this.sortedGraphVertexes = helperStack;
    }
}
