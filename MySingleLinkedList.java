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
	public Node insert(Integer data) {
		Node node = new Node(dummyHead.getNext(), data);
		dummyHead.setNext(node);
		size++;
		return node;
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
		Node runner = dummyHead;
		Node mth = dummyHead;
		for (int i=0; i<m; i++) {
			if (runner.getNext() != null)
				runner = runner.getNext();
			else
				return null; // illegal m value passed
		}

		while (runner.getNext() != null) {
			runner = runner.getNext();
			mth = mth.getNext();
		}

		return mth.getData();
	}

	public boolean hasCycle() {
		Node slow = dummyHead.getNext();
		if (slow == null || slow.getNext() == null)
			return false;
		Node fast = slow.getNext();

		while (fast != null && fast.getNext() != null) {
			if (fast == slow || fast.getNext() == slow)
				return true;
			slow = slow.getNext();
			fast = fast.getNext().getNext();
		}
		return false;
	}

	public Node getCycleNode() {
		Node slow = dummyHead.getNext();
		if (slow == null || slow.getNext() == null)
			return null;
		Node fast = slow.getNext();

		boolean hasCycle = false;
		while (fast != null && fast.getNext() != null) {
			if (fast == slow) {
				hasCycle = true;
				break;
			}
			slow = slow.getNext();
			fast = fast.getNext().getNext();
		}

		if (hasCycle) {
			slow = dummyHead.getNext();
			fast = fast.getNext();
			while (slow != fast) {
				slow = slow.getNext();
				fast = fast.getNext();
			}
			return slow;
		}
		else
			return null;
	}

	/**
	*	Node classes representing a node in a singly linked list.
	*	Generified to accept any type of data.
	*/
	public static class Node {
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