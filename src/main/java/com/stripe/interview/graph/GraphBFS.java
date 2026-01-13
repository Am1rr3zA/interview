package com.stripe.interview.graph;

import java.util.*;

class GraphBFS {
    private int vertices;
//    private LinkedList<Integer>[] adjacencyList;
    private LinkedList<Integer>[] adjacencyList;

    // Constructor
    public GraphBFS(int vertices) {
        this.vertices = vertices;
        adjacencyList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new LinkedList<>();
        }

//        List<Integer> nnn = new ArrayList<Integer>();
//        nnn.add(4);

    }

    // Add edge
    public void addEdge(int source, int destination) {
        adjacencyList[source].add(destination);

        // For undirected graph, add this line:
        // adjacencyList[destination].add(source);
    }

    // BFS traversal
    public void BFS(int startVertex) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[startVertex] = true;
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            System.out.print(vertex + " ");

            for (int neighbor : adjacencyList[vertex]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }

    public static class Main {
        public static void main(String[] args) {
            GraphBFS graph = new GraphBFS(6);

            graph.addEdge(0, 1);
            graph.addEdge(0, 2);
            graph.addEdge(1, 3);
            graph.addEdge(1, 4);
            graph.addEdge(2, 4);
            graph.addEdge(3, 5);
            graph.addEdge(4, 5);

            System.out.println("BFS starting from vertex 0:");
            graph.BFS(0);
        }
    }
}

