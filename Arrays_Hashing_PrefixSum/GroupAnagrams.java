import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {
    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
    }

    public static List<List<String>> groupAnagrams(String[] strs) {

        // // Brute/Better - Sorting + HashMap
        // // HashMap - ["aet" - ["eat", "tea", "ate"]]
        // //           ["ant" - ["tan", "nat"]       ]
        // //           ["abt" - ["bat"]              ]
        // // TC: O(n * k logk)  -> n is number of words, k is max word length (due to sorting)
        // // SC: O(n * k)       -> Memory needed to store all characters in the HashMap
        // //
        // HashMap<String, List<String>> map = new HashMap<>();
        // for (String i : strs) {
        //     char[] st = i.toCharArray();             // st = ['e', 'a', 't']
        //     Arrays.sort(st);                         // st = ['a', 'e', 't']
        //     String sortedStr = new String(st);       // sortedStr = "aet"
        // //
        //     if (!map.containsKey(sortedStr)) {
        //         map.put(sortedStr, new ArrayList<>());
        //     }
        //     map.get(sortedStr).add(i);               // .add() is a List method
        // }
        // return new ArrayList<List<String>>(map.values());



        // Optimal - Character count + HashMap (use this)
        // // HashMap - ["#1#0#0#0#1#0...#1" - ["eat", "tea", "ate"]]
        // //           ["#1#0#0#0...#1#1"   - ["tan", "nat"]       ]
        // //           ["#1#1#0#0...#0#1"   - ["bat"]              ]
        // TC: O(n * k) -> for each string, we build a char freq array
        // SC: O(n * k) -> space for char freq array for each string
        //
        HashMap<String, List<String>> map = new HashMap<>();
        for (String i : strs) {
            int[] cntArr = new int[26];
            for (char c : i.toCharArray()) {        // For each str in list, create char freq array
                cntArr[c - 'a']++;                 
            }
            StringBuilder sb = new StringBuilder(); // Build key for that str using char freq array
            for (int c : cntArr) {                  // take each num from cntArr and append in sb
                sb.append("#").append(c);
            }
            String key = sb.toString();             // Convert StringBuilder sb to String key
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(i);
        }
        return new ArrayList<>(map.values());



        // Micro-Optimized - Raw Char Array Snapshot + HashMap
        // // HashMap - ["\u0001\u0000...\u0001" - ["eat", "tea", "ate"]]
        // //           ["\u0001\u0000...\u0001" - ["tan", "nat"]       ]
        // //           ["\u0001\u0001...\u0000" - ["bat"]              ]
        // // TC: O(n * k) -> for each string, we build a char freq array
        // // SC: O(n * k) -> space for char freq array snapshot for each string
        // //
        // HashMap<String, List<String>> map = new HashMap<>();
        // for (String i : strs) {
        //     char[] cntArr = new char[26];
        //     for (char c : i.toCharArray()) {   // For each str in list, create char freq array
        //         cntArr[c - 'a']++;             // Stores counts directly in the char's numeric slot
        //     }
        //     String key = new String(cntArr);   // Takes a direct raw memory snapshot of the array as a String key
        //     if (!map.containsKey(key)) {
        //         map.put(key, new ArrayList<>());
        //     }
        //     map.get(key).add(i);               // .add() is a List method
        // }
        // return new ArrayList<>(map.values());
    }
}



// NOTES - 
// ------------------------------
// ## 🗣️ Step-by-Step Interview Script (What to Say)## 1. Introduce the Brute/Better Approach First

// "To solve Group Anagrams, our goal is to group words with identical character frequencies. A straightforward way to do this is by Sorting. For each word, we can convert it to a character array, sort it alphabetically, and use that sorted string as a unique key in a HashMap. This gives us a time complexity of $O(N \cdot K \log K)$ due to the sorting step, and a space complexity of $O(N \cdot K)$ to house the map data."

// ## 2. Propose the Optimal Frequency Approach

// "We can optimize this to $O(N \cdot K)$ time by counting character frequencies instead of sorting. The standard way to do this is using an int[26] array for each word and building a delimited string key like "#1#0#2..." with a StringBuilder. This works perfectly, but it adds object allocation overhead inside our main loop."

// ## 3. Deliver the Micro-Optimized Masterstroke

// "To achieve peak performance, we can implement a Raw Character Array Snapshot strategy. Instead of an int[] array, we use a char[26] array to track counts directly in their numeric character slots. Then, we instantiate our key instantly using new String(cntArr). This treats the underlying memory block as a direct raw snapshot. It eliminates the 26-iteration string builder loop entirely, keeping Java's garbage collector incredibly happy."

// ------------------------------
// ## 🗂️ Crucial Follow-Up Questions & Expert Answers## Q1: Why is a standard int[] array technically better/safer in production than this char[] trick?

// * Integer Overflow Safety: A Java char is an unsigned 16-bit type maxing out at 65,535. If a streaming text file has 65,538 copies of letter 'a', it overflows and wraps around to 2 ($65,538 \pmod{65,536}$). This breaks our groupings. An int[] safely scales up to 2.1 billion.
// * Visual Debugging: Printing an int[] key string reveals readable digits like "#1#0#2". Printing a char[] key outputs unprintable, invisible Unicode symbols like \u0001\u0000, making system logs impossible to read or debug.

// ## Q2: What are the architectural limitations of this char[26] setup?

// * Character Set Constraints: Hardcoding a size of 26 and subtracting 'a' strictly works for lowercase English letters. If production data includes uppercase characters, punctuation, spaces, or emojis, this code crashes with an ArrayIndexOutOfBoundsException.
// * The Production Fix: In a real-world system, I would use a HashMap<Character, Integer> or a dynamic sorting pattern to cleanly support any global Unicode character set.

// ------------------------------
// ## ⚡ The Character Array Mechanic (Short & Punchy Explanation)
// If the interviewer asks: "Explain exactly how cntArr[c - 'a']++ works when c = 'b'."
// Give them this brief, high-utility technical summary:

//    1. ASCII Math: 'b' - 'a' subtracts character ASCII values (98 - 97), giving index 1.
//    2. Value Increment: cntArr[1]++ increments the value in that box from its default 0 to 1.
//    3. Under the Hood Storage: Because cntArr is a character array, that raw numeric value 1 is stored directly as the literal, unprintable Unicode character \u0001.
