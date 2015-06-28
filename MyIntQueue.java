public class MyIntQueue {
	private int[] queue;
	private int head, tail;

	public MyIntQueue(int size) {
		queue = new int[size];
	}

	public void enqueue(int val) {
		if (head  == nextTail())
			System.out.println("queue is full");
		else {
			queue[tail] = val;
			tail = nextTail();
		}
	}

	public int dequeue() {
		if (isEmpty()) {
			System.out.println("queue is empty");
			return -1;
		}
		int val = queue[head];
		head = nextHead();
		return val;
	}

	public boolean isEmpty() {
		return head == tail;
	}

	private int nextHead() {
		if (head + 1 == queue.length)
			return 0;
		return head + 1;
	}

	private int nextTail() {
		if (tail + 1 == queue.length)
			return 0;
		return tail + 1;
	}
}