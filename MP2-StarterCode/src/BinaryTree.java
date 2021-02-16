import java.io.Serializable;

/**
 * Class for a binary tree that stores type E objects
 * @param <E> data type to store
 */
public class BinaryTree<E> implements Serializable {
    //Data Field
    /** The root of the binary tree */
    protected BinaryNode<E> root;

    //Constructors

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(BinaryNode<E> root){
        this.root = root;
    }

    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree){
        root = new BinaryNode<E>(data);
        if (leftTree != null){
            root.left = leftTree.root;
        } else {
            root.left = null;
        }
        if (rightTree != null){
            root.right = rightTree.root;
        } else {
            root.right = null;
        }
    }

    public BinaryTree<E> getLeftSubtree(){
        if(root != null && root.left != null){
            return new BinaryTree<E>(root.left);
        } else {
            return null;
        }
    }

    public BinaryTree<E> getRightSubtree(){
        if(root != null && root.right != null){
            return new BinaryTree<E>(root.right);

        } else {
            return null;
        }
    }

    public E getData(){
        if(root != null){
            return root.data;
        } else {
            return null;
        }
    }

    public boolean isLeaf(){
        return (root.left == null && root.right == null);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);

        return sb.toString();
    }


    private void preOrderTraverse(BinaryNode<E> BinaryNode, int depth, StringBuilder sb){
        for(int i = 0; i < depth; i++){
            sb.append(" ");
        }
        if(BinaryNode == null){
            sb.append("null\n");
        } else {
            sb.append(BinaryNode.toString() + "\n");
            preOrderTraverse(BinaryNode.left, depth + 1, sb);
            preOrderTraverse(BinaryNode.right, depth+1, sb);
        }

    }

    /**
     * Class to encapsulate a tree node
     * @param <E> data type
     */
    protected class BinaryNode<E> implements Serializable {
        //Data Fields
        /** The information stored in this node. */
        protected E data;
        /** Reference to the left child */
        protected BinaryNode<E> left;
        /** Reference to the right child */
        protected BinaryNode<E> right;
        //Constructors

        /**
         * Construct a node with given data and no
         * children.
         * @param data the data to store in this node.
         */
        public BinaryNode(E data){
            this.data = data;
            this.left = null;
            this.right = null;

        }
        // Methods

        /**
         * Return a string representation of the node.
         * @return A string representation of the data fields
         */
        public String toString(){
            return data.toString();
        }

        protected BinaryNode<E> getLeftChild(){
            return left;
        }
        protected BinaryNode<E> getRightChild(){
            return right;
        }

    }
}
