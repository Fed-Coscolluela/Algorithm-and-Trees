@SuppressWarnings("unchecked")
public class RedBlackTree<E extends Comparable<E>> extends BinarySearchTreeWithRotate<E> {
  
    
	private boolean fixupRequired;
  
    @Override
    public boolean insert(E item) {  	 	
        if (root == null) {
            root = new RedBlackNode<>(item);
            ((RedBlackNode<E>) root).isRed = false; 
            return true;
        } else {
            root = add((RedBlackNode<E>) root, item);
            ((RedBlackNode<E>) root).isRed = false; 
            return addReturn;
        }
    }

    private RedBlackNode<E> add(RedBlackNode<E> localRoot, E item) {    	
        if (item.compareTo((E) localRoot.data) == 0) {    
            addReturn = false;
            return localRoot;
            
        }    
        else if (item.compareTo((E) localRoot.data) < 0) {           
            if (localRoot.left == null) {
                localRoot.left = new RedBlackNode<>(item);
                addReturn = true;
                return localRoot;
            } 
            else { 
                moveBlackDown(localRoot);
                localRoot.left = add((RedBlackNode<E>) localRoot.left, item);
                if (((RedBlackNode<E>) localRoot.left).isRed) {
                    if (localRoot.left.left != null && ((RedBlackNode<E>) localRoot.left.left).isRed) {
                        ((RedBlackNode<E>) localRoot.left).isRed = false;
                        localRoot.isRed = true;
                        return (RedBlackTree<E>.RedBlackNode<E>) rotateRight(localRoot);
                    } 
                    else if (localRoot.left.right != null && ((RedBlackNode<E>) localRoot.left.right).isRed) {
                        localRoot.left = rotateLeft(localRoot.left);
                        ((RedBlackNode<E>) localRoot.left).isRed = false;
                        localRoot.isRed = true;
                        return (RedBlackTree<E>.RedBlackNode<E>) rotateRight(localRoot);
                    }
                }
                return localRoot;
            }
        } 
        else { 
            if (localRoot.right == null) {            
                localRoot.right = new RedBlackNode<>(item);
                addReturn = true;
                return localRoot;
            } 
            else { 
                moveBlackDown(localRoot);           
                localRoot.right = add((RedBlackNode<E>) localRoot.right, item);
        
                if (((RedBlackNode<E>) localRoot.right).isRed) {
                    if (localRoot.right.right != null && ((RedBlackNode<E>) localRoot.right.right).isRed) {
                        ((RedBlackNode<E>) localRoot.right).isRed = false;
                        localRoot.isRed = true;
                        return (RedBlackTree<E>.RedBlackNode<E>) rotateLeft(localRoot);
                    } else if (localRoot.right.left != null && ((RedBlackNode<E>) localRoot.right.left).isRed) {
                        localRoot.right = rotateRight(localRoot.right);
                        ((RedBlackNode<E>) localRoot.right).isRed = false;
                        localRoot.isRed = true;
                        return (RedBlackTree<E>.RedBlackNode<E>) rotateLeft(localRoot);
                    }
                }
                return localRoot;
            }

        }
    }
    
    private void moveBlackDown(RedBlackNode<E> localRoot) {
        if (localRoot.left != null && localRoot.right != null
            && ((RedBlackNode<E>) localRoot.left).isRed 
            && ((RedBlackNode<E>) localRoot.right).isRed) {
        	
            ((RedBlackNode<E>) localRoot.left).isRed = false;
            ((RedBlackNode<E>) localRoot.right).isRed = false;
            localRoot.isRed = true;
            
        }
    }

    @Override
    public E delete(E target) {
        fixupRequired = false;
        if (root == null) {
            return null;
        } 
        else {
            int compareReturn = target.compareTo(root.data);
            if (compareReturn == 0) {
                E oldValue = root.data;
                root = findReplacement((RedBlackTree<E>.RedBlackNode<E>) root);
                if (fixupRequired) {
                    root = fixupLeft((RedBlackTree<E>.RedBlackNode<E>) root);
                }
                return oldValue;
            } 
            else if (compareReturn < 0) {
                if (root.left == null) {
                    return null;
                } 
                else {
                    E oldValue = removeFromLeft((RedBlackTree<E>.RedBlackNode<E>) root, target);
                    if (fixupRequired) {
                        root = fixupLeft((RedBlackTree<E>.RedBlackNode<E>) root);
                    }
                    return oldValue;
                }
            } 
            else {
                if (root.right == null) {
                    return null;
                } 
                else {
                    E oldValue = removeFromRight((RedBlackTree<E>.RedBlackNode<E>) root, target);
                    if (fixupRequired) {
                        root = fixupRight((RedBlackTree<E>.RedBlackNode<E>) root);
                    }
                    return oldValue;
                }
            }
        }
    }

