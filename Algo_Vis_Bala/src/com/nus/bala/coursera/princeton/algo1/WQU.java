/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nus.bala.coursera.princeton.algo1;

/**
 *
 * @author prasannav
 */
public class WQU {
    // variable, property declaration
    int array[];
    int tSizeArray[];
    
    
    //gettters
    public int[] getArray() {
        return array;
    }
   
    // constructor
    public WQU(int size)
    {
        array = new int[size];
        tSizeArray = new int[size];
        for (int i = 0; i < array.length; i++) {array[i] = i;}
        for (int i = 0; i < array.length; i++) {tSizeArray[i] = 1;}

    }
    
    //operation methods
    public boolean connected (int x, int y){
        return root(x)==root(y);
    }
    
    public int root(int x){
        while (array[x] != x){
            // Path compression
            array[x] = array[array[x]];
            x = array[x];
        }
        return x;
    }
    
    public void union(int p, int q){
        int leftRoot = root(p);
        int rightRoot = root(q);
      
      // Take root of the first iteam, then make it as child of second item
      if(tSizeArray[leftRoot] < tSizeArray[rightRoot]){
          array[leftRoot] = rightRoot;
          tSizeArray[rightRoot] += tSizeArray[leftRoot];
      }else
      {
          array[rightRoot] = leftRoot;
          tSizeArray[leftRoot] += tSizeArray[rightRoot];
      }
    }
    
    // util methods
    public void print(){
        System.out.println("");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
    }
    
    public static void findRoots(int[] array){
        for (int i = 0; i < array.length; i++) {
        int x=i;
        while(x != array[i])
        {
            x=array[i];
            System.out.println("x is"+x);
        }
        System.out.println("Root of i "+i+" is "+x);
        }
    }
    
}
