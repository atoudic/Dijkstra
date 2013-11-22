package fr.dijkstra.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GraphTest {
    private Vertex lille = new Vertex("Lille");
    private Vertex paris = new Vertex("Paris");
    private Vertex reims = new Vertex("Reims");
    private Vertex nancy = new Vertex("Nancy");
    private Vertex lyon = new Vertex("Lyon");
    private Vertex marseille = new Vertex("Marseille");
    private Vertex lemans = new Vertex("Le Mans");
    private Vertex nantes = new Vertex("Nantes");
    private Vertex bordeaux = new Vertex("Bordeaux");
    private Vertex toulouse = new Vertex("Toulouse");
    private Vertex clermont = new Vertex("Clermont Ferrant");
    private Vertex montpellier = new Vertex("Montpellier");

    @Before
    public void setup() {
        lille.connectTo(reims, 206);
        lille.connectTo(paris, 222);
        lille.connectTo(nancy, 418);

        reims.connectTo(paris, 144);
        reims.connectTo(nancy, 245);
        reims.connectTo(lyon, 489);

        paris.connectTo(lyon, 465);
        paris.connectTo(lemans, 208);
        paris.connectTo(clermont, 423);

        lyon.connectTo(clermont, 166);
        lyon.connectTo(marseille, 313);
        lyon.connectTo(montpellier, 304);

        lemans.connectTo(nantes, 189);
        lemans.connectTo(bordeaux, 443);

        nantes.connectTo(bordeaux, 347);

        bordeaux.connectTo(toulouse, 243);

        toulouse.connectTo(montpellier, 245);

        montpellier.connectTo(marseille, 169);
        montpellier.connectTo(toulouse, 245);

        marseille.connectTo(montpellier, 169);

        clermont.connectTo(lyon, 166);
        clermont.connectTo(montpellier, 333);
        clermont.connectTo(marseille, 474);
    }

    @Test
    public void getDistanceForTwoAdjacentVertices() throws EmptyGraphException, UnreachableVertexException, OutOfGraphException {
        Graph graph = new Graph(paris, lyon);

        Assert.assertEquals(graph.getDistance("Paris", "Lyon"), 465);
    }

    @Test
    public void getDistanceForTwoVerticesWithOneIntermediary() throws EmptyGraphException, UnreachableVertexException, OutOfGraphException {
        Graph graph = new Graph(paris, lyon, marseille);

        Assert.assertEquals(graph.getDistance("Paris", "Marseille"), 778);
    }

    @Test
    public void getDistanceForThreeVerticesWithTwoIntermediaries() throws EmptyGraphException, UnreachableVertexException, OutOfGraphException {
        Graph graph = new Graph(paris, lyon, montpellier, toulouse);

        Assert.assertEquals(graph.getDistance("Paris", "Toulouse"), 1014);
    }

    @Test
    public void getDistanceWithMultipleChoices() throws EmptyGraphException, UnreachableVertexException, OutOfGraphException {
        Graph graph = new Graph(lille, reims, paris);

        Assert.assertEquals(graph.getDistance("Lille", "Paris"), 222);
    }

    @Test
    public void getDistanceAvoidingCycles() throws EmptyGraphException, UnreachableVertexException, OutOfGraphException {
        Graph graph = new Graph(lemans, nantes, bordeaux, toulouse, montpellier);

        Assert.assertEquals(graph.getDistance("Le Mans", "Montpellier"), 931);
    }

    @Test(expected=EmptyGraphException.class)
     public void emptyGraph() throws EmptyGraphException, UnreachableVertexException, OutOfGraphException {
        Graph graph = new Graph();
        graph.getDistance("Paris", "Marseille");
    }

    @Test(expected=UnreachableVertexException.class)
    public void noPath() throws EmptyGraphException, UnreachableVertexException, OutOfGraphException {
        Graph graph = new Graph(lemans, paris, lyon);
        graph.getDistance("Lyon", "Le Mans");
    }

    @Test(expected=OutOfGraphException.class)
    public void outOfGraph() throws EmptyGraphException, UnreachableVertexException, OutOfGraphException {
        Graph graph = new Graph(lemans, paris, lyon);
        graph.getDistance("Lyon", "Marseille");
    }


}