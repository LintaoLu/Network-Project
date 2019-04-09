package com.example.demo.Controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class FindAllPath
{
    private Stack<Integer> path = new Stack<>();
    private boolean[] onPath;
    private Bag<Edge>[] graph;
    private ArrayList<ArrayList<Integer>> result;

    public FindAllPath(Bag<Edge>[] graph)
    {
        this.graph = graph;
        onPath = new boolean[graph.length];
        result = new ArrayList<>();
    }

    public ArrayList<ArrayList<Integer>> findAllPath(int from, int to)
    {
        findPath(from, to);
        return result;
    }

    private void findPath(int from, int to)
    {
        path.push(from);
        onPath[from] = true;
        if(from == to)
        {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.addAll(path);
            result.add(temp);
        }
        else
            {
                Iterator<Edge> iterator = graph[from].iterator();
                while(iterator.hasNext())
                {
                    Edge e = iterator.next();
                    int child = e.either(from);
                    if(!onPath[child]) findPath(child, to);
                }
            }
        path.pop();
        onPath[from] = false;
    }

    public ArrayList printPath() {
        ArrayList list = new ArrayList();
        for (ArrayList<Integer> path : result) {
            StringBuilder sb = new StringBuilder();
            for (int i : path) {
                sb.append(i + " -> ");
            }
            sb.setLength(sb.length() - 3);
            list.add(sb.toString());
            //System.out.println(sb.toString());
        }
        return list;
    }
}