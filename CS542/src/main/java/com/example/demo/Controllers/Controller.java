package com.example.demo.Controllers;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;



@RestController
public class Controller {

    @CrossOrigin
    @PostMapping("/file")
    public ArrayList getHello(@RequestParam("file") String file) {

        writeBase64File(file);
        UndirectedGraph undirectedGraph = new UndirectedGraph("file.txt");
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
    @GetMapping("/allPaths")
    public String getAllPaths(){
        UndirectedGraph undirectedGraph = new UndirectedGraph("file.txt");
        FindAllPath findAllPath = new FindAllPath(undirectedGraph.getGraph());
        //findAllPath.findAllPath(1, 5);
        //System.out.println(findAllPath.printPath());
        ArrayList<ArrayList<Integer>> paths = findAllPath.findAllPath(1, 5);
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
        return sb.toString();
    }

    @GetMapping("/shortestpathcost")
    @CrossOrigin
    public double getShortestPathCost(){

        UndirectedGraph undirectedGraph = new UndirectedGraph("file.txt");
        ShortestPath shortestPath = new ShortestPath(undirectedGraph.getGraph());
        shortestPath.findShortestPath(1, 5);
        return shortestPath.getShortestPath(5);
    }

    @GetMapping("/shortestPath")
    @CrossOrigin
    public String getShortestpath(){
        UndirectedGraph undirectedGraph = new UndirectedGraph("file.txt");
        ShortestPath shortestPath = new ShortestPath(undirectedGraph.getGraph());
        shortestPath.findShortestPath(1, 5);
        System.out.println(shortestPath.tracePath(5));

        return shortestPath.tracePath(5);
    }
}
