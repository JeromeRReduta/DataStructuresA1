public class BSTNode<T> {
    private Comparable<T> data;
    private BSTNode left;
    private BSTNode right;

    public BSTNode(Comparable<T> input) {
        data = input;
    }

    public Comparable<T> getData() {return data;}
    public void setData(Comparable<T> input) {data = input;}

    public BSTNode<T> getLeft() {return left;}
    public void setLeft(BSTNode<T> input) {left = input;}

    public BSTNode<T> getRight() {return right;}
    public void setRight(BSTNode<T> input) {right = input;}

    public boolean greaterThan(Comparable<T> reference) {
        return getData().compareTo((T)reference) > 0;
    }

    public boolean greaterThan(String reference) {
        return data.toString().compareToIgnoreCase(reference.toString()) > 0;
    }

    public boolean lessThan(Comparable<T> reference) {
        return getData().compareTo((T)reference) < 0; }

    public boolean lessThan(String reference) {
        return data.toString().compareToIgnoreCase(reference.toString()) < 0;
    }

    public boolean equalTo(Comparable<T> reference) {
        return getData().compareTo((T)reference) == 0;
    }

    public boolean equalTo(String reference) {
        return data.toString().compareToIgnoreCase(reference.toString()) == 0;
    }

    public boolean isStringMatch(String reference) {
        String word = data.toString().toUpperCase();
        String refWord = reference.toString().toUpperCase();

        return word.equals(refWord);
    }
}
