@SuppressWarnings("unchecked")
public class AVLTree<E extends Comparable<E>> extends BinarySearchTreeWithRotate<E> {
    private boolean increase;
	private boolean decrease;
	
	
	public boolean insert(E item) {
		increase = false;
		root = insertSupport((AVLNode<E>) root, item);
		return addReturn;
	}
	private AVLNode<E> insertSupport(AVLNode<E> localRoot, E item){
		
		
		if (localRoot == null) {
			addReturn = true;
			increase = true;
			return new AVLNode<E>(item);
			}
		
		
		if (item.compareTo((E) localRoot.data) == 0) {
			
			increase = false;
			addReturn = false;
			return localRoot;
			}
		
		else if (item.compareTo((E) localRoot.data) < 0) {
			
			localRoot.left = insertSupport((AVLNode<E>) localRoot.left, item);
			
			if (increase) {
				decrementBalance(localRoot);
				if (localRoot.balance < AVLNode.LEFT_HEAVY) {
					increase = false;
					return rebalanceLeft(localRoot);
				}
				
			}
			return localRoot; 
		}
		
		
		else { 
			localRoot.right = insertSupport((AVLNode<E>) localRoot.right, item);
			
			if (increase) {
				incrementBalance(localRoot);
				if (localRoot.balance > AVLNode.RIGHT_HEAVY) {
					increase = false;
					return rebalanceRight(localRoot);
				 } 
			 }
			return localRoot; 
		}
    }
    
	@Override
	public E delete( E target) {
		decrease = false;
		root = deleteSupport((AVLNode<E>)root, target);
		return deleteReturn;
	}
	
	
	
	
	private AVLNode<E> deleteSupport(AVLNode<E> localRoot, E item) {
		if (localRoot == null) {
		
			decrease = false;
			deleteReturn = null;
			return localRoot;
		}
	
		int compResult = item.compareTo((E) localRoot.data);
		if (compResult < 0) {
			
			localRoot.left = deleteSupport((AVLNode<E>)localRoot.left, item);
			if (decrease) {
				incrementBalance(localRoot);
				if ( localRoot.balance > AVLNode.RIGHT_HEAVY ) {
					return rebalanceRight(localRoot);
				}
				
			}
			return localRoot; 
		} 
		else if (compResult > 0) {
			
			localRoot.right = deleteSupport((AVLNode<E>)localRoot.right, item);
			if(decrease) {
				decrementBalance(localRoot);
				if ( localRoot.balance < AVLNode.LEFT_HEAVY ) {
					return rebalanceLeft(localRoot);
				}
			}
			return localRoot; 
		} 
		else {
			// item is at local root.
			deleteReturn = (E) localRoot.data;
			decrease = true;
			if (localRoot.left == null) {
				
				return (AVLNode<E>)localRoot.right;
			} else if (localRoot.right == null) {
				
				return (AVLNode<E>)localRoot.left;
			} else {
			
				
				if (localRoot.left.right == null) {
					
					localRoot.data = localRoot.left.data;
				
					localRoot.left = localRoot.left.left;
					

					incrementBalance(localRoot);
					if ( localRoot.balance > AVLNode.RIGHT_HEAVY ) {
						return rebalanceRight(localRoot);
					}
					return localRoot;
					
				} else {
					AVLNode<E> node = (AVLNode<E>)localRoot.left ;
					localRoot.data = findLargestChild(node);
					localRoot.left = modifyLeft((AVLNode<E>)localRoot.left);
					if (decrease) {
						incrementBalance(localRoot);
						if ( localRoot.balance > AVLNode.RIGHT_HEAVY ) {
							return rebalanceRight(localRoot);
						}
						
					}
					return localRoot;
				}
				
			}
		}
	}
	
	private E findLargestChild(AVLNode<E> parent) {

		if ( parent.balance == AVLNode.RIGHT_HEAVY) {
			decrease = true;
		}
		AVLNode<E> c_parent = parent;
		while (c_parent.right.right != null ) {
			c_parent = (AVLNode<E>)c_parent.right;
		}
		E returnValue = (E) c_parent.right.data;
		c_parent.right = c_parent.right.left;
		
		return returnValue;
	}
	
	private AVLNode<E> modifyLeft(AVLNode<E> parent) {
		
		decrementBalance(parent);
		if ( parent.balance < AVLNode.LEFT_HEAVY ) {
			parent = rebalanceLeft(parent);
		}
		return parent;
		
	}
	