    private E removeFromLeft(RedBlackNode<E> parent, E item) {
        if (item.compareTo((E) parent.left.data) < 0) {
            if (parent.left.left == null) {
                return null;
            } 
            else {
                E oldValue = removeFromLeft((RedBlackTree<E>.RedBlackNode<E>) parent.left, item);
                if (fixupRequired) {
                    parent.left = fixupLeft((RedBlackTree<E>.RedBlackNode<E>) parent.left);
                }
                return oldValue;
            }
        } 
        else if (item.compareTo((E) parent.left.data) > 0) {
            if (parent.left.right == null) {
                return null;
            } 
            else {
                E oldValue = removeFromRight((RedBlackTree<E>.RedBlackNode<E>) parent.left, item);
                if (fixupRequired) {
                    parent.left = fixupRight((RedBlackTree<E>.RedBlackNode<E>) parent.left);
                }
                return oldValue;
            }
        } 
        else {
            E oldValue = (E) parent.left.data;
            parent.left = findReplacement((RedBlackTree<E>.RedBlackNode<E>) parent.left);
            return oldValue;
        }
    }

    private E removeFromRight(RedBlackNode<E> parent, E item) {
        if (item.compareTo((E) parent.right.data) < 0) {
            if (parent.right.left == null) {
                return null;
            } 
            else {
                E oldValue = removeFromLeft((RedBlackTree<E>.RedBlackNode<E>)parent.right, item);
                if (fixupRequired) {
                    parent.right = fixupLeft((RedBlackTree<E>.RedBlackNode<E>) parent.right);
                }
                return oldValue;
            }
        } 
        else if (item.compareTo((E) parent.right.data) > 0) {
            if (parent.right.right == null) {
                return null;
            } 
            else {
                E oldValue = removeFromRight((RedBlackTree<E>.RedBlackNode<E>) parent.right, item);
                if (fixupRequired) {
                    parent.right = fixupRight((RedBlackTree<E>.RedBlackNode<E>) parent.right);
                }
                return oldValue;
            }
        } 
        else {
            E oldValue = (E) parent.right.data;
            parent.right = findReplacement((RedBlackTree<E>.RedBlackNode<E>) parent.right);
            return oldValue;
        }
    }

    private RedBlackNode<E> findReplacement(RedBlackNode<E> node) {
        if (node.left == null) {
            if (((RedBlackNode<E>) node).isRed) {             
                return (RedBlackTree<E>.RedBlackNode<E>) node.right;
            } 
            else if (node.right == null) {           
                fixupRequired = true;
                return null;
            }
            else if (((RedBlackNode<E>) node.right).isRed) {
                // replace black node with red child
                ((RedBlackNode<E>) node.right).isRed = false;
                return (RedBlackTree<E>.RedBlackNode<E>) node.right;
            } 
            else {       
                throw new RuntimeException("Invalid Red-Black " + "Tree Structure");
            }
        } 
        else if (node.right == null) {
            if (((RedBlackNode<E>) node).isRed) { 
                return (RedBlackTree<E>.RedBlackNode<E>) node.left;
            } 
            else if (((RedBlackNode<E>) node.left).isRed) {
                ((RedBlackNode<E>) node.left).isRed = false;
                return (RedBlackTree<E>.RedBlackNode<E>) node.left;
            } 
            else {  
                throw new RuntimeException("Invalid Red-Black " + "Tree structure");
            }
        } 
        else {
            if (node.left.right == null) {
                node.data = node.left.data;
                if (((RedBlackNode<E>) node.left).isRed) {
                    node.left = node.left.left;
                } 
                else if (node.left.left == null) {
                    fixupRequired = true;
                    node.left = null;
                } 
                else if (((RedBlackNode<E>) node.left.left).isRed) {
                    ((RedBlackNode<E>) node.left.left).isRed = false;
                    node.left = node.left.left;
                } 
                else {
                    throw new RuntimeException("Invalid Red-Black " + "Tree structure");
                }
                return node;
            } 
            else {
                node.data = findLargestChild((RedBlackTree<E>.RedBlackNode<E>) node.left);
                if (fixupRequired) {
                    node.left = fixupRight((RedBlackTree<E>.RedBlackNode<E>) node.left);
                }
                if (fixupRequired) {
                    return fixupLeft(node);
                } 
                else {
                    return node;
                }
            }
        }
    }

