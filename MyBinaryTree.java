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
}