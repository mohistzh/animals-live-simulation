package com.github.mohistzh.repo.model;

import java.util.LinkedHashSet;
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
    private LinkedHashSet<Integer>[] adj;

    /**
     * Create a graph with the number of vertices
     * @param V
     */
    public FriendshipGraph(int V) {
        this.V = V;
        adj = new LinkedHashSet[V];
        IntStream.range(0, V).forEach( i -> adj[i] = new LinkedHashSet<>());
    }
    /**
     * Add an edge  v -> w
     * @param v
     * @param w
     */
    public void addFriendship(int v, int w) {
        if (v < 1 || w < 1) {
            return;
        }
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    /**
     * Break up friendship between v and w
     * @param v
     * @param w
     */
    public void removeFriendship(int v, int w) {
        adj[v].remove(w);
        adj[w].remove(v);
        E--;
    }

    /**
     * How many friends do I have?
     * @param v
     * @return
     */
    public int numberOfFriends(int v) {
        return adj[v].size();
    }

    /**
     * Get all friends by input id
     * @param v vertex
     * @return
     */
    public LinkedHashSet<Integer> getFriends(int v) {
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
