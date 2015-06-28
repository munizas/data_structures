public class MyLinkedList<T> {
    private ListNode<T> nil;
    private ListNode<T> root;
    private ListNode<T> cur;
    private int size;
    
    public MyLinkedList() {
        nil = new ListNode<T>();
        nil.setNext(nil);
        nil.setPrev(nil);
    }

    public void insert(T item) {
        ListNode<T> newNode = new ListNode<T>();
        newNode.setData(item);
        newNode.setNext(nil.getNext());
        nil.getNext().setPrev(newNode);
        nil.setNext(newNode);
        newNode.setPrev(nil);
        size++;
    }

    public ListNode<T> find(T item) {
        ListNode<T> node = nil.getNext();
        while (node != nil && node.getData() != item)
            node = node.getNext();
        return node;
    }

    public void delete(ListNode<T> node) {
        node.getNext().setPrev(node.getPrev());
        node.getPrev().setNext(node.getNext());
    }

    public void deleteItem(T data) {
        ListNode<T> node = find(data);
        if (node != nil)
            delete(node);
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode<T> it = nil;
        while (it.getNext() != nil) {
            sb.append(it.getNext().toString() + "--");
            it = it.getNext();
        }
        return sb.toString();
    }
}