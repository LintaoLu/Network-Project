package com.example.demo.Controllers;

import java.util.Iterator;
import java.util.Stack;

public class ShortestPath
{
    private Bag<Edge>[] graph;
    private boolean[] marked;
    private double[] shortestPath;
    private int[] edgeTo;

    public double getShortestPath(int node)
    {
        return shortestPath[node];
    }

    public ShortestPath(Bag<Edge>[] graph)
    {
        this.graph = graph;
        marked = new boolean[graph.length];
        shortestPath = new double[graph.length];
        edgeTo = new int[graph.length];
        for(int i = 0; i < graph.length; i++)
        {
            marked[i] = false;
            shortestPath[i] = Double.POSITIVE_INFINITY;
            edgeTo[i] = 0;
        }
    }

    public double findShortestPath(int from, int to)
    {
        findShortestPath(from);
        return shortestPath[to];
    }

    public String tracePath(int to)
    {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        while(to != 0)
        {
            stack.push(to);
            to = edgeTo[to];
        }
        while(!stack.empty())
        {
            sb.append(stack.pop() + " -> ");
        }
        sb.setLength(sb.length() - 3);
        return sb.toString();
    }

    //Lazy implementation for shortest path
    private void findShortestPath(int from)
    {
        IndexMinPQ indexMinPQ = new IndexMinPQ(graph.length+1);
        shortestPath[from] = 0;
        indexMinPQ.add(from, 0);
        while(!indexMinPQ.isEmpty())
        {
            int parent = indexMinPQ.popTop();
            marked[parent] = true;
            Iterator<Edge> iterator = graph[parent].iterator();
            while(iterator.hasNext())
            {
                Edge edge = iterator.next();
                int child = edge.either(parent);
                if(!marked[child])
                {
                    double distanceToChild = shortestPath[parent] + edge.getDistance();
                    if(distanceToChild < shortestPath[child])
                    {
                        shortestPath[child] = distanceToChild;
                        edgeTo[child] = parent;
                    }
                    indexMinPQ.add(child, shortestPath[child]);
                }
            }
        }
    }
}

