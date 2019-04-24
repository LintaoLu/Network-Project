package com.example.demo.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class UndirectedGraph
{
    private Bag<Edge>[] graph;
    private double[][] matrix;

    public UndirectedGraph(String filename)
    {
        ReadMatrix square = new ReadMatrix(filename);
        matrix = square.getMatrix();
        buildGraph();
    }

    private void buildGraph()
    {
        int length = matrix.length;
        graph = new Bag[length+1];
        for(int i = 1; i < length+1; i++)
            graph[i] = new Bag();
        for(int row = 0; row < length; row++)
        {
            for(int column = 0; column < length; column++)
            {
                double number = matrix[row][column];
                if(number != 0 && number != Double.POSITIVE_INFINITY)
                {
                    Edge e = new Edge(row+1, column+1, number);
                    graph[row+1].add(e);
                }
            }
        }
    }

    //Add a new router.
    public void addNode(int from, int to, double distanceToFrom, double distanceToTo)
    {
        //'form' is the number of new router. If it less or equal to a router
        //that is already in graph, this is not a new router, just return.
        if(from <= graph.length-1) return;

        Edge edgeFrom = new Edge(from, to, distanceToTo);
        Edge edgeTo = new Edge(to, from, distanceToFrom);
        Bag<Edge>[] newGraph = (Bag<Edge>[]) new Bag[from+ 1];
        for (int i = 1; i < graph.length; i++)
        {
            newGraph[i] = graph[i];
        }
        for(int i = graph.length; i < from + 1; i++) newGraph[i] = new Bag<>();
        System.out.println(edgeFrom);
        System.out.println(edgeTo);
        //newGraph[from].add(edgeFrom);
        newGraph[from].add(edgeTo);
        newGraph[to].add(edgeFrom);
        //newGraph[to].add(edgeTo);
        graph = newGraph;
    }

    //Delete a router.
    public void deleteNode(int number)
    {
        if(number >= graph.length) return;
        Bag bag = graph[number];
        Iterator<Edge> iterator = bag.iterator();
        while(iterator.hasNext())
        {
            Edge edge = iterator.next();
            int child = edge.either(number);
            Bag newBag = new Bag();
            Iterator<Edge> childIterator = graph[child].iterator();
            while(childIterator.hasNext())
            {
                Edge e = childIterator.next();
                if(e.either(child) != number) newBag.add(e);
            }
            graph[child] = newBag;
        }
        graph[number] = new Bag<>();
    }

    public void printGraph()
    {
        for(int i = 1; i < graph.length; i++)
        {
            Iterator<Edge> iterator = graph[i].iterator();
            while(iterator.hasNext())
            {
                System.out.println(iterator.next());
            }
        }
    }

    public Bag<Edge>[] getGraph() { return graph; }

    public String printAllConnections(int v)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Router " + v + "\t\t-\n");
        HashMap<Integer, Double> hashMap = new HashMap<>();

        Iterator<Edge> iterator = graph[v].iterator();
        while(iterator.hasNext())
        {
            Edge e = iterator.next();
            hashMap.put(e.either(v), e.getDistance());
        }
        for(int i = 1; i < graph.length; i++)
        {
            if(i != v && !graph[i].isEmpty())
            {
                if(hashMap.containsKey(i)) sb.append("Router " + i + "\t\t" + hashMap.get(i)+"\n");
                else sb.append("Router " + i + "\t\tInfinity\n");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args)
    {
        UndirectedGraph undirectedGraph = new UndirectedGraph("square.txt");
        //undirectedGraph.deleteNode(2);
        //undirectedGraph.printGraph();
        System.out.println(undirectedGraph.printAllConnections(4));
    }
}

