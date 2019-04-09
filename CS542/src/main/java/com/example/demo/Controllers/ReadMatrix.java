package com.example.demo.Controllers;

import java.io.*;

public class ReadMatrix
{
    private double[][] matrix;
    private int size = 0;

    public ReadMatrix(String filename)
    {
        try { readFile(filename); }
        catch (IOException e) { e.printStackTrace(); }
    }

    public void readFile(String filename) throws IOException
    {
        //InputStream stream = ClassLoader.getSystemResourceAsStream(filename);
        File file = new File(filename);
        BufferedReader buffer = new BufferedReader(new FileReader(file));

        String line;
        int row = 0;

        line = buffer.readLine();
        String[] vals = line.trim().split("\\s+");
        size = vals.length;
        matrix = new double[size][size];

        while ((line = buffer.readLine()) != null)
        {
            vals = line.trim().split("\\s+");
            if (matrix == null)
            {
                size = vals.length;
                matrix = new double[size][size];
            }

            for (int col = 1; col < size+1; col++)
            {
                double number = Double.parseDouble(vals[col]);
                if(number < 0) number = Double.POSITIVE_INFINITY;
                matrix[row][col-1] = number;
            }

            row++;
        }
    }

    public void printTable()
    {
        int count = 0;
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                if(count < size - 1)
                {
                    System.out.print(String.format( "%.2f", matrix[i][j]) + "\t");
                    count++;
                }
                else
                    {
                        System.out.println(String.format( "%.2f", matrix[i][j]));
                        count = 0;
                    }
            }
        }
    }

    public double[][] getMatrix() { return matrix; }

    public static void main(String[] args)
    {
        ReadMatrix square = new ReadMatrix("/Users/oje/Desktop/square.txt");
        square.printTable();
    }
}
