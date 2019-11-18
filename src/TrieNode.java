public class TrieNode {
    final static int MAX_SIZE = 27; // 26 alphabet letters + '
    private boolean value = false;
    private TrieNode[] refs = new TrieNode[MAX_SIZE];

    public TrieNode() {
    }

    /* Returns index of a char such that:
    a = 0
    b = 1
    z = 25
    ' = 26
     */

    public int indexOf(String c) throws Error {
        int charVal = (int)c.toUpperCase().charAt(0);
        if ((charVal < 65 || charVal > 90) && !(isSpecialChar(c)))
            throw new Error("'" + c + "' not an included symbol");

        else if (c.equals("'"))
            return 26;
        return charVal - (int)'A';

    }

    // Was going to use this func in case more special chars came up...
    // ...but none did
    private boolean isSpecialChar(String c) {
        return c.equals("'");
    }


    public boolean getValue() {return value;}
    public void setValue(boolean input) {value = input;}

    public TrieNode[] getRefs() {return refs;}

    // Given a letter, returns the node "for that letter"
    public TrieNode getNext(String str) {return refs[indexOf(str)];}

    // Given an int, returns node w/ that int
    public TrieNode getNext(int i) {return refs[i];}

    // Creates a new child TrieNode "for that letter"
    public TrieNode linkRef(String str) throws Error {
        refs[indexOf(str)] = new TrieNode();
        return refs[indexOf(str)];
    }


}
