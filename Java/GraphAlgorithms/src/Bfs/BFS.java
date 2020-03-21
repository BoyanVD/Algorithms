package Bfs;

import java.util.LinkedList;
import java.util.Queue;

public class BFS<T> {
    public void bfs(Vertex<T> root) {
        Queue<Vertex<T>> queue = new LinkedList<>();

        root.setVisited(true);
        queue.add(root);

        while(!queue.isEmpty()) {
            Vertex<T> currentVertex = queue.remove();
            System.out.println(currentVertex);

            for (Vertex<T> vertex : currentVertex.getNeighbours()) {
                if (!vertex.isVisited()) {
                    vertex.setVisited(true);
                    queue.add(vertex);
                }
            }
        }
    }
}
