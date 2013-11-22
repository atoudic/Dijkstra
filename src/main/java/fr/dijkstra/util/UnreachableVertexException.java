package fr.dijkstra.util;


public class UnreachableVertexException extends Exception {
    public UnreachableVertexException(){
        System.out.println("No path between source and destination.");
    }
}
