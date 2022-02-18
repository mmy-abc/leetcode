package com.mmy.hot100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution399 {
    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>();
        List<String> e = new ArrayList<>();
        e.add("a");
        e.add("b");
        equations.add(e);
        e = new ArrayList<>();
        e.add("b");
        e.add("c");
        equations.add(e);
        double[] values = {2.0, 3.0};
        List<List<String>> queries = new ArrayList<>();
        e = new ArrayList<>();
        e.add("a");
        e.add("c");
        queries.add(e);
        e = new ArrayList<>();
        e.add("a");
        e.add("c");
        queries.add(e);
        double[] res = new Solution399().calcEquation(equations, values, queries);
        for (double r : res) {
            System.out.println(r);
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int n = equations.size() * 2;
        UnionFind unionFind = new UnionFind(n);
        Map<String, Integer> map = new HashMap<>();
        int idx = 0;
        for (int i = 0; i < equations.size(); i++) {
            String start = equations.get(i).get(0);
            String end = equations.get(i).get(1);
            if (!map.containsKey(start)) {
                map.put(start, idx++);
            }
            if (!map.containsKey(end)) {
                map.put(end, idx++);
            }
            unionFind.union(map.get(start), map.get(end), values[i]);
        }

        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);
            if (!map.containsKey(start) || !map.containsKey(end)) {
                res[i] = -1.0;
            } else {
                unionFind.union(map.get(start), map.get(end), values[i]);
                res[i] = unionFind.dividedValue(map.get(start), map.get(end));
            }
        }
        return res;
    }

    private static class UnionFind {
        int[] parents;
        double[] weights;

        public UnionFind(int n) {
            parents = new int[n];
            weights = new double[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
                weights[i] = 1.0;
            }
        }

        public void union(int x, int y, double weight) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parents[rootX] = rootY;
                weights[rootX] = weight * weights[y] / weights[x];
            }
        }

        public int find(int x) {
            if (x != parents[x]) {
                int origin = parents[x];
                parents[x] = find(parents[x]);
                weights[x] *= weights[origin];
            }
            return parents[x];
        }

        public double dividedValue(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return weights[x] / weights[y];
            } else {
                return -1.0;
            }
        }
    }
}
