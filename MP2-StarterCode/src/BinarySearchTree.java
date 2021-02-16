import java.util.ArrayList;

public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> implements SearchTree<E> {

    protected boolean addReturn;
    protected E deleteReturn;

    public boolean insert(E item) {
        root = insertSupport(root, item);
        return addReturn;
    }

    private BinaryNode<E> insertSupport(BinaryNode<E> localRoot, E item){
        if (localRoot == null){
            addReturn = true;
            return new BinaryNode<E>(item);
        } else if(item.compareTo(localRoot.data) == 0) {
            addReturn = false;
        } else if(item.compareTo(localRoot.data) < 0) {
            localRoot.left = insertSupport(localRoot.left, item);
        } else {
            localRoot.right = insertSupport(localRoot.right, item);
        }
        return localRoot;
    }

    public boolean exists(E target) {
        return search(target) != null;
    }

    public E search(E target) {
        return searchSupport(root, target);
    }

    private E searchSupport(BinaryNode<E> localRoot, E target) {
        if (localRoot == null) {
            return null;
        }
        //
        int comparisonResults = target.compareTo(localRoot.data);
        if (comparisonResults == 0)
            return localRoot.data;
        else if (comparisonResults < 0)
            return searchSupport(localRoot.left, target);
        else
            return searchSupport(localRoot.right, target);

    }

    public E delete(E target) {
        root = deleteSupport(root, target);
        return deleteReturn;

    }

    private BinaryNode<E> deleteSupport(BinaryNode<E> localRoot, E target){
        if(localRoot == null){
            deleteReturn = null;
        } else {
            int comparisonResults = target.compareTo(localRoot.data);
            if (comparisonResults == 0) {
                deleteReturn = localRoot.data;
                if (localRoot.left == null && localRoot.right == null) {
                    return null;
                } else if (localRoot.left == null){
                    return localRoot.right;
                } else if (localRoot.right == null){
                    return localRoot.left;
                } else {
                    if (localRoot.left.right == null) {
                        localRoot.left.right = localRoot.right;
                        return localRoot.left;
                    }

                    localRoot.data = rightMostBinaryNodeDataDelete(localRoot, localRoot.left);

                }
            }
            else if(comparisonResults < 0){
                localRoot.left = deleteSupport(localRoot.left, target);
            } else {
                localRoot.right = deleteSupport(localRoot.right, target);
            }
        }
        return localRoot;
    }

    protected E rightMostBinaryNodeDataDelete(BinaryNode<E> root, BinaryNode<E> parentRoot) {
        if (root.right == null){
            root.right = root.left;
            return root.data;
        } else {
            return rightMostBinaryNodeDataDelete(root.right, root);
        }
    }

    public boolean remove(E target){
        return (delete(target) != null);
    }

    public String preorder(){
        StringBuilder preorderTraversal = new StringBuilder();
        preorderSupport(root, preorderTraversal);
        return preorderTraversal.toString();
    }

    private void preorderSupport(BinaryNode<E> root, StringBuilder preorder){
        if (root != null) {
            preorder.append(root + " ");
            preorderSupport(root.getLeftChild(), preorder);
            preorderSupport(root.getRightChild(), preorder);
        }
    }

    public String inorder() {
        StringBuilder inorderTraversal = new StringBuilder();
        inorderSupport(root, inorderTraversal);
        return inorderTraversal.toString();
    } // end inorder

    public void inorderSupport(BinaryNode<E> root, StringBuilder inorder) {
        if(root != null) {
            inorderSupport(root.getLeftChild(), inorder);
            inorder.append(root + " ");
            inorderSupport(root.getRightChild(), inorder);
        }
    }
    /**
     * Returns a postorder representation of the binary search tree
     *
     * @return postorder string of the binary search tree
     */
    //Complete this method
    public String postorder() {
        StringBuilder postorderTraversal = new StringBuilder();
        postorderSupport(root, postorderTraversal);
        return postorderTraversal.toString();
        //return "";
    } // end postorder

    public void postorderSupport(BinaryNode<E> root, StringBuilder postorder) {
        if(root != null) {
            postorderSupport(root.getLeftChild(), postorder);
            postorderSupport(root.getRightChild(), postorder);
            postorder.append(root + " ");
        }

    }

    public String levelorder(){
        StringBuilder levelorderTraversal = new StringBuilder();
        String levelorder;
        levelorder = levelorderSupport(root, levelorderTraversal);
        return levelorder;
    }

    public String levelorderSupport(BinaryNode<E> root, StringBuilder levelorder){
    	int height = height(root);
    	int i;
    	
    	for (i = 1; i <= height; i++) {
    			printGivenLevel(root, i, levelorder);
    		}
        return levelorder.toString();
    }
    
    void printGivenLevel(BinaryNode<E> root, int level, StringBuilder levelorder) {
    	if (root == null) {
    		return;
    	}
    	if (level == 1) {
    		levelorder.append(root + " ");
    	}
    	else if (level > 1) {
    		printGivenLevel(root.left, level - 1, levelorder);
    		printGivenLevel(root.right, level - 1, levelorder);
    	}
    }
  
    
    int height(BinaryNode<E> root) {
    	if (root != null) {
    		int lheight = height(root.getLeftChild());
    		int rheight = height(root.getRightChild());
    		
    		if (lheight > rheight) {
    			return lheight + 1;
    		}
    		else {
    			return rheight + 1;
    		}
    	}
    	else {
    		return 0;
    	}
    }


    public BinaryNode<E> minimum(){
        //Implement the minimum method
    	BinaryNode<E> minimum = this.root;
    	while(minimum.getLeftChild() != null) {
    		minimum = minimum.getLeftChild();
    	}
        return minimum;
    }
    public BinaryNode<E> maximum(){
        //Implement the maximum method
    	BinaryNode<E> maximum = this.root;
    	while(maximum.getRightChild() != null) {
    		maximum = maximum.getRightChild();
    	}
        return maximum;
    }
    
    public BinaryNode<E> findNode (E target) {
    	BinaryNode<E> current = this.root;
    	while (current != null) {
    		if (target.compareTo(current.data) < 0) {
    			current = current.getLeftChild();
    		}
    		else if (target.compareTo(current.data) > 0) {
    			current = current.getRightChild();
    		}
    		else {
    			break;
    		}
    	}
    	return current;
    }
    
    public BinaryNode<E> findParent(BinaryNode<E> node) {
    	if (node == null) {
    		return null;
    	}
    	E e = node.data;
    	BinaryNode<E> current = root;
    	BinaryNode<E> parent = null;
    	while (current != null) {
    		if (e.compareTo(current.data) < 0) {
    			parent = current;
    			current = current.getLeftChild();
    		}
    		else if (e.compareTo(current.data) > 0) {
    			parent = current;
    			current = current.getRightChild();
    		}
    		else {
    			break;
    		}
    	}
    	return parent;
    }
    
    public BinaryNode<E> findMin(BinaryNode<E> root) {
    	BinaryNode<E> current = root;
    	while (current.getLeftChild() != null) {
    		current = current.getLeftChild();
    	}
    	return current;
    }

    public BinaryNode<E> findMax(BinaryNode<E> root) {
    	BinaryNode<E> current = root;
    	while (current.getRightChild() != null) {
    		current = current.getRightChild();
    	}
    	return current;
    }
    
    public BinaryNode<E> successor(E target){
        //Implement the method to find the successor of the target key.
    	BinaryNode<E> current = findNode(target);
    	BinaryNode<E> parent = findParent(current);
    	BinaryNode<E> successor = null;
    	
    	if (current == null) {
    		return null;
    	}
    	if (current.getRightChild() != null) {
    		return findMin(current.getRightChild());
    	}
    	else {
    		successor = parent;
    		while (successor != null && current == successor.getRightChild()) {
    			current = successor;
    			successor = findParent(successor);
    		}
    		return successor;
    	}
    }

    public BinaryNode<E> predecessor(E target){
    	BinaryNode<E> current = findNode(target);
    	BinaryNode<E> parent = findParent(current);
    	BinaryNode<E> predecessor = null;
    	
    	if (current == null) {
    		return null;
    	}
    	if (current.getLeftChild() != null) {
    		return findMax(current.getLeftChild());
    	}
    	else {
    		predecessor = parent;
    		while (predecessor != null && current == predecessor.getLeftChild()) {
    			current = predecessor;
    			predecessor = findParent(predecessor);
    		}
    		return predecessor;
    	}
    }
        //Implement the method to find the predecessor of the target key.	
}