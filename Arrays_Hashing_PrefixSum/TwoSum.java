// Leetcode 1 - https://leetcode.com/problems/two-sum/

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {2, 5, 3, -1, 7};
        int target = 4;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }

    public static int[] twoSum(int[] nums, int target) {
        
        // // Brute force - nested for loops
        // // TC: O(n²)
        // // SC: O(1) - no extra space used which grows with input
        // for (int i = 0; i < nums.length; i++) {
        //     for (int j = i + 1; j < nums.length; j++) {
        //         if (nums[i] + nums[j] == target) {
        //             return new int[] {i, j};
        //         }
        //     }
        // }
        // return new int[] {};


        // Optimal - HashMap, use this
        // TC: O(n) - Traverse the array only once
        // SC: O(n) - by HashMap
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int rem = target - nums[i];
            if (map.containsKey(rem)) {
                return new int[] {map.get(rem), i};    // Ascending order of indices
            }
            map.put(nums[i], i);
        }
        return new int[] {};


        // Sorting and then two pointers approach won't be used here as it will be O(nlogn) and we have to return indices, which will be complex. Use this in Leetcode 167
    }
}
