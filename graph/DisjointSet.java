package com.dsa.graph;

import java.util.ArrayList;
import java.util.List;

public class DisjointSet {
    List<Integer> parent = new ArrayList<>();

    /*
        constructor of disjoint set of size n
        we are creating lists of n+1 size
            i from 0 to n inclusive
           a. we will go from 0 to n-1 for 0 based indexing
           b. 1 to n for 1 based indexing
     */
    public DisjointSet(int n) {
        for(int i = 0; i <= n; i++) {
            parent.add(i); // each node will be it's own parent at the start
        }
    }

            /*
            Find ultimate parent
            eg. 1 -> 2 -> 3 here p[1] = 1, p[2] = 1 and p[3] = 2
            ultimate parent of 3 is p[3] = 2 --> p[2] = 2 --> p[1] = 1
            we will stop once p[i] = i --> which is the ultimate parent
         */
    public int findUltimateParent(int node) {
        // node it self is the ultimate parent
        if(parent.get(node) == node) {
            return node;
        }
        int ultimateParent = findUltimateParent(parent.get(node));
        // path compression, so that next time we get ultimate parent in constant time
        parent.set(node, ultimateParent);
        return ultimateParent;
    }



    // if the nodes have same ultimate parent then they belong to same component
    public boolean isSameComponent(int u, int v) {
        int ultimateParentOfU = parent.get(u);
        int ultimateParentOfV = parent.get(v);
//        System.out.println(u + ":" + ultimateParentOfU + " " + v + ":" + ultimateParentOfV);
        return ultimateParentOfU == ultimateParentOfV;
    }
}
