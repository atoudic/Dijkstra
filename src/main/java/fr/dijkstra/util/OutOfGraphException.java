package fr.dijkstra.util;


public class OutOfGraphException extends Exception {
    public OutOfGraphException(){
        System.out.println("Watch out : source or/and destination is out of the graph!");
    }
}
