
public class BinarySearchTree {

    TreeNode root;
    int numberOfNodes;
    private String longest;
    private String shortest;
    /**
     * CONSTRUCTOR for BinarySearchTree
     */
    public BinarySearchTree() {
        this.root = null;
        this.numberOfNodes = 0;
        this.shortest = null;
        this.longest = null;
    }

    /**
     * @param String Non-recursive method to add a node to the BST with no
     * return statements FIXED
     */
    public void add(String value) {
        TreeNode newNode = new TreeNode(value);

        // Special case for an empty tree
        if (root == null) {
            root = newNode;
            this.shortest = newNode.getWord();
            this.longest = newNode.getWord();
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
            } else if (parent.getWord().compareTo(value) < 0) {
                parent.setRight(newNode);
            }
            if (newNode.getWord().length() > this.longest.length()) {
                this.longest = newNode.getWord();
            }
            if (newNode.getWord().length() < this.shortest.length()) {
                this.shortest = newNode.getWord();
            }
            this.numberOfNodes++;
        }
    }//method add
    /**
     * contains
     * @param target
     * @return found
     * while(cursor != null)
     *  if(cursor == target)
     *      found = true
     *  if(cursor.compare < 0 )
     *      cursor = left
     *  else
     *      cursor = right
     */
    public boolean contains(String target) {
        TreeNode cursor = this.root;
        boolean found = false;
        while (cursor != null && !found) {
            if (cursor.getWord().equals(target)) {
                found = true;
            }
            if (target.compareTo(cursor.getWord())< 0) {
                cursor = cursor.getLeft();
            } else {
                cursor = cursor.getRight();
            }
        }

        return found;
    }//method contains
    
    /**
     * remove method
     * @param target
     * @param cursor
     * @return cursor
     * 
     * while(cursor != null){
     *  if(cursor == target)
     *      found = true
     *  else if(cursor < 0)
     *      parent = cursor
     *      cursor = cursor.getLeft
     *  else
     *      parent = cursor
     *      cursor = cursor.getRight
     * if(cursor.children = 1)
     *  if(left)
     *      cursor = left
     *  if(right)
     *      cursor = right
     * else if(cursor.children == 2)
     *  if(left longer)
     *      cursor = left
     *      parent = cursor
     *  else(right longer)
     *      cursor = right
     *      parent = cursor
     * removed = cursor
     * cursor = null
     * else
     *  parent = cursor;
        removed = cursor;
        cursor = null;
     * return removed
     */
    public TreeNode remove(String target, TreeNode cursor) {
        TreeNode parent = cursor;
        boolean found = false;
        TreeNode removed;
        int child1 = 1;
        int child2 = 2;
        while(cursor != null && found){
            if (cursor.getWord().equals(target)) {
                found = true;
            }else if (target.compareTo(cursor.getWord())< 0) {
                parent = cursor;
                cursor = cursor.getLeft();
            } else {
                parent = cursor;
                cursor = cursor.getRight();
            }
        }
        if(cursor.countChildren() == child1){
            if(cursor.hasLeft()){
                cursor = cursor.getLeft();
            }else{
                cursor = cursor.getRight();
            }
            parent = cursor;
            removed = cursor;
            cursor = null;
        }else if(cursor.countChildren() == child2){
            if(cursor.getWord().compareTo(cursor.getLeft().getWord()) < 0) {
                cursor = cursor.getLeft();
                parent = cursor;
            }else if(cursor.getWord().compareTo(cursor.getRight().getWord()) > 0){
                cursor = cursor.getRight();
                parent = cursor;
            }
            removed = cursor;
            cursor = null;
        }else{
            parent = cursor;
            removed = cursor;
            cursor = null;

        }
        return removed;
    }//method remove

    /**
     * Descriptive text representation; it returns information about the contents of
     * the tree and not the actual contents. For the actual contents we can use the
     * in-order traversal of the tree.
     */
    public String toString() {
        // Local constants
        final String EMPTY = "*The tree is empty.*";
        final String NODES_FMT = "There are %d nodes in the tree.\n";
        final String ROOT_FMT = "The tree is rooted at \"%s\".\n";
        final String SHORTEST_FMT = "The shortest entry is \"%s\" with %d characters.\n";
        final String LONGEST_FMT = "The longest entry is \"%s\" with %d characters.";
        StringBuilder sb = new StringBuilder();
        if (this.root == null) {
            sb.append(EMPTY);
        } else {
            sb.append(String.format(NODES_FMT, this.numberOfNodes));
            sb.append(String.format(ROOT_FMT, this.root.getWord()));
            sb.append(String.format(SHORTEST_FMT, this.shortest, this.shortest.length()));
            sb.append(String.format(LONGEST_FMT, this.longest, this.longest.length()));
        }
        return sb.toString();
    } // method toString

    /**
     * helper method remove
     * @param target
     * @return removed true/false
     */
    public boolean remove(String target) {
        boolean removed = false;
        boolean found = contains(target);
        if (target != null && this.root != null && found) {
           this.remove(target, this.root);
           removed = true;
           this.numberOfNodes--;
        }
        return removed;
    } // helper method remove

     /**
     * In order traversal of a tree
     * 
     * @return a String[] with the contents of the tree as they appear
     */
    public void traverseInOrder(TreeNode node) {
        if (node != null) {
            traverseInOrder(node.getLeft());
            System.out.println(node.getWord());
            traverseInOrder(node.getRight());
        }
    } // method traverseInOrder

    /**
     * Helper method to start in-order traversal
     */
    public void traverseInOrder() {
        if (this.root != null) {
            this.traverseInOrder(this.root);
        }
    } // helper method traverseInOrder

   
    public static void main(String[] args) {
        BinarySearchTree demo = new BinarySearchTree();
        String[] words = {
                "now", "is", "the", "winter", "of", "our", "discontent",
                "made", "glorious", "by", "this", "son", "of", "york",
                "and", "all", "the", "clouds", "that", "lourd", "upon",
                "our", "house", "in", "the", "deep", "bosom", "of",
                "the", "ocean", "buried", "now", "are", "our", "brows",
                "bound", "with", "victorious", "wreaths", "our",
                "bruised", "arms", "hung", "up", "for", "monuments",
                "our", "stern", "alarums", "changed", "to",
                "merry", "meetings", "our", "dreadful", "marches",
                "to", "delightful", "measures"
        };
        for (String word : words) {
            demo.add(word);
        }
        TreeNode node = demo.root;
        //demo.traverseInOrder();
        System.out.println(demo.contains("Leo"));
        System.out.println(demo.contains("winter"));
        System.out.println(demo.toString());
        System.out.println(demo.remove("now"));
    }
}
