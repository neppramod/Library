package pr.lib.algo.backtracking;

import java.util.Arrays;

public class HamiltonianCycle {
    public static void main(String[] args) {
        HamiltonianCycle hamiltonian =
                new HamiltonianCycle();
        /* Let us create the following graph
           (0)--(1)--(2)
            |   / \   |
            |  /   \  |
            | /     \ |
           (3)-------(4)    */
        int graph1[][] = {{0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 1},
                {0, 1, 1, 1, 0},
        };

        // Print the solution
        hamiltonian.hamCycle(graph1);

        int graph2[][] = {{0, 1, 0, 1, 0},
                {1, 0, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 0, 0},
                {0, 1, 1, 0, 0},
        };

        // Print the solution
        hamiltonian.hamCycle(graph2);
    }

    int V;
    int[] path;

    public void hamCycle(int[][] graph) {
        V = graph.length;
        path = new int[V];

        Arrays.fill(path, -1);
        path[0] = 0;

        if (hamCycle(graph, path, 1)) {
            printCycle();
        } else {
            System.out.println("No hamiltonian cycle");
        }
    }

    private boolean hamCycle(int[][] graph, int[] path, int v) {
        // if v reaches V (went through all nodes), check if there is a graph path from that node to 0th node in graph
        if (v == V) {
            if (path(graph, path[v-1], path[0])) {
                return true;
            } else {
                return false;
            }
        } else {
            for (int i = 1; i < V; i++) {

                // if path[v-1] is adjacent to i, and not added previously we can set path[v] as i
                if (isSafe(graph, path, v, i)) {
                    path[v] = i;
                    if (hamCycle(graph, path, v + 1)) {
                        return true;
                    } else {
                        path[v] = -1;
                    }
                }
            }
            return false;
        }
    }

    private boolean path(int[][] graph, int src, int dst) {
        return graph[src][dst] == 1;
    }


    private boolean isSafe(int[][] graph, int[] path, int src, int dst) {

        // check if dst vertex is adjacent to previously added vertex.
        if (graph[path[src-1]][dst] == 0) {
            return false;
        }

        // check if the dst vertex has already been added
        for (int i = 0; i < src; i++) {
            if (path[i] == dst) {
                return false;
            }
        }

        return true;
    }

    private void printCycle()
    {
        for (int i = 0; i < path.length; i++) {
            System.out.printf("%d ", path[i]);
        }
        System.out.println(path[0]);
    }
}
