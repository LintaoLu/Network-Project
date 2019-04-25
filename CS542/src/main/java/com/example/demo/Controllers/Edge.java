package com.example.demo.Controllers;

class Edge implements Comparable<Edge>
{
    private int from, to;
    private double distance;
    public Edge(int from, int to, double weight)
    {
        this.from = from;
        this.to = to;
        this.distance = weight;
    }

    public int either(int side)
    {
        if(side == from) return to;
        return from;
    }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("from: " + from + " to: " + to);
        sb.append(" distance: " + distance);
        return sb.toString();
    }

    public double getDistance() { return distance; }

    public void setDistance(double distance) { this.distance = distance; }

    @Override
    public int compareTo(Edge e)
    {
        double answer = this.getDistance() - e.getDistance();
        if(answer > 0) return 1;
        else if(answer < 0) return -1;
        else return 0;
    }
}