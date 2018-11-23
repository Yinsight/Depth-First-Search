import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DFS {

    private static final int WHITE = 0;
    private static final int GRAY  = 1;
    private static final int BLACK = 2;

    private static final int NIL = -1;

    private static int[] color;
    private static int[] pi;
    private static int[] d;
    private static int[] f;

    private static int time = 0;

    static void dfs(DirectedGraph g) {
        int V = g.V(); // get the range of vertices [0, V)

        pi    = createArray(V, NIL);   // the initial values of the array 'pi' are NIL
        color = createArray(V, WHITE); // the initial values of the array 'color' are WHITE

        d = createArray(V, 0);
        f = createArray(V, 0);

        for (int u = 0; u < V; u++) {  // for each vertex in the graph
            if (color[u] == WHITE) {   // the vertex u has not been visited yet
                visit(g, u);
            }
        }

    }

    static void visit(DirectedGraph g, int u) {
        time = time + 1;
        d[u] = time;
        color[u] = GRAY;

        List<Integer> adj_u = g.adj(u);
        for (int v : adj_u) {  // for each vertex directed from u
            if (color[v] == WHITE) {  // the vertex v has not been visited yet
                pi[v] = u;    // this indicates that the vertex v was visited from vertex u
                visit(g, v);  // visit the vertex v
            }
        }

        color[u] = BLACK;
        time = time + 1;
        f[u] = time;
    }

    static int[] createArray(int length, int initialValue) {
        int[] array = new int[length];

        for (int i = 0; i < length; i++) {
            array[i] = initialValue;
        }

        return array;
    }

    static DirectedGraph createGraphRandomly(int numOfNodes, int numOfEdges) {
        DirectedGraph g = new DirectedGraph(numOfNodes);

        Random random = new Random();

        for (int i = 0; i < numOfEdges;) {
            int start = random.nextInt(numOfNodes); 
            int end = random.nextInt(numOfNodes); 

            if (start == end || g.containsEdge(start, end)) {
                continue;
            }

            // the graph does not contain this edge(start -> end)
            g.addEdge(start, end);

            i++;
        }

        return g;
    }

    public static void main(String[] args) {

        try (Scanner stdin = new Scanner(System.in)) {
            System.out.print("Please enter the number of nodes: ");
            int numOfNodes = stdin.nextInt();

            System.out.print("Please enter the number of edges: ");
            int numOfEdges = stdin.nextInt();

            DirectedGraph graph = createGraphRandomly(numOfNodes, numOfEdges);

            long begin = System.nanoTime();

            dfs(graph);  // use dfs algorithm to visit the graph

            long end = System.nanoTime();
            double time = (end - begin) / 1E3;  // unit is microsecond

            System.out.printf("Running time: %.3f us\n", time);
        }

    }
}
