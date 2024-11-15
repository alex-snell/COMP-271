public class TreeNode implements Comparable<TreeNode> {

    /** The data payload of the node */
    private String word;
    /** Its left and right pointers */
    private TreeNode left;
    private TreeNode right;

    /**
     * Basic constructor creates a simple node with a payload and two null children.
     * 
     * @param word
     */
    public TreeNode(String word) {
        this.word = word;
        this.left = null;
        this.right = null;
    } // basic constructor

    
    public int countChildren(TreeNode node){
        int numChild = 0;
        if(node.getLeft() != null){
            numChild++;
        }
        if(node.getRight() != null){
            numChild++;
        }
        return numChild;
    }
    //getter returns string value at node
    public String getWord() {
        return word;
    }
    //getter returns left value
    public TreeNode getLeft(){
        return this.left;
    }
    //getter returns right value
    public TreeNode getRight(){
        return this.right;
    }
    //set method
    public void setLeft(TreeNode node){
        this.left = node;
    }
    //set method
    public void setRight(TreeNode node){
        this.right = node;
    }
    public boolean hasLeft(){
        return this.getLeft() != null;
    }
    public boolean hasRight(){
        return this.getRight() != null;
    }
    /** Implementation of Comparable using the nodes' content strings as basis for comparison. */
    public int compareTo(TreeNode other) {
        int result = 1;
        if (other != null)
            result = this.getWord().compareTo(other.getWord());
        return result;
    } // method compareTo
    public int countChildren(){
        int numChild = 0;
        if(this.hasLeft()){
            numChild++;
        }
        if(this.hasRight()){
            numChild++;
        }
        return numChild;
    }
}