import java.util.Deque;
import java.util.ArrayDeque;

public class MyBinaryTree<T> {
    T root;
    MyBinaryTree<T> left, right;
    
    public MyBinaryTree(T root) {
        this.root = root;
    }
    
    public MyBinaryTree(T root, MyBinaryTree<T> left, MyBinaryTree<T> right) {
        this.root = root;
        this.left = left;
        this.right = right;
    }
    
    public MyBinaryTree(MyBinaryTree<T> that) {
        root = that.getRoot();
        left = that.getLeft();
        right = that.getRight();
    }
    
    public T getRoot() {
        return root;
    }
    
    public MyBinaryTree<T> getLeft() {
        return left;
    }
    
    public MyBinaryTree<T> getRight() {
        return right;
    }
    
    public void setRoot(T root) {
        this.root = root;
    }
    
    public void setLeft(MyBinaryTree<T> left) {
        this.left = left;
    }
    
    public void setRight(MyBinaryTree<T> right) {
        this.right = right;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        if (left != null) {
            sb.append(left.toString());
            sb.append(",");
        }
        sb.append(root);
        if (right != null) {
            sb.append(",");
            sb.append(right.toString());
        }
        sb.append(")");
        return sb.toString();
    }
    
    public boolean isLeaf() {
        return left==null && right==null;
    }
    
    public int size() {
        int size = 1;
        size += (left!=null ? left.size() : 0);
        size += (right!=null ? right.size() : 0);
        return size;
    }
    
    public int height() {
        int leftH = (left!=null ? left.height()+1 : 0);
        int rightH = (right!=null ? right.height()+1 : 0);
        return Math.max(leftH, rightH);
    }
    
    public boolean contains(T item) {
        if (root == item)
            return true;
        boolean leftC = (left!=null ? left.contains(item) : false);
        boolean rightC = (right!=null ? right.contains(item) : false);
        return leftC || rightC;
    }
    
    public int numLeaves() {
        if (left==null && right==null)
            return 1;
        int num = 0;
        if (left != null)
            num += left.numLeaves();
        if (right != null)
            num += right.numLeaves();
        return num;
    }
    
    public int count(T item) {
        int count = 0;
        if (root == item)
            count = 1;
        if (left != null)
            count += left.count(item);
        if (right != null)
            count += right.count(item);
        return count;
    }
    
    public boolean isFull() {
        int height = height();
        int size = size();
        return size == Math.pow(2, height+1) - 1;
    }
    
    public boolean isBalanced() {
        if (isLeaf())
            return true;
        int leftH = (left!=null ? left.height()+1 : 0);
        int rightH = (right!=null ? right.height()+1 : 0);
        return Math.abs(leftH - rightH) < 2;
    }
    
    public int level(T item) {
        if (root == item)
            return 0;
        if (isLeaf())
            return -1;
        int leftL = (left!=null ? left.level(item)+1 : -1);
        if (leftL == 0)
            leftL = -1;
        int rightL = (right!=null ? right.level(item)+1 : -1);
        if (rightL == 0)
            rightL = -1;
        return Math.max(leftL, rightL);
    }

    public static <T> void inOrderPrint(MyBinaryTree<T> tree) {
        if (tree.getLeft() != null)
            inOrderPrint(tree.getLeft());
        System.out.print(" " + tree.getRoot());
        if (tree.getRight() != null)
            inOrderPrint(tree.getRight());
    }
    
    public static <T> void postOrderPrint(MyBinaryTree<T> tree) {
        if (tree.getLeft() != null)
            postOrderPrint(tree.getLeft());
        if (tree.getRight() != null)
            postOrderPrint(tree.getRight());
        System.out.print(" " + tree.getRoot());
    }
    
    public static <T> void preOrderPrint(MyBinaryTree<T> tree) {
        System.out.print(" " + tree.getRoot());
        if (tree.getLeft() != null)
            preOrderPrint(tree.getLeft());
        if (tree.getRight() != null)
            preOrderPrint(tree.getRight());
    }
    
