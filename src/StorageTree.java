import java.nio.file.*;
import java.util.*;
import java.io.*;

// Tree that stores Strings
public interface StorageTree {

    // Insert String into tree
    public void insert(String item);

    // Return if we can find a given String in the tree
    public boolean find(Object item);

    // If a given String is a word in the tree, return it
    // Otherwise, return two alternatives
    public String[] getString(String item);

    // Given an input and output file, check all the words in...
    // ...the file against the words in the tree

    // If not in the tree, word is considered a misspelling, and...
    // ...the func replaces it with two alternate words
    public static void process(StorageTree tree, File inFile, String outPath) throws FileNotFoundException, IOException, Exception{
        Scanner inScan = new Scanner(inFile);
        Scanner outScan = new Scanner(new File(outPath));

        // Unrelated words are in own array, while alternate spellings...
        // ...are in same array
        ArrayList<String[]> words = new ArrayList<>();

        while (inScan.hasNext()) {
            String[] arr = tree.getString(inScan.next());
            words.add(arr);
        }

        /* Transcribes words from input to outfile file such that:
            If word is in the tree, it is copied exactly
            Else, considered a misspelling, and two alternates are written...
            ...on the line where the misspelled word would be
         */
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(outPath, false));
            for (String[] matches : words) {
                for (String word : matches)
                    out.write(word + "\t");
                out.write("\n");
            }
            out.close();
        }

        catch (IOException e) {
            throw new IOException("Couldn't find file!");
        }

        catch (Exception e) {
            throw new Exception("Something happened!");
        }


    }
}

