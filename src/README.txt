Jerome Reduta
Assignment 1
Completed at 9:50 PM, 17 Nov. 2019

Implemented:
    BST
    BSTNode
    Trie
    TrieNode
    StorageTree
    a1properties.txt
    Files for:
        Jazzy English Dictionary (english.0)
        input
        output

Runtime Analysis:

    Many funcs, but only three matter: insert(), find(),
        getString()


    Insert():
        BST.insert() = O(s), where s = size of branch
            Because s is at worst n and usually log(n),
            BST.insert() has runtime O(n) and average runtime of
            O(log(n)).

        Trie.insert() = O(m), where m = length of longest word

        Trie.insert() is faster than BST.insert() when length of
            longest inputted word is less than the number of elements
            being put in tree

        Since this is usually the case, Trie.insert() is usually
            faster than BST.insert()

    Find():
        Note: boolean find() is not used itself, but the code used
            for traversing each tree in find() is present
            in the getString() functions of both trees

        BST.find() = O(n) worst case runtime, for reasons above
        Trie.find() = O(m) worst case runtime, for reasons above

        When length of longest inputted word < number of inputted
            elements (usually the case), Trie.find() is faster


    getString():
        BST.getString() = O(n)
            Worst case: In tree w/ n elem, starting from one root
                node with one right child node, and all
                n-2 descendants from there are left child nodes

            O(n-x) to traverse tree + O(x) to find alternative
            Total = O(max (n-x, x)) = O(n)


        Trie.getString() = O(m)
            Worst case: Trie has depth m, and every interior
                node has 27 TrieNode children (no null), but
                no words are valid until you reach a leaf node
                at the bottom

            This means each word has length m

            Takes O(m) to traverse to end of tree

            To find alt: Need to check s**g different branches max,
                where s = # of symbols in alphabet, and
                g = # of generations we want to look for

                s = constant, and I've hard-coded g to 7
                so g is a constant, too

            So takes O(1) worst case time to find Alts
                I hope this is wrong because it sounds immoral

                If s = 26 and g = 5, need to find 26^5 different
                    solutions

                I don't think this should be O(1)

            Worst-case runtime: O(m)

            Trie is again faster usually, because m usually < n

    For all three functions, trie is faster than BST when
        length of longest word < number of elements to be
        put in tree

    Because length of longest word very often < number of
        elements to be put in tree, TRIE IS VERY OFTEN
        FASTER THAN BST





















Extra Credit:
    Made good spell check for BST:
            Made spell-check for BST based on finding in-order
                successor, which theoretically should give the
                closest, if not a very close alternate word

            Made spell-check for Trie as well that produces
                good alternates, but not nearly as consistent as
                BST


Notes:
    Weakness of findTwoAlts() for Trie:
        Only assumes that the last two words may be wrong

        Only returns alternates if it can find two alternates.
            This means that if there is one distinct word in
            the dictionary, and a similar but misspelled word
            is checked against it, it will return nonsense as
            it can only find one alternate to return

References:
    https://www.baeldung.com/java-write-to-file - for writing
        to file

    https://medium.com/basecs/trying-to-understand-tries-3ec6bede0014 -
        To understand what the heck a trie was

    Professor David Guy Brizan - Also for understanding a trie,
        and for good advice in general

    My brain - which at this point is hurting