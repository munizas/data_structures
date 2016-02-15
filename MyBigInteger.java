import java.lang.StringBuilder;

public class MyBigInteger {
	private String mag;

	public MyBigInteger(String mag) {
		this.mag = mag;
	}

	public String toString() {
		return mag;
	}

	public MyBigInteger add(MyBigInteger bigInt) {
		int magInt = stringToInt();
		int addInt = bigInt.stringToInt();
		int sum = magInt + addInt;
		return new MyBigInteger(MyBigInteger.intToString(sum));
	}

	private int stringToInt() {
		int num = 0;
		int i = 0;
		boolean isNeg = false;
		if (mag.charAt(0) == '-') {
			isNeg = true;
			i = 1;
		}
		for (; i<mag.length(); i++) {
			num *= 10;
			num += mag.charAt(i) - '0';
		}
		if (isNeg)
			num *= -1;
		return num;
	}

	private static String intToString(int num) {
		StringBuilder builder = new StringBuilder();
		boolean isNeg = false;
		if (num < 0) {
			isNeg = true;
			num *= -1;
		}
		while (num > 0) {
			builder.append((char)((num%10) + '0'));
			num /= 10;
		}
		if (isNeg)
			builder.append("-");
		return builder.reverse().toString();
	}
}