import java.io.*;
import java.util.*;


public class CS245A1 {
    /*

    Reorders alphabetically sorted string such that:

    Have an empty list and list of words
    Insert listOfWords[midLeft - length] to listOfWords[midLeft]
    into empty list in reverse order

    Insert listOfWords[midRight] to listOfWords[midRight + length]
    into empty list in reverse order

    repeat starting from midLeft - 2*length to midLeft - length + 1
    and midRight + length + 1 to midRight + 2* length

    And then insert the rest
    */

    public static String[] reorderWords(Scanner scan) {
        ArrayList<String> list = new ArrayList<>();
        while (scan.hasNext())
            list.add(scan.next());

        String[] words = new String[list.size()];

        int wordsIndex = 0;
        int midLeft = list.size() / 2;
        int midRight = midLeft + 1;
        int branchLength = list.size() / 25;

        while (midLeft >= branchLength && midRight < list.size() - branchLength) {

            for (int i = midLeft - branchLength; i <= midLeft; i++)
                words[wordsIndex++] = list.get(i);

            midLeft -= (branchLength + 1);

            for (int i = midRight + branchLength; i >= midRight; i--)
                words[wordsIndex++] = list.get(i);

            midRight += branchLength + 1;
        }

        for (int i = 0; i <= midLeft; i++) {
            words[wordsIndex++] = list.get(i);
        }

        for (int i = list.size() - 1; i >= midRight; i--)
            words[wordsIndex++] = list.get(i);

        return words;

    }

    // Creates tree based on properties file
    public static StorageTree initTree(File refFile, File propFile) throws FileNotFoundException {
        // PROBLEM: BST has stack overflow - try coding iterative vers later
        Scanner refScan = new Scanner(refFile);
        String[] words = reorderWords(refScan);

        Scanner propScan = new Scanner(propFile);

        String properties = propScan.next();

        StorageTree tree;

        if (properties.equals("storage=tree"))
            tree = new BST();


        else
            tree = new Trie();

        for (String word : words)
            tree.insert(word);

        return tree;

    }

    public static void main(String[] args) throws Exception  {
        // Paths to: reference file, properties file, input file, output file
        String refPath = "src/english.0";
        String propPath = "src/a1properties.txt";

        String inPath = "src/" + args[0];
        String outPath = "src/" + args[1];


        StorageTree tree = initTree(new File(refPath), new File(propPath));

        StorageTree.process(tree, new File(inPath), outPath);

    }

}

