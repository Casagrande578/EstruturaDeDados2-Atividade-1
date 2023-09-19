package Node;

public abstract class Node {
    private float data;
    protected Node parent;
    protected Node left;
    protected Node right;

    public Node(){
        this(null);
    }
    public Node(Float data){
        this.data = data;
    }

    public Float getData() {
        return data;
    }

    public Node getLeft() {
        return left;
    }

    public Node getParent() {
        return parent;
    }

    public Node getRight() {
        return right;
    }

    public void setData(Float data) {
        this.data = data;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public boolean hasLeftChild(){
        if(this.left != null){
            return true;
        }
        return false;
    }
    public boolean hasRightChild(){
        if(this.right != null){
            return true;
        }
        return false;
    }

    public float visitar(){
        return this.getData();
    }

    @Override
    public String toString(){
        return (
                "\nData: " + this.data
                + "\nParent: " + this.parent
                + "\nLeft: " + this.left
                + "\nRight: " +this.right
                );
    }
}
