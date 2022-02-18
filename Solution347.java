package com.mmy.hot100;

import java.util.*;

public class Solution347 {
    public static void main(String[] args) {

    }

    static class Node {
        int key;
        int val;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Node> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            list.add(new Node(entry.getValue(), entry.getKey()));
        }
        list.sort((Node a, Node b) -> {
            return b.key - a.key;
        });
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = list.get(i).val;
        }
        return res;
    }
}
