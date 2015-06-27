/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nus.bala.coursera.princeton.algo1;

import java.util.Random;

/**
 *
 * @author prasannav
 */
public class QU_test {
    
    public static void main(String[] args) {
    
    
    QU quickUnion = new QU(10);
      
        Random r = new Random();
//        int x = r.nextInt(9);
//        int y = r.nextInt(9);
        
        performUnion(quickUnion, 4, 3);
        performUnion(quickUnion, 3, 8);
        performUnion(quickUnion, 6, 5);
        performUnion(quickUnion, 9, 4);
        performUnion(quickUnion, 2, 1);
        performUnion(quickUnion, 5, 0);
        performUnion(quickUnion, 7, 2);
        performUnion(quickUnion, 6, 1);
        performUnion(quickUnion, 7, 3);

    } 
    public static void performUnion(QU quickUnion, int x, int y){
        if(!quickUnion.connected(x, y))
        {
            quickUnion.union(x, y);
            quickUnion.print();
        }
    }
}


/*
<<<<< EXPECTED OUTPU >>>>>
0123356789
0128356789
0128355789
0128355788
0118355788
0118305788
0118305188
1118305188
1818305188

*/