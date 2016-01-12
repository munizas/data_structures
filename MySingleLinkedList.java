public class MySingleLinkedList {

	private Node dummyHead;
	private int size;

	public MySingleLinkedList() {
		dummyHead = new Node(null, null);
	}

	public int getSize() {
		return size;
	}

	/**
	*	Inserts the new node at the beginning of the list
	*/
	public void insert(Integer data) {
		Node node = new Node(dummyHead.getNext(), data);
		dummyHead.setNext(node);
		size++;
	}

	/**
	* Inserts the new node in ascending order
	*/
	public void insertAscending(Integer data) {
		Node it = dummyHead;
		while (it.getNext() != null) {
			if (data < it.getNext().getData())
				break;
			it = it.getNext();
		}
		it.setNext(new Node(it.getNext(), data));
		size++;
	}

	public void delete(Integer data) {
		Node it = dummyHead;
		while (it.getNext() != null) {
			if (data == it.getNext().getData())
				break;
			it = it.getNext();
		}
		if (it != null) {
			it.setNext(it.getNext().getNext());
			size--;
		}
	}

	public void print() {
		Node it = dummyHead;
		while (it.getNext() != null) {
			it = it.getNext();
			System.out.print(it.getData() + " ");
		}
		System.out.println("size: " + size);
	}

	public Integer getMthToLastData(int m) {
		// TODO
		return null;
	}

	/**
	*	Node classes representing a node in a singly linked list.
	*	Generified to accept any type of data.
	*/
	public class Node {
		private Node next;
		private Integer data;

		public Node(Node next, Integer data) {
			this.next = next;
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public Integer getData() {
			return data;
		}
	}
}