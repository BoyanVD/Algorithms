package Dfs;

import java.util.List;
import java.util.Stack;

public class DFS<T> {
    private Stack<Vertex<T>> stack;

    public DFS() {
        this.stack = new Stack<>();
    }

    public void dfs(List<Vertex<T>> graph) {
        for (Vertex<T> vertex : graph) {
            if (!vertex.isVisited()) {
                this.stackDfs(vertex);
            }
        }
    }

    private void stackDfs(Vertex<T> root) {
        root.setVisited(true);
        this.stack.push(root);

        while (!this.stack.isEmpty()) {
            Vertex<T> currentVertex = this.stack.pop();
            System.out.println(currentVertex.getData());

            for(Vertex<T> vertex : currentVertex.getNeighbours()) {
                if (!vertex.isVisited()) {
                    vertex.setVisited(true);
                    this.stack.push(vertex);
                }
            }
        }
    }

}
