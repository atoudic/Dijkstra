package fr.dijkstra.util;


public class EmptyGraphException extends Exception {
    public EmptyGraphException(){
        System.out.println("Watch out : graph is empty!");
    }
}