    public static <T> void levelOrderPrint(MyBinaryTree<T> tree) {
        Deque<MyBinaryTree<T>> queue = new ArrayDeque<>();
        queue.addLast(tree);
        while (!queue.isEmpty()) {
            MyBinaryTree<T> cur = queue.removeFirst();
            System.out.print(" " + cur.getRoot());
            if (cur.getLeft() != null)
                queue.addLast(cur.getLeft());
            if (cur.getRight() != null)
                queue.addLast(cur.getRight());
        }
    }

    public static <T> void printByLevel(MyBinaryTree<T> tree) {
        ArrayDeque<MyBinaryTree<T>> curLevel = new ArrayDeque<>();
        ArrayDeque<MyBinaryTree<T>> nextLevel = new ArrayDeque<>();
        curLevel.addLast(tree);
        while (!curLevel.isEmpty()) {
            MyBinaryTree<T> t = curLevel.removeFirst();
            System.out.print(t.getRoot() + " ");
            if (t.getLeft() != null)
                nextLevel.addLast(t.getLeft());
            if (t.getRight() != null)
                nextLevel.addLast(t.getRight());
            if (curLevel.isEmpty()) {
                curLevel = nextLevel.clone();
                nextLevel.clear();
                System.out.println();
            }
        }
    }

    public static <T> MyBinaryTree<T> buildInOrderTree(T[] input, int min, int max) {
        if (max < min)
            return null;
        int mid = (max+min)/2;
        MyBinaryTree<T> newNode = new MyBinaryTree<T>(input[mid]);
        newNode.setLeft(buildInOrderTree(input, min, mid-1));
        newNode.setRight(buildInOrderTree(input, mid+1, max));
        return newNode;
    }

    public static MyBinaryTree<Integer> buildPostOrderTree(Integer[] input, int leftIndex, int rightIndex) {
        if (rightIndex < leftIndex)
            return null;
        MyBinaryTree<Integer> newNode = new MyBinaryTree<Integer>(input[rightIndex]);
        int lastLeftIndex = findLastLeftIndex(input, leftIndex, rightIndex-1, input[rightIndex]);
        if (lastLeftIndex > -1) {
            newNode.setLeft(buildPostOrderTree(input, leftIndex, lastLeftIndex));
            newNode.setRight(buildPostOrderTree(input, lastLeftIndex+1, rightIndex-1));
        }
        return newNode;
    }

    public static MyBinaryTree<Integer> buildFromInOrderAndPostOrder(int[] inOrder, int lowInOrder, int highInOrder, int[] postOrder, int lowPostOrder, int highPostOrder) {
        if (highInOrder < lowInOrder || highPostOrder < lowPostOrder)
            return null;

        MyBinaryTree<Integer> root = new MyBinaryTree<>(postOrder[highPostOrder]);
        int divideIndex = searchInOrder(inOrder, lowInOrder, highInOrder, postOrder[highPostOrder]);
        int sizeLeftSubTree = divideIndex - lowInOrder;
        int sizeRightSubTree = highInOrder - divideIndex;

        root.setRight(buildFromInOrderAndPostOrder(inOrder, divideIndex+1, highInOrder, postOrder, highPostOrder-sizeRightSubTree, highPostOrder-1));
        root.setLeft(buildFromInOrderAndPostOrder(inOrder, lowInOrder, divideIndex-1, postOrder, highPostOrder-sizeRightSubTree-sizeLeftSubTree, highPostOrder-sizeRightSubTree));

        return root;
    }

    private static int searchInOrder(int[] inOrder, int lowInOrder, int highInOrder, int target) {
        for (int i=lowInOrder; i<=highInOrder; i++) {
            if (inOrder[i] == target)
                return i;
        }
        return lowInOrder;
    }

    private static int findLastLeftIndex(Integer[] input, int leftIndex, int rightIndex, int value) {
        int index = -1;
        for (int i=leftIndex; i<=rightIndex; i++) {
            if (input[i]>value) {
                index = i-1;
                break;
            }
        }
        return index;
    }
}