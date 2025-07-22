package com.dsa.graph;

import java.util.ArrayList;
import java.util.List;

public class DisjointSetUnionBySize extends DisjointSet{
    List<Integer> sizes = new ArrayList<>();

    public DisjointSetUnionBySize(int n) {
        super(n);
        // at the start each node is independent, hence their size is 1
        for(int i = 0; i <= n; i++) {
            sizes.add(1);
        }
    }

    // combine two nodes in one component
    public void unionBySize(int u, int v) {
        int ultimateParentOfU = findUltimateParent(u);
        int ultimateParentOfV = findUltimateParent(v);

        // part of the same component
        if(ultimateParentOfU == ultimateParentOfV) return;

        // calculate ranks of ultimate parents
        int sizeOfParentOfU = sizes.get(ultimateParentOfU);
        int sizeOfParentOfV = sizes.get(ultimateParentOfV);

        /*
            we will attach smaller sized component to the larger one
            and same size can go anywhere, we will need to condition u > v and u <= v
            JOINING of a node
            assume v is getting joined to u
            ie. p[v] = u ==> parent of v is now u
            since v is attached to u now, size of u = size[u] + size[v]
         */

        if(sizeOfParentOfU < sizeOfParentOfV) {
            parent.set(ultimateParentOfU, ultimateParentOfV);
            sizes.set(ultimateParentOfV, sizeOfParentOfU+sizeOfParentOfV);
        } else {
            parent.set(ultimateParentOfV, ultimateParentOfU); // parent of v is u
            sizes.set(ultimateParentOfU, sizeOfParentOfV+sizeOfParentOfU);
        }
    }
}
