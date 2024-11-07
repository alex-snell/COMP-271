

public class BinarySearchTree {
    TreeNode root;
    /**
     * CONSTRUCTOR for BinarySearchTree
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * @param String
     *  Non-recursive method to add a node to the BST with 
     * no return statements 
     * FIXED */
    public void add(String value) {
        TreeNode newNode = new TreeNode(value);

        // Special case for an empty tree
        if (root == null) {
            root = newNode;
        } else {
            TreeNode current = root;
            TreeNode parent = null;

            // Traverse the tree to find the correct position
            while (current != null) {
                parent = current;
                if (current.getWord().compareTo(value) > 0) {
                    current = current.getLeft();
                } else if (current.getWord().compareTo(value) < 0) {
                    current = current.getRight();
                } else {
                    // If the value already exists, do nothing (BSTs typically don't allow duplicates)
                    current = null;
                }
            }

            // Attach the new node to the appropriate parent node
            if (parent.getWord().compareTo(value) > 0) {
                parent.setLeft(newNode);
            } else if(parent.getWord().compareTo(value) < 0){
                parent.setRight(newNode);
            }
        }
    }
    public static void main(String[] args) {
        
    }
}