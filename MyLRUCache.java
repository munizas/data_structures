import java.util.Map;
import java.util.HashMap;

public class MyLRUCache {
	private Map<Integer, Node> map;
	private Node head, tail;
	private int capacity;
	private int size;

	public MyLRUCache(int capacity) {
		map = new HashMap<>(capacity);
		head = new Node(0, "HEAD");
		tail = new Node(-1, "TAIL");

		head.setNext(tail);
		head.setPrev(tail);

		tail.setNext(head);
		tail.setPrev(head);

		this.capacity = capacity;
	}

	public String get(int key) {
		String value = null;
		if (map.containsKey(key)) {
			Node node = map.get(key);
			recentlyUsed(node);
			value = node.getData();
		}
		return value;
	}

	public void put(int key, String value) {
		if (size == capacity)
			removeEldestElement();
		Node newNode = new Node(key, value, head.getNext(), head);
		head.getNext().setPrev(newNode);
		head.setNext(newNode);

		map.put(key, newNode);

		size++;
	}

	private void recentlyUsed(Node node) {
		node.getPrev().setNext(node.getNext());
		node.getNext().setPrev(node.getPrev());

		// insert at front of list
		node.setPrev(head);
		node.setNext(head.getNext());

		head.getNext().setPrev(node);
		head.setNext(node);
	}

	private void removeEldestElement() {
		Node remove = tail.getPrev();
		tail.setPrev(remove.getPrev());
		remove.getPrev().setNext(tail);
		remove.setNext(null);
		remove.setPrev(null);
		map.remove(remove.getKey());
		size--;
	}

	public void display() {
		System.out.println("\nHashMap contents======");
		for (Integer key : map.keySet())
			System.out.println("key=" + key + ", value=" + map.get(key));

		System.out.println("\nList contents=========");
		Node iter = head;
		do {
			System.out.println(iter + ": " + iter.getData());
			iter = iter.getNext();
		} while (iter != head);
		System.out.println();
	}

	private class Node {
		int key;
		private String data;
		private Node next, prev;

		public Node(int key, String data) {
			this(key, data, null, null);
		}

		public Node(int key, String data, Node next, Node prev) {
			this.key = key;
			this.data = data;
			this.next = next;
			this.prev = prev;
		}

		public int getKey() {
			return key;
		}

		public String getData() {
			return data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public Node getPrev() {
			return prev;
		}

		public void setPrev(Node prev) {
			this.prev = prev;
		}
	}

}