package com.example.demo.Controllers;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;

@RestController
public class Controller {

    private UndirectedGraph undirectedGraph;
    private HashSet<Integer> set;

    public Controller()
    {
        undirectedGraph = new UndirectedGraph("file.txt");
        set = new HashSet<>();
        for(int i = 1; i < undirectedGraph.getGraph().length; i++) set.add(i);
    }

    @CrossOrigin
    @PostMapping("/file")
    public ArrayList getHello(@RequestParam("file") String file) {

        writeBase64File(file);
        FindAllPath findAllPath = new FindAllPath(undirectedGraph.getGraph());
        findAllPath.findAllPath(1, 5);
        System.out.println(findAllPath.printPath());

        return findAllPath.printPath();
    }

    public void writeBase64File(String file) {

        byte[] data = Base64.decodeBase64(file);
        try {
            OutputStream stream = new FileOutputStream("file.txt");
            stream.write(data);
        } catch (IOException e) {

        }
    }

    @CrossOrigin
    @PostMapping("/allPaths")
    public String getAllPaths(@RequestParam("from") String from, @RequestParam("to") String to){

        try{
            Integer.valueOf(from);
            Integer.valueOf(to);
        }
        catch(Exception e){
            return "something went wrong";
        }
        int graphSize = undirectedGraph.getGraph().length;
        if(!set.contains(Integer.valueOf(from)) || !set.contains(Integer.valueOf(to)))
            return "Out of boundary!";
        FindAllPath findAllPath = new FindAllPath(undirectedGraph.getGraph());
        ArrayList<ArrayList<Integer>> paths =
                findAllPath.findAllPath(Integer.valueOf(from), Integer.valueOf(to));
        StringBuilder sb = new StringBuilder();
        int pathNumber = 1;
        for (ArrayList<Integer> singlePath : paths)
        {
            sb.append("path "+pathNumber + ": ");
            for(int node: singlePath)
            {
                sb.append(node + " -> ");
            }
            sb.setLength(sb.length()-3);
            sb.append("\n");
            pathNumber++;
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    @PostMapping("/shortestpathcost")
    @CrossOrigin
    public double getShortestPathCost(@RequestParam("from") String from, @RequestParam("to") String to){
        try{
            Integer.valueOf(from);
            Integer.valueOf(to);
        }
        catch(Exception e){
            return Double.POSITIVE_INFINITY;
        }
        int graphSize = undirectedGraph.getGraph().length;
        if(!set.contains(Integer.valueOf(from)) || !set.contains(Integer.valueOf(to)))
            return Double.POSITIVE_INFINITY;
        ShortestPath shortestPath = new ShortestPath(undirectedGraph.getGraph());
        shortestPath.findShortestPath(Integer.valueOf(from), Integer.valueOf(to));
        return shortestPath.getShortestPath(Integer.valueOf(to));
    }

    @PostMapping("/shortestPath")
    @CrossOrigin
    public String getShortestpath(@RequestParam("from") String from, @RequestParam("to") String to){
        try{
            Integer.valueOf(from);
            Integer.valueOf(to);
        }
        catch(Exception e){
            return "something went wrong";
        }
        if(!set.contains(Integer.valueOf(from)) || !set.contains(Integer.valueOf(to)))
            return "Out of boundary!";
        ShortestPath shortestPath = new ShortestPath(undirectedGraph.getGraph());
        shortestPath.findShortestPath(Integer.valueOf(from), Integer.valueOf(to));
        System.out.println(shortestPath.tracePath(Integer.valueOf(to)));

        return shortestPath.tracePath(Integer.valueOf(to));
    }

    @CrossOrigin
    @PostMapping("deleteNode")
    private String deleteNode(@RequestParam("nodeNumber") String nodeNumber) {
        try{
            Integer.valueOf(nodeNumber);
        }
        catch(Exception e){
            return "something went wrong";
        }
        if(!set.contains(Integer.valueOf(nodeNumber))) return "This node doesn't exist!";
        undirectedGraph.deleteNode(Integer.valueOf(nodeNumber));
        set.remove(Integer.valueOf(nodeNumber));
        return "success";
    }

    @GetMapping("/reset")
    @CrossOrigin
    public void reset()
    {
        undirectedGraph = new UndirectedGraph("file.txt");
        set = new HashSet<>();
        for(int i = 1; i < undirectedGraph.getGraph().length; i++) set.add(i);
    }

    @PostMapping("/addnode")
    @CrossOrigin
    private String addNode(@RequestParam("from") String from,@RequestParam("to") String to,
                         @RequestParam("costTo") String costTo, @RequestParam("costFrom") String costFrom ){
        try{
            Integer.valueOf(to);
            Integer.valueOf(from);
            Double.valueOf(costFrom);
            Double.valueOf(costTo);
        }
        catch(Exception e){
            return "something went wrong";
        }
        if (set.contains(Integer.valueOf(from))) return "The Node already exists";
        if(!set.contains(Integer.valueOf(to))) return "The Node has to already exist";
        undirectedGraph.addNode(Integer.valueOf(from), Integer.valueOf(to),
                Double.valueOf(costFrom), Double.valueOf(costTo));
        set.add(Integer.valueOf(from));
        set.add(Integer.valueOf(to));
        System.out.println(Integer.valueOf(from) + " " + Integer.valueOf(to));
        return "successful";
    }

    @PostMapping("/findallconnectedrouter")
    @CrossOrigin
    private String findallconnectedrouter(@RequestParam("routerNumber") String routerNumber){
        try{
            Integer.valueOf(routerNumber);
        }
        catch(Exception e){
            return "something went wrong";
        }
        if(!set.contains(Integer.valueOf(routerNumber))) return "No such router";
        String answer = undirectedGraph.printAllConnections(Integer.valueOf(routerNumber));
        return answer;
    }
}
