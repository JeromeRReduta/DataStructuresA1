import java.util.*;

public class Trie implements StorageTree{
    private TrieNode root = new TrieNode();
    private static String symbols = "abcdefghijklmnopqrstuvwxyz'";
    public Trie() {}

    public void insert(String item) {
        insert(item, 0, root);
    }

private void insert(String word, int index, TrieNode current) {

    if (index == word.length()) {
        current.setValue(true);
        return;
    }

    // Recursive case: Not last letter
    String nextLetter = word.substring(index, index+1);

    /*if (index == word.length() - 1)
        System.out.println("Going to make node w/ " + nextLetter + " = true");
*/

    TrieNode next = current.getNext(nextLetter);

    if (next == null)
        next = current.linkRef(nextLetter);

    insert(word, index+1, next);
}

    // Checks if a string is represented in trie
    /* String is represented if:
        1) Its letters are in order in 1 branch
        2) Its last letter's value is true
     */
    public boolean find(Object word) {
        return find((String)word, 0, root);
    }

    private boolean find(String word, int index, TrieNode current) {


        // Base case 1: Can't find current letter
        if (current == null)
            return false;

        // Base case 2: Last letter we're looking for has value
        else if (index == word.length())
            return current.getValue();

        // Recursive case 1: Not last letter

        String nextLetter = word.substring(index, index+1);
        TrieNode next = current.getNext(nextLetter);
        return find(word, index + 1, next);

    }

    // Searches for a match for item
    // If it can't find one, tries to find 2 close alternatives
    public String[] getString(String item) {

        // Tracks current node, its parent, and its grandparent
        TrieNode grandparent = root, parent = root, current = root;
        int index = 0;


        // Represents "where we are in the word"
        String fragment = "";

        // Tracks which letter is next and the letter before it
        String letter = "";
        String prevLetter = "";

        // While we have not reached the end of the word...
        // ...or the branch of the trie, traverse the trie

        // Update values to reflect current node's values

        while (current != null && index < item.length()) {
            prevLetter = letter;
            letter = item.substring(index, index+1);

            fragment = fragment.concat(prevLetter);
            grandparent = parent;
            parent = current;
            current = current.getNext(letter);
            index++;
        }

        /* After passing through while loop, 3 possible cases:
            1: Branch ends early
            2: Found end of word, but end of word's value == false
            3: Found end of word and end of word's value == true
         */

        // Case 1 and case 2 - Return two close alternatives instead
        if (current == null || !current.getValue()) {
            return findTwoAlts(fragment, prevLetter, grandparent);
        }

        // Case 3 - return word, since we found it
        fragment = fragment.concat(letter);
        return new String[]{fragment};
    }

    // Finds two close alternate words
    private String[] findTwoAlts(String fragment, String parentLetter, TrieNode grandparent) {

        // Stores alts in arraylist so we can access size() in getAlt()
        ArrayList<String> alts = new ArrayList<>();

        TrieNode[] children = reOrderChildren(grandparent, parentLetter);

        // Checks for up to all 27^7 different suffixes to add to fragment
        // Adds any fragment + suffix to alts if they are a valid word
        for (TrieNode child : children)
            getAlt(alts, fragment, child, 0, 7);

        // Case: Found 2 alternatives
        if (alts.size() == 2)
            return new String[]{alts.get(0), alts.get(1)};

        // Rare default case: Can't find 2 alternatives
        // Probably nonsense, so return nonsense

        // Note: Function tends to return this when there is one...
        // ...distinct version of a word (e.g. "Czechoslovakia", and...
        // ... no "Czechoslovakia's", etc.)
        return new String[]{"nonsense", "nonsensical"};


    }

    // Reorders children nodes of TrieNode so that the parent node is first

    // Lets us prioritize searching for alternatives that are only one letter off,
    // ...ensuring that we get the closest alternatives first
    private TrieNode[] reOrderChildren(TrieNode grandparent, String parentLetter) {
        if (grandparent == null)
            return null;

        TrieNode[] children = new TrieNode[TrieNode.MAX_SIZE];

        // Puts our parent node first
        children[0] = grandparent.getNext(parentLetter);

        // Puts the other nodes in alphabetical order
        for (int i = 1; i < grandparent.indexOf(parentLetter); i++)
            children[i] = grandparent.getNext(i - 1);

        for (int i = grandparent.indexOf(parentLetter) + 1; i < TrieNode.MAX_SIZE; i++)
            children[i] = grandparent.getNext(i);

        return children;
    }

    // Recursively checks all subtrees branching from grandparentNode...
    // ...for (int limit) generations, and if we find a valid word we
    // ...add that to alts until alts has 2 alternate words
    private void getAlt(ArrayList<String> alts, String fragment, TrieNode current, int genNum, int limit) {

        // Base cases: Have 2 alternatives or current is null
        // Or gone (int limit) generations already
        if (alts.size() == 2 || current == null || genNum == limit)
            return;

        // Case: Found a valid alternate
        if (current.getValue() == true) {
            alts.add(fragment);
        }

        // Recursive case: < 2 alternatives && current exists &&...
        // ...haven't gone (int limit) generations yet
        for (int i = 0; i < TrieNode.MAX_SIZE; i++) {
            String newWord = fragment.concat(symbols.substring(i, i+1));
            TrieNode next = current.getNext(i);
            getAlt(alts, newWord, next, genNum + 1, limit);
        }



    }
}
