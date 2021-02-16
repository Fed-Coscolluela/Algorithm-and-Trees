public class BinarySearchTreeWithRotate<E extends Comparable<E>> extends BinarySearchTree<E> {
    /**
     * Rotates tree to left from local root BinaryNode
     */
    protected BinaryNode<E> rotateLeft(BinaryNode<E> localRoot){
        BinaryNode<E> temp = localRoot.right;
        localRoot.right = temp.left;
        temp.left = localRoot;
        localRoot =temp;

        return localRoot;

    }

    /**
     * Rotates tree to right from local root BinaryNode
     */
    protected BinaryNode<E> rotateRight(BinaryNode<E> localRoot){
        BinaryNode<E> temp = localRoot.left;
        localRoot.left = temp.right;
        temp.right = localRoot;
        localRoot =temp;
        return localRoot;
    }
}
