package DAGShortestPath;

import java.util.List;
import java.util.Stack;

public class DAGShortestPath<T> {
    public void findShortestPath(List<Vertex<T>> vertices, Vertex<T> root, Vertex<T> target) throws DAGShortestPathException {
        root.setDistance(0);
        TopologicalSort<T> topologicalSort = new TopologicalSort<T>();
        topologicalSort.sort(vertices);
        Stack<Vertex<T>> sortedVertexes = topologicalSort.getAscendingTopologicalSort();

        while(!sortedVertexes.isEmpty()) {
            Vertex<T> current = sortedVertexes.pop();

            for (Edge<T> edge : current.getAncestors()) {
                Vertex<T> start = edge.getStart();
                Vertex<T> end = edge.getEnd();

                double calculatedDistance = start.getDistance() + edge.getWeight();
                if (calculatedDistance < end.getDistance()) {
                    end.setDistance(calculatedDistance);
                    end.setPredecessor(start);
                }
            }
        }

        if (target.getDistance() == Double.MAX_VALUE) {
            throw new DAGShortestPathException("There is no path from root to target vertex !");
        }
    }

    public Stack<Vertex<T>> restorePathToVertex(Vertex<T> target) throws DAGShortestPathException {
        if (target.getDistance() == Double.MAX_VALUE) {
            throw new DAGShortestPathException("You don't have shortest path on target vertex given !");
        }

        Stack<Vertex<T>> stack = new Stack<>();
        Vertex<T> current = target;
        while(current != null) {
            stack.push(current);
            current = current.getPredecessor();
        }
        return stack;
    }
}
