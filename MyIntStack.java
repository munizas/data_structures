public class MyIntStack {
	private int[] stack;
	private int top;

	public MyIntStack(int size) {
		stack = new int[size];
		top = -1;
	}

	public boolean isEmpty() {
		return top < 0;
	}

	public void push(int val) {
		if (top + 1 >= stack.length)
			System.out.println("stackoverflow...stack is full");
		else {
			top++;
			stack[top] = val;
		}
	}

	public int pop() {
		if (isEmpty()) {
			System.out.println("stack is already empty");
			return -1;
		}
		else {
			return stack[top--];
		}
	}

}