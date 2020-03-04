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
        adj[v].add(w);
        adj[w].add(v);
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

    public int getV() {
        return this.V;
    }

    public int getE() {
        return this.E;
    }


}
