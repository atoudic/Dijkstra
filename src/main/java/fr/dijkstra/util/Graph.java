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
        int n = vertices.size(), i = 0;
        while (i <= n && !(vertices.get(i).getName() == from)){
            i++;
        }
        if (!(i == n + 1)){
            Vertex fr = vertices.get(i);
            int m = fr.getEdges().size();
            int j = 0;
            while (j <= m && !(fr.getEdges().get(j).getTarget().getName() == to)){
                j++;
            }
            if (!(j == m+1)){
                return fr.getEdges().get(j).getDistance();
            }
            else return 0;
        }
        else return 0;
    }
}
