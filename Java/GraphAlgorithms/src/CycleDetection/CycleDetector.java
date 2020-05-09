package CycleDetection;

import java.util.List;

public class CycleDetector<T> {
    public boolean detect(List<Vertex<T>> graph) {
        for (Vertex<T> vertex : graph) {
            if (vertex.isAbsolutelyVisited()) {
                continue;
            }
            if (isThereCycle(vertex)) {
                return true;
            }
        }
        return false;
    }

    private boolean isThereCycle(Vertex<T> vertex) {
        vertex.setPartlyVisited(true);

        boolean isThereCycle = false;

        for (Vertex<T> neighbour : vertex.getNeighbours()) {
            if (neighbour.isPartlyVisited()) {
                return true;
            }
            if (!neighbour.isAbsolutelyVisited()) {
                 isThereCycle = isThereCycle(neighbour);
                neighbour.setAbsolutelyVisited(true);
            }
        }

        vertex.setPartlyVisited(false);
        vertex.setAbsolutelyVisited(true);
        return isThereCycle;
    }
}
