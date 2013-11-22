package fr.dijkstra.util;

import java.util.*;

public class Graph {
    private List<Vertex> vertices = new ArrayList<Vertex>();
    private Set<Vertex> visited;
    private Set<Vertex> unvisited;
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Integer> distance;

    public Graph(Vertex... vertices) {
        this.vertices.addAll(Arrays.asList(vertices));
        visited = new HashSet<Vertex>();
        unvisited = new HashSet<Vertex>();
        distance = new HashMap<Vertex, Integer>();
        predecessors = new HashMap<Vertex, Vertex>();
    }

    public int getDistance(String from, String to)
            throws EmptyGraphException, UnreachableVertexException, OutOfGraphException {
        int n = vertices.size();
        if (n == 0){
            throw new EmptyGraphException();
        }
        else{
        Vertex source = null;
        Vertex target = null;

        int i = 0, count = 0;
        while (i <n && count != 2){
            String name = vertices.get(i).getName();
            if (name == from){
                source = vertices.get(i);
                count++;
            }
            else if (name == to){
                target = vertices.get(i);
                count++;
            }
            i++;
        }

        if (count != 2)
            throw new OutOfGraphException();
        else{
        distance.put(source, 0);
        unvisited.add(source);
        while (unvisited.size() > 0) {
            Vertex vertex = getMinimum(unvisited);
            visited.add(vertex);
            unvisited.remove(vertex);
            findMinimalDistances(vertex);
        }

        int d = algorithmDistance(target);
        if (d == Integer.MAX_VALUE)
            throw new UnreachableVertexException();
        else return d;
        }
        }

    }

    private Vertex getMinimum(Set<Vertex> unvisited) {
        Vertex min = null;
        for (Vertex vertex : unvisited) {
            if (min == null) {
                min = vertex;
            } else {
                if (algorithmDistance(vertex) < algorithmDistance(min)) {
                    min = vertex;
                }
            }
        }
        return min;
    }

    private int algorithmDistance(Vertex vertex) {
        Integer d = distance.get(vertex);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    private boolean isVisited(Vertex vertex) {
        return visited.contains(vertex);
    }

    private List<Vertex> getNeighbors(Vertex vertex) {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        for (Edge edge : vertex.getEdges()) {
            Vertex target = edge.getTarget();
            if (vertices.contains(target) && !isVisited(target)){
                neighbors.add(target);
            }
        }
        return neighbors;
    }

    private int neighborsDistance(Vertex source, Vertex target){
        List<Edge> edges = source.getEdges();
        int n = edges.size(), i = 0;
        while (i < n && edges.get(i).getTarget() != target)
            i++;
        return edges.get(i).getDistance();
    }

    private void findMinimalDistances(Vertex vertex) {
        List<Vertex> neighbors = getNeighbors(vertex);
        for (Vertex target : neighbors) {
            if (algorithmDistance(target) >
                    algorithmDistance(vertex) + neighborsDistance(vertex, target)){

                distance.put(target,
                        algorithmDistance(vertex) + neighborsDistance(vertex, target));
                predecessors.put(target, vertex);
                unvisited.add(target);
            }
        }

    }

}