	private AVLNode<E> rebalanceLeft(AVLNode<E> localRoot) {
		
		
		AVLNode<E> leftChild = (AVLNode<E>) localRoot.left;
		
		if (leftChild.balance > AVLNode.BALANCED) {  
			
			AVLNode<E> leftRightChild = (AVLNode<E>) leftChild.right;
		
			if (leftRightChild.balance < AVLNode.BALANCED) {
				leftChild.balance = AVLNode.BALANCED;
				leftRightChild.balance = AVLNode.BALANCED;
				localRoot.balance = AVLNode.RIGHT_HEAVY;
			} 
			else if (leftRightChild.balance > AVLNode.BALANCED) {
				leftChild.balance = AVLNode.LEFT_HEAVY;
				leftRightChild.balance = AVLNode.BALANCED;
				localRoot.balance = AVLNode.BALANCED;
			}
			else { 
				leftChild.balance = AVLNode.BALANCED;
				leftRightChild.balance = AVLNode.BALANCED;
				localRoot.balance = AVLNode.BALANCED;
			}
			
			localRoot.left = rotateLeft(leftChild);
		}
		else if (leftChild.balance < AVLNode.BALANCED){  
			
			leftChild.balance = AVLNode.BALANCED;
			localRoot.balance = AVLNode.BALANCED;
		}
		else {
			localRoot.balance = AVLNode.LEFT_HEAVY;
			leftChild.balance = AVLNode.RIGHT_HEAVY;
		}
		
		if ( localRoot.balance == AVLNode.BALANCED ) {
			decrease = true;
		}
		else {
			decrease = false;
		}
		
		return (AVLNode<E>) rotateRight(localRoot);
	}
	
	private AVLNode<E> rebalanceRight(AVLNode<E> localRoot) {
		
		
		AVLNode<E> rightChild = (AVLNode<E>) localRoot.right;
		
		if ( rightChild.balance < AVLNode.BALANCED ) {  
			
			AVLNode<E> rightLeftChild = (AVLNode<E>) rightChild.left;
			
			if ( rightLeftChild.balance < AVLNode.BALANCED ) { 
				localRoot.balance = AVLNode.BALANCED;
				rightChild.balance = AVLNode.RIGHT_HEAVY;
				rightLeftChild.balance = AVLNode.BALANCED ;
			}
			else if ( rightLeftChild.balance > AVLNode.BALANCED ){  
				localRoot.balance = AVLNode.LEFT_HEAVY;
				rightChild.balance = AVLNode.BALANCED;
				rightLeftChild.balance = AVLNode.BALANCED;
			}
			else { // [deletion case]
				localRoot.balance = AVLNode.BALANCED;
				rightChild.balance = AVLNode.BALANCED;
				rightLeftChild.balance = AVLNode.BALANCED;
				
			}
			
			localRoot.right = rotateRight(rightChild);
		}
		
		else if ( rightChild.balance > AVLNode.BALANCED ) { 
			
			rightChild.balance = AVLNode.BALANCED;
			localRoot.balance = AVLNode.BALANCED;
		}
		else { 
			rightChild.balance = AVLNode.LEFT_HEAVY;
			localRoot.balance = AVLNode.RIGHT_HEAVY;
		}
		
		if ( localRoot.balance == AVLNode.BALANCED ) {
			decrease = true;
		}
		else {
			decrease = false;
		} 
		
		
		return (AVLNode<E>) rotateLeft(localRoot);
		
	}
	
	private void decrementBalance(AVLNode<E> node) {
		
		int prev_balance = node.balance;
		
		node.balance--;
		if (node.balance == AVLNode.BALANCED) {
			
			increase = false;
			
			decrease = true;
		}
		else if ( prev_balance == AVLNode.BALANCED && node.balance != AVLNode.BALANCED ) {
		
			decrease = false;
			increase = true;
		 }  
		
	}
	
	private void incrementBalance( AVLNode<E> node ) {
		
		int prev_balance = node.balance;
	
		node.balance++;
		if (node.balance == AVLNode.BALANCED) {
			
			increase = false;
			decrease = true;
		}
		else if ( prev_balance == AVLNode.BALANCED && node.balance != AVLNode.BALANCED ) {
		
			decrease = false;
			increase = true;
		} 
	}

    public String preorder(){
        return super.preorder();
    }

    public String postorder(){
        return super.postorder();
    }

    public String inorder(){
        return super.inorder();
    }


    private class AVLNode<E> extends BinaryTree.BinaryNode {
        private static final int LEFT_HEAVY = -1;
        private static final int RIGHT_HEAVY = 1;
        private static final int BALANCED = 0;
        private int balance;

        /**
         * Constructor for AVLNode
         *
         * @param data
         *            data to be inserted into new AVLNode
         */
        public AVLNode(E data) {
            super(data);
            balance = BALANCED;
        }

        @Override
        public String toString() {
            return super.toString() + "(" + balance + ")";
        }

        @Override
        protected BinaryNode getLeftChild() {
            return super.getLeftChild();
        }

        @Override
        protected BinaryNode getRightChild() {
            return super.getRightChild();
        }
    }
}