package com.dsa.graph;

import java.util.ArrayList;
import java.util.List;

public class DisjointSetUnionByRank extends DisjointSet{
    List<Integer> rank = new ArrayList<>();

    public DisjointSetUnionByRank(int n) {
        super(n);
        for(int i = 0; i <= n; i++) {
            rank.add(0); // default rank is 0
        }
    }

    // combine two nodes in one component
    public void unionByRank(int u, int v) {
        int ultimateParentOfU = findUltimateParent(u);
        int ultimateParentOfV = findUltimateParent(v);

        // part of the same component
        if(ultimateParentOfU == ultimateParentOfV) return;

        // calculate ranks of ultimate parents
        int rankOfParentOfU = rank.get(ultimateParentOfU);
        int rankOfParentOfV = rank.get(ultimateParentOfV);

        // attach low rank to high one
        if(rankOfParentOfU > rankOfParentOfV) {
            parent.set(ultimateParentOfV, ultimateParentOfU);
        } else if(rankOfParentOfU < rankOfParentOfV) {
            parent.set(ultimateParentOfU, ultimateParentOfV);
        } else {
            // both ranks are equal
            /*
            we have pushed v to u  (v = 1-> 2, u = 3 -> 4)
            since ranks were same, after adding v to u rank of u will be increased by 1
            new u => 3 -> 4
                     |-> 1 -> 2
             here 3 -> 1 -> 2 has a rank of 2
             */
            parent.set(ultimateParentOfV, ultimateParentOfU);
            rank.set(ultimateParentOfU, rankOfParentOfU+1);
        }
    }
}