    private E findLargestChild(RedBlackNode<E> parent) {
        if (parent.right.right == null) {
            E returnValue = (E) parent.right.data;
            if (((RedBlackNode<E>) parent.right).isRed) {
                parent.right = parent.right.left;
            } 
            else if (parent.right.left == null) {
                fixupRequired = true;
                parent.right = null;
            } 
            else if (((RedBlackNode<E>) parent.right.left).isRed) {
                ((RedBlackNode<E>) parent.right.left).isRed = false;
                parent.right = parent.right.left;
            } 
            else {
                throw new RuntimeException("Invalid Red-Black "+ "Tree structure");
                        
            }
            return returnValue;
        } else {
            E returnValue = findLargestChild((RedBlackTree<E>.RedBlackNode<E>) parent.right);
            if (fixupRequired) {
                parent.right = fixupRight((RedBlackTree<E>.RedBlackNode<E>) parent.right);
            }
            return returnValue;
        }
    }

    private RedBlackNode<E> fixupRight(RedBlackNode<E> localRoot) {
        if (localRoot.right != null
                && ((RedBlackNode<E>) localRoot.right).isRed) {
            
            ((RedBlackNode<E>) localRoot.right).isRed = false;
            fixupRequired = false;
            return localRoot;
        }
        
        RedBlackNode<E> s = (RedBlackNode<E>) localRoot.left;
        
        if (s.isRed) { 
            s.isRed = false;
            ((RedBlackNode<E>) localRoot).isRed = true;
            RedBlackNode<E> returnValue = (RedBlackTree<E>.RedBlackNode<E>) rotateRight(localRoot);
            returnValue.right = fixupRight((RedBlackTree<E>.RedBlackNode<E>) returnValue.right);
            if (fixupRequired) {
                return fixupRight(returnValue);
            } else {
                return returnValue;
            }
        } 
        else { 
            if ((s.left == null && s.right == null)
            		|| ((s.left != null
                    && !((RedBlackNode<E>) s.left).isRed)
                    && (s.right != null
                    && !((RedBlackNode<E>) s.right).isRed))) {
                
                s.isRed = true;
                return localRoot;
            } 
            else {
                if (s.right != null && ((RedBlackNode<E>) s.right).isRed) {
                   
                    s.isRed = true;
                    ((RedBlackNode<E>) s.right).isRed = false;
                    localRoot.left = rotateLeft(s);
                    s = (RedBlackNode<E>) localRoot.left;
                }
                
                s.isRed = ((RedBlackNode<E>) localRoot).isRed;
                ((RedBlackNode<E>) s.left).isRed = false;
                ((RedBlackNode<E>) localRoot).isRed = false;
                fixupRequired = false;
                return (RedBlackTree<E>.RedBlackNode<E>) rotateRight(localRoot);
            }
        }
    }

    private RedBlackNode<E> fixupLeft(RedBlackNode<E> localRoot) {       
        if (localRoot.left != null
                && ((RedBlackNode<E>) localRoot.left).isRed) {
          
            ((RedBlackNode<E>) localRoot.left).isRed = false;
            fixupRequired = false;
            return localRoot;
        }
        
        RedBlackNode<E> s = (RedBlackNode<E>) localRoot.right;
        
        if (s.isRed) { 
            s.isRed = false;
            ((RedBlackNode<E>) localRoot).isRed = true;
            RedBlackNode<E> returnValue = (RedBlackTree<E>.RedBlackNode<E>) rotateLeft(localRoot);
            returnValue.left = fixupLeft((RedBlackTree<E>.RedBlackNode<E>) returnValue.left);
            if (fixupRequired) {
                return fixupLeft(returnValue);
            } 
            else {
                return returnValue;
            }
        } 
        else { 
            if ((s.right == null && s.left == null)
                    || ((s.right != null
                    && !((RedBlackNode<E>) s.right).isRed) && (s.left != null
                    && !((RedBlackNode<E>) s.left).isRed))) {
                
                s.isRed = true;
                return localRoot;
            } 
            else {
                if (s.left != null && ((RedBlackNode<E>) s.left).isRed) {
                   
                    s.isRed = true;
                    ((RedBlackNode<E>) s.left).isRed = false;
                    localRoot.right = rotateRight(s);
                    s = (RedBlackNode<E>) localRoot.right;
                }
              
                s.isRed = ((RedBlackNode<E>) localRoot).isRed;
                ((RedBlackNode<E>) s.right).isRed = false;
                ((RedBlackNode<E>) localRoot).isRed = false;
                fixupRequired = false;
                return (RedBlackTree<E>.RedBlackNode<E>) rotateLeft(localRoot);
            }
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

    private class RedBlackNode<E> extends BinaryTree.BinaryNode {
        
		private boolean isRed;


        public RedBlackNode(E data) {
            super(data);
            isRed = true;
        }

        @Override
        public String toString() {
            /**
            if(isRed){
                return "Red : " + super.toString();
            } else {
                return "Black : " + super.toString();
            }
             */
            String color = isRed ? "(red)" : "(black)";
            return super.toString() + color;
        }
    }
}
