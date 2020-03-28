package DAGShortestPath;

import java.util.List;
import java.util.Stack;

public class TopologicalSort<T> {
    private Stack<Vertex<T>> stack;

    TopologicalSort() {
        this.stack = new Stack<>();
    }

    public void sort(List<Vertex<T>> vertices) {
        for (Vertex<T> vertex : vertices) {
            if (!vertex.isVisited()) {
                this.dfs(vertex);
            }
        }
    }

    private void dfs(Vertex<T> vertex) {
        vertex.setVisited(true);
        for (Edge<T> edge : vertex.getAncestors()) {
            Vertex<T> target = edge.getEnd();
            if (!target.isVisited()) {
                this.dfs(target);
            }
        }
        this.stack.push(vertex);
    }

    public Stack<Vertex<T>> getAscendingTopologicalSort() {
        Stack<Vertex<T>> reversed = new Stack<>();
        while (!this.stack.isEmpty()) {
            reversed.push(this.stack.pop());
        }
        return reversed;
    }

    public Stack<Vertex<T>> getDescendingTopologicalSort() {
        return this.stack;
    }
}
