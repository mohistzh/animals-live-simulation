package com.github.mohistzh.model;

import java.util.LinkedList;
import java.util.stream.IntStream;

/**
 * Friendship data model
 * @Author Jonathan
 * @Date 2020/3/4
 **/
public class FriendshipGraph {
    // number of vertices
    private final int V;

    // number of edges
    private int E;

    // adjacency table
    private LinkedList<Integer>[] adj;

    /**
     * Create a graph with the number of vertices
     * @param V
     */
    public FriendshipGraph(int V) {
        this.V = V;
        adj = new LinkedList[V];
        IntStream.range(0, V).forEach( i -> adj[i] = new LinkedList<>());
    }
    /**
     * Add an edge  v -> w
     * @param v
     * @param w
     */
    public void addEdge(int v, int w) {
        if (v < 1 || w < 1) {
            return;
        }
        adj[v].add(w);
        //adj[w].add(v);
        E++;
    }

    /**
     * Get all adjacent vertices of input v
     * @param v vertex
     * @return
     */
    public LinkedList<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * number of vertices
     * @return
     */
    public int getV() {
        return this.V;
    }

    /**
     * number of edges
     * @return
     */
    public int getE() {
        return this.E;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getV() - 1);
        stringBuilder.append(" vertices, ");
        stringBuilder.append(this.getE());
        stringBuilder.append(" edges\n");

        for (int i = 1; i < this.getV(); i++) {
            stringBuilder.append(i);
            stringBuilder.append(": ");
            for (int w : this.adj[i]) {
                stringBuilder.append(w);
                stringBuilder.append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

}
