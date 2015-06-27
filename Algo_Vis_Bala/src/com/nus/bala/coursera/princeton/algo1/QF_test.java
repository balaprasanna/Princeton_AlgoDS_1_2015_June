package com.nus.bala.coursera.princeton.algo1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author prasannav
 */

import com.nus.bala.coursera.princeton.algo1.*;
import java.util.Random;

public class QF_test {
    
    public static void main(String[] args) {
        QF quickFind = new QF(10);
      
//         performUnion(quickFind, 4, 3);
//        performUnion(quickFind, 3, 8);
//        performUnion(quickFind, 6, 5);
//        performUnion(quickFind, 9, 4);
//        performUnion(quickFind, 2, 1);
//        performUnion(quickFind, 5, 0);
//        performUnion(quickFind, 7, 2);
//        performUnion(quickFind, 6, 1);
//        performUnion(quickFind, 7, 3);
      performUnion(quickFind, 8, 4);
        performUnion(quickFind, 0, 7);
        performUnion(quickFind,3, 7);
        performUnion(quickFind, 6, 3);
        performUnion(quickFind, 1, 0);
        performUnion(quickFind, 5, 9);
        
    }
     public static void performUnion(QF quickFind, int x, int y){
       if(!quickFind.connected(x, y))
        {
            quickFind.union(x, y);
            quickFind.print();
        }
    }
    
}
