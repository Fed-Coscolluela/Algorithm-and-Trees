public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
        bst.insert(21);
        bst.insert(10);
        bst.insert(40);
        bst.insert(42);
        bst.insert(31);
        bst.insert(22);
        bst.insert(35);

        System.out.println("BINARY SEARCH TREE");
        System.out.println("Searching for 31: "+bst.search(31)); //31
        System.out.println("The minimum key is: "+bst.minimum()); //10
        System.out.println("The maximum key is: "+bst.maximum()); //42
        System.out.println("The successor of 10 is: "+bst.successor(10)); //21
        System.out.println("The predecessor of 10 is: "+bst.predecessor(10)); //null
        System.out.println("Successor of 22 is " + bst.successor(22)); //31
        System.out.println("Predecessor of 22 is " + bst.predecessor(22)); //21
        System.out.println("The preorder traversal of bst: "+bst.preorder()); // 21 10 40 31 22 35 42
        System.out.println("The inorder traversal of bst: "+bst.inorder()); // 10 21 22 31 35 40 42
        System.out.println("The postorder traversal of bst: "+bst.postorder()); // 10 22 35 31 42 40 21
        System.out.println("The level order traversal of bst: "+bst.levelorder()); //21 10 40 31 42 22 35

        System.out.println("Deleting 31: " + bst.delete(31)); //31
        System.out.println("Deleting 10: " + bst.delete(10)); //10
        System.out.println("Deleting 35: " + bst.delete(35)); //35
        System.out.println("Deleting 8: " + bst.delete(8)); //null

        System.out.println("Searching for 31: " + bst.search(31)); //null
        System.out.println("Searching for 10: " + bst.search(10)); //null
        System.out.println("\n\n");

        //Personal test cases for the AVL Tree
        System.out.println("AVL TREE");
        System.out.println("TEST CASE 1:");
        AVLTree<Integer> avl = new AVLTree<Integer>();
        avl.insert(9);
        avl.insert(5);
        avl.insert(10);
        avl.insert(0);
        avl.insert(6);
        avl.insert(11);
        avl.insert(3);
        avl.insert(1);
        avl.insert(2);
        System.out.println("BEFORE: The preorder traversal of avl: "+ avl.preorder());//9(-1) 3(0) 1(0) 0(0) 2(0) 5(1) 6(0) 10(1) 11(0)
        
        System.out.println("Deleting 10: " + avl.delete(10));//10
        System.out.println("AFTER: The preorder traversal of avl: "+ avl.preorder());//3(1) 1(0) 0(0) 2(0) 9(-1) 5(1) 6(0) 11(0)
        
        System.out.println("\n\n");
        
        System.out.println("TEST CASE 2:");
        AVLTree<Integer> avl1 = new AVLTree<Integer>();
        avl1.insert(20);
        avl1.insert(4);
        avl1.insert(77);
        avl1.insert(-12);
        avl1.insert(10);
        avl1.insert(1);
        avl1.insert(103);
        avl1.insert(68);
        avl1.insert(32);
        avl1.insert(19);
        System.out.println("BEFORE: The inorder traversal of avl: "+ avl1.inorder()); //-12(1) 1(0) 4(0) 10(1) 19(0) 20(0) 32(0) 68(1) 77(1) 103(0)
        
        System.out.println("Deleting 10: " + avl1.delete(77));//77
        System.out.println("Deleting 10: " + avl1.delete(10));//10
        System.out.println("Deleting 19: " + avl1.delete(19));//19

        System.out.println("AFTER: The inorder traversal of avl: "+ avl1.inorder()); //-12(0) 1(0) 4(0) 20(0) 32(0) 68(0) 103(0)
        
        System.out.println("\n");
        
        System.out.println("TEST CASE 3:");
        AVLTree<Integer> avl2 = new AVLTree<Integer>();
        avl2.insert(200);
        avl2.insert(42);
        avl2.insert(771);
        avl2.insert(-120);
        avl2.insert(109);
        avl2.insert(15);

        System.out.println("BEFORE: The postorder traversal of avl: "+ avl2.postorder());// 15(0) -120(1) 109(0) 771(0) 200(0) 42(0)
        
        System.out.println("Deleting 77: " + avl2.delete(77));//null
        System.out.println("Deleting 10: " + avl2.delete(10));//null
        System.out.println("AFTER: The postorder traversal of avl: "+ avl2.postorder());//15(0) -120(1) 109(0) 771(0) 200(0) 42(0)
        System.out.println("\n");

        System.out.println("TEST CASE 4:");
        AVLTree<Integer> avl3 = new AVLTree<Integer>();
        avl3.insert(10);
        avl3.insert(13);
        avl3.insert(9);
        avl3.insert(4);
        avl3.insert(5);
        avl3.insert(1);
        avl3.insert(15);
        avl3.insert(18);
        
        System.out.println("Delete: " + avl3.delete(10));
        System.out.println("levelorder: " + avl3.levelorder());//5(1) 4(-1) 15(-1) 1(0) 9(1) 18(0) 13(0)
        System.out.println("\n\n");
      
        //Personal test cases for the RedBlack Tree
        System.out.println("REDBLACK TREE");
        RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
        rbt.insert(21);
        rbt.insert(10);
        rbt.insert(40);
        rbt.insert(42);
        rbt.insert(31);
        rbt.insert(22);
        rbt.insert(35);

        System.out.println("Preorder of RBT: " + rbt.preorder());
        System.out.println("inorder traversal of RBT: "+rbt.inorder());
        System.out.println("postorder of RBT: " + rbt.postorder());

        System.out.println("\n");
        System.out.println("Delete: " + rbt.delete(31));//31
        System.out.println("Delete: " + rbt.delete(31));//null
        
        System.out.println("levelorder: " + rbt.levelorder());//21(black) 10(black) 40(red) 22(black) 42(black) 35(red) 
        System.out.println("Delete: " + rbt.delete(10));//10
        System.out.println("levelorder: " + rbt.levelorder());//40(black) 22(red) 42(black) 21(black) 35(black) 
        System.out.println("Delete: " + rbt.delete(35));//35
        System.out.println("levelorder: " + rbt.levelorder());//40(black) 22(black) 42(black) 21(red) 
        System.out.println("Delete: " + rbt.delete(2));//null
    }
}
