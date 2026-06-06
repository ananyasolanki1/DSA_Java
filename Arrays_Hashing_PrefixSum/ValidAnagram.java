// Leetcode 242 - https://leetcode.com/problems/valid-anagram/

public class ValidAnagram {
    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaran";
        System.out.println(isAnagram(s, t));
    }

    public static boolean isAnagram(String s, String t) {

        // // Sorting
        // // TC: O(nlogn)
        // // SC: O(n)     or O(1) if in-place sorting
        // //
        // if (s.length() != t.length()) return false;
        //
        // char[] sChars = s.toCharArray();
        // char[] tChars = t.toCharArray();
        //
        // Arrays.sort(sChars);
        // Arrays.sort(tChars);
        //
        // return (Arrays.equals(sChars, tChars));



        // // HashMap - Better (if unicode chars in strings)
        // // TC: O(n)
        // // SC: O(k), k = unique chars
        // if (s.length() != t.length()) return false;
        //
        // HashMap<Character, Integer> map = new HashMap<>();
        //
        // // Count letters in the first string
        // for (int i = 0; i < s.length(); i++) {
        //     char ch = s.charAt(i);
        //     map.put(ch, map.getOrDefault(ch, 0) + 1);
        // }
        //
        // // Subtract letters using the second string
        // for (int i = 0; i < t.length(); i++) {
        //     char ch = t.charAt(i);
        //     if (!map.containsKey(ch)) return false;   // if letter not in map, not anagram
        //     map.put(ch, map.get(ch) - 1);             // subtract the count
        //     if (map.get(ch) == 0) {                   // remove key from map, if its val is 0
        //         map.remove(ch);
        //     }
        // }
        //
        // // If map is empty, all counts matched perfectly. it is anagram
        // return map.isEmpty();



        // Arrays - Best when only lowercase are there in strings
        // Optimal in general, use this
        // TC: O(n)
        // SC: O(26) ~ O(1)
        if (s.length() != t.length()) return false;
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
            counts[t.charAt(i) - 'a']--;
        }
        for (int c : counts) {
            if (c != 0) return false;
        }
        return true;
    }

    // The aim of s.charAt(i) - 'a' is to map every lowercase letter perfectly to a zero-based array index from 0 to 25.
    // Under the hood, Java treats characters as ASCII numbers, where 'a' is 97, 'b' is 98, and so on. By subtracting 'a' from the current character, we calculate its relative distance from the start of the alphabet.
    // For example, 'c' - 'a' translates to 99 - 97 = 2, steering us directly to index 2. This lets us find the exact counter slot instantly using basic math, keeping the space complexity locked at a tiny, constant O(1)
}
