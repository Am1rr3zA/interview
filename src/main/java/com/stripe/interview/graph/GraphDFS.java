package com.stripe.interview.graph;

import java.util.*;

public class GraphDFS {

    private int vertices;
    private LinkedList<Integer>[] adjacencyList;

    // Constructor
    @SuppressWarnings("unchecked")
    public GraphDFS(int vertices) {
        this.vertices = vertices;
        adjacencyList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    // Add edge (for undirected graph)
    public void addEdge(int source, int destination) {
        adjacencyList[source].add(destination);
        adjacencyList[destination].add(source); // Remove this for directed graph
    }

    // DFS traversal - RECURSIVE approach
    public void DFSRecursive(int startVertex) {
        boolean[] visited = new boolean[vertices];

        System.out.println("DFS Recursive Traversal starting from vertex " + startVertex + ":");
        dfsHelper(startVertex, visited);
        System.out.println();
    }

    // Helper method for recursive DFS
    private void dfsHelper(int vertex, boolean[] visited) {
        // Mark current vertex as visited and print it
        visited[vertex] = true;
        System.out.print(vertex + " ");

        // Recur for all adjacent vertices
        for (int neighbor : adjacencyList[vertex]) {
            if (!visited[neighbor]) {
                dfsHelper(neighbor, visited);
            }
        }
    }

    // DFS traversal - ITERATIVE approach (using Stack)
    public void DFSIterative(int startVertex) {
        boolean[] visited = new boolean[vertices];
        Stack<Integer> stack = new Stack<>();

        // Push starting vertex onto stack
        stack.push(startVertex);

        System.out.println("DFS Iterative Traversal starting from vertex " + startVertex + ":");

        while (!stack.isEmpty()) {
            // Pop a vertex from stack
            int currentVertex = stack.pop();

            // If not visited, mark it visited and print it
            if (!visited[currentVertex]) {
                visited[currentVertex] = true;
                System.out.print(currentVertex + " ");

                // Push all unvisited neighbors onto stack
                // Iterate in reverse to maintain similar order as recursive
                List<Integer> neighbors = adjacencyList[currentVertex];
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    int neighbor = neighbors.get(i);
                    if (!visited[neighbor]) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        System.out.println();
    }

    // DFS to find path between two vertices
    public void findPath(int start, int end) {
        boolean[] visited = new boolean[vertices];
        List<Integer> path = new ArrayList<>();

        System.out.println("Finding path from " + start + " to " + end + ":");

        if (findPathHelper(start, end, visited, path)) {
            System.out.println("Path found: " + path);
        } else {
            System.out.println("No path exists!");
        }
    }

    private boolean findPathHelper(int current, int end, boolean[] visited, List<Integer> path) {
        visited[current] = true;
        path.add(current);

        // If we reached destination
        if (current == end) {
            return true;
        }

        // Explore neighbors
        for (int neighbor : adjacencyList[current]) {
            if (!visited[neighbor]) {
                if (findPathHelper(neighbor, end, visited, path)) {
                    return true;
                }
            }
        }

        // Backtrack if no path found through this vertex
        path.remove(path.size() - 1);
        return false;
    }

    // Print the graph
    public void printGraph() {
        System.out.println("Graph Adjacency List:");
        for (int i = 0; i < vertices; i++) {
            System.out.print("Vertex " + i + " -> ");
            System.out.println(adjacencyList[i]);
        }
        System.out.println();
    }

    // ============ TEST CASES ============
    public static void main(String[] args) {

        // Create a graph with 6 vertices (0 to 5)
        GraphDFS graph = new GraphDFS(6);

        /*
         * Graph Structure:
         *
         *       0
         *      / \
         *     1   2
         *    /|   |
         *   3 |   |
         *    \|   |
         *     4---+
         *     |
         *     5
         */

        // Add edges
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        // Print the graph
        graph.printGraph();

        // Run DFS - Recursive
        graph.DFSRecursive(0);

        // Run DFS - Iterative
        graph.DFSIterative(0);

        // Find path between vertices
        graph.findPath(0, 5);
        graph.findPath(3, 2);

        System.out.println("\n========== TEST CASE 2 ==========\n");

        // Create another graph with 5 vertices
        GraphDFS graph2 = new GraphDFS(5);

        /*
         * Graph Structure:
         *     0 --- 1
         *     |   / |
         *     |  /  |
         *     | /   |
         *     2     3
         *      \   /
         *       \ /
         *        4
         */

        graph2.addEdge(0, 1);
        graph2.addEdge(0, 2);
        graph2.addEdge(1, 2);
        graph2.addEdge(1, 3);
        graph2.addEdge(2, 4);
        graph2.addEdge(3, 4);

        graph2.printGraph();
        graph2.DFSRecursive(0);
        graph2.DFSIterative(0);

        System.out.println("\n========== TEST CASE 3: Disconnected Graph ==========\n");

        // Disconnected graph
        GraphDFS graph3 = new GraphDFS(6);

        /*
         * Graph Structure (disconnected):
         *   0 --- 1        4 --- 5
         *   |
         *   2 --- 3
         */

        graph3.addEdge(0, 1);
        graph3.addEdge(0, 2);
        graph3.addEdge(2, 3);
        graph3.addEdge(4, 5);

        graph3.printGraph();
        graph3.DFSRecursive(0);
        graph3.findPath(0, 5); // No path exists
    }
}
