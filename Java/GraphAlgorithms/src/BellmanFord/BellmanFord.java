package BellmanFord;

public class BellmanFord<T> {
    private Graph<T> graph;

    public BellmanFord(Graph<T> graph) {
        this.graph = graph;
    }

    public boolean bellmanFord(Vertex<T> root) {
        root.setDistance(0);

        int numberOfIterations = this.graph.getVertices().size();
        for (int counter = 0; counter < numberOfIterations; ++counter) {
            for (Edge<T> edge : this.graph.getEdges()) {
                Vertex<T> startVertex = edge.getStart();
                Vertex<T> endVertex = edge.getEnd();

                if (startVertex.getDistance() == Double.MAX_VALUE) {
                    continue;
                }

                double calculatedDistance = startVertex.getDistance() + edge.getWeight();
                if (calculatedDistance < endVertex.getDistance()) {
                    endVertex.setPredecessor(startVertex);
                    endVertex.setDistance(calculatedDistance);
                }
            }
        }

        return this.isThereNegativeCycle();
    }

    private boolean isThereNegativeCycle() {
        for (Edge<T> edge : this.graph.getEdges()) {
            if (this.hasError(edge)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasError(Edge<T> edge) {
        return edge.getStart().getDistance() + edge.getWeight() < edge.getEnd().getDistance();
    }
}
