// Leetcode 217 - https://leetcode.com/problems/contains-duplicate/

import java.util.*;
public class ContainsDuplicate {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(containsDuplicate(nums));
    }

    public static boolean containsDuplicate(int[] nums) {
        
        // // Brute force
        // // TC: O(1) best     O(n²) avg/worst
        // // SC: O(1) 
        //
        // int len = nums.length;
        // for (int i = 0; i < len; i++) {
        //     for (int j = i+1; j < len; j++) {
        //         if (nums[j] == nums[i]) {
        //             return true;
        //         }
        //     }
        // }
        // return false;
    
    
        // // Better by sorting and comparing with prev elem
        // // TC: O(nlogn) best/avg   O(nlogn) or O(n²) worst [for Quicksort worst-case]
        // // SC: O(logn) [due to internal recursion stack frame of Arrays.sort] or O(1) if in place sorting
        //
        // int len = nums.length;
        // Arrays.sort(nums);                  // takes TC: O(nlogn) on avg
        // for (int i = 1; i < len; i++) {     // TC: O(1) best, O(n) worst
        //     if (nums[i] == nums[i-1]) {
        //         return true;
        //     }
        // }
        // return false;
    
    
        // HashSet - contains unique elements, optimal, use this
        // TC: O(1) best    O(n) avg/worst
        // SC: O(n) for HashSet, trading space of speed
        //
        HashSet<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (set.contains(n)) {
                return true;
            }
            set.add(n);
        }
        return false;
    }
    
}
