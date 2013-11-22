package fr.dijkstra.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {
    private List<Vertex> vertices = new ArrayList<Vertex>();

    public Graph(Vertex... vertices) {
        this.vertices.addAll(Arrays.asList(vertices));
    }

    public int getDistance(String from, String to) {
        return 0;
    }
}
