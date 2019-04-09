package com.example.demo.Controllers;

public class Run
{
    public static void main(String[] args)
    {
        UndirectedGraph undirectedGraph = new UndirectedGraph("file.txt");
        FindAllPath findAllPath = new FindAllPath(undirectedGraph.getGraph());
        findAllPath.findAllPath(1, 5);
        findAllPath.printPath();
        //undirectedGraph.deleteNode(2);
        //undirectedGraph.addNode(6, 5, 100);
        //undirectedGraph.addNode(7, 6, 10);
        ShortestPath shortestPath = new ShortestPath(undirectedGraph.getGraph());
        double answer = shortestPath.findShortestPath(1, 5);
        System.out.println(shortestPath.tracePath(5));
        shortestPath.tracePath(5);
    }
}