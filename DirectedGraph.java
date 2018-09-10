import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a directed graph, of which vertices are represented by integers.
 */
public class DirectedGraph {

    private int numOfVertices;
    private ArrayList<ArrayList<Integer>> adjList;

    public DirectedGraph(int numOfVertices) {
        this.numOfVertices = numOfVertices;

        // initialize the adjacency list
        adjList = new ArrayList<>(numOfVertices);
        for (int i = 0; i < numOfVertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    /**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    public int V() {
        return numOfVertices;
    }

    /**
     * Returns all adjacency vertices of the given vertex.
     *
     * @param vertex the given vertex
     * @return all adjacency vertices of the given vertex
     */
    public List<Integer> adj(int vertex) {
        return adjList.get(vertex);
    }

    /**
     * Adds an edge to this graph.
     *
     * @param start the start node of the edge
     * @param end the end node of the edge
     */
    public void addEdge(int start, int end) {
        adjList.get(start).add(end);
    }

    /**
     * Returns true if this graph contains the given edge, otherwise false.
     *
     * @param start the start node of the edge
     * @param end the end node of the edge
     *
     * @return true if this graph contains the given edge, otherwise false
     */
    public boolean containsEdge(int start, int end) {
        return adjList.get(start).contains(end);
    }

}
