package TopologicalSort;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ApplicationDemo {
    private static Scanner scanner = new Scanner(System.in);

    private static void initVertexes(List<Vertex<Integer>> vertices) {
        System.out.println("Please enter number of vertexes : ");
        int num = scanner.nextInt();
        for (int index = 0; index < num; ++index) {
            System.out.println("Enter value for vertex number " + (index + 1) + ":");
            Integer data = scanner.nextInt();
            Vertex<Integer> vertex = new Vertex<Integer>(data);
            vertices.add(index, vertex);
        }
    }

    private static <T> void setNeighbours(List<Vertex<T>> vertices) {
        for (int index = 0; index < vertices.size(); ++index) {
            System.out.println("Please enter neighbours for vertex " + (index + 1) + " :");
            int neighbour = scanner.nextInt();
            Vertex<T> currentVertex = vertices.get(index);
            while(neighbour > 0 && neighbour <= vertices.size()) {
                --neighbour;
                Vertex<T> neighbourVertex = vertices.get(neighbour);
                currentVertex.addNeighbour(neighbourVertex);
                neighbour = scanner.nextInt();
            }
            System.out.println("Neighbours for current vertex initialization ended !");
        }
    }
    public static void main(String[] args) {
        TopologicalSort<Integer> topologicalSort = new TopologicalSort<Integer>();
        List<Vertex<Integer>> vertices = new LinkedList<>();
        initVertexes(vertices);
        setNeighbours(vertices);
        topologicalSort.topologicalSort(vertices);
        topologicalSort.printTopologicalSort();
    }
}
