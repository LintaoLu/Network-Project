package com.example.demo.Controllers;

public class IndexMinPQ
{
    private double[] valueArray;
    private int[] keyArray;
    private int index = 1;
    private int size = 0;

    public IndexMinPQ(int length)
    {
        valueArray = new double[length];
        keyArray = new int[length];
    }

    public void add(int key, double value)
    {
        if(index >= keyArray.length) enlargeArray();
        keyArray[index] = key;
        valueArray[index] = value;
        swim(index);
        index++;
        size++;
    }

    public int popTop()
    {
        int top = keyArray[1];
        exchange(1, index-1);
        index--;
        sink(1, index);
        size--;
        return top;
    }

    public boolean isEmpty() { return size == 0; }

    private void swim(int index)
    {
        while (index/2 > 0)
        {
            if (valueArray[index] < valueArray[index / 2])
            {
                exchange(index, index/2);
                index = index / 2;
            }
            else break;
        }
    }

    private void sink(int index, int length)
    {
        if (index > length - 1) return;
        while (index * 2 < length - 1)
        {
            int smallest = index;
            int left_child = index * 2;
            int right_child = index * 2 + 1;
            if (left_child < length &&
                    valueArray[left_child] < valueArray[smallest])
                smallest = left_child;
            if (right_child < length &&
                    valueArray[right_child] < valueArray[smallest])
                smallest = right_child;

            if (smallest != index)
            {
                exchange(smallest, index);
                index = smallest;
            }
            else break;
        }
    }

    private void exchange(int i, int j)
    {
        double temp1 = valueArray[i];
        valueArray[i] = valueArray[j];
        valueArray[j] = temp1;
        int temp2 = keyArray[i];
        keyArray[i] = keyArray[j];
        keyArray[j] = temp2;
    }

    //This method is a lazy solution to deal with lazy
    //shortest path implementation...
    private void enlargeArray()
    {
        int length = keyArray.length * 2;
        if(length > 1000) return;
        int[] newKeyArray = new int[length];
        double[] newValueArray = new double[length];
        for(int i = 0; i < keyArray.length; i++)
        {
            newKeyArray[i] = keyArray[i];
            newValueArray[i] = valueArray[i];
        }
        keyArray = newKeyArray;
        valueArray = newValueArray;
    }

    public static void main(String[] args)
    {
        IndexMinPQ indexMinPQ = new IndexMinPQ(4);
        indexMinPQ.add(1, 9);
        indexMinPQ.add(5, 5);
        indexMinPQ.add(4, 3);
        for(int i = 0; i < 4; i++)
        {
            System.out.println(indexMinPQ.keyArray[i] + " " + indexMinPQ.valueArray[i]);
        }
    }
}
