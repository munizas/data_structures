import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class SumProblems {
	public static int[] twoSum(int[] input, int target) {
		int[] result = new int[2];
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i=0; i<input.length; i++) {
			if (map.containsKey(input[i])) {
				int index = map.get(input[i]);
				result[0] = index+1;
				result[1] = i+1;
				break;
			}
			else {
				map.put(target-input[i], i);
			}
		}
		return result;
	}

	public static List<String> threeSum(int[] input, int target) {
		List<String> results = new ArrayList<>();
		for (int i=0; i<input.length-2; i++) {
			for (int j=i+1; j<input.length-1; j++) {
				for (int k=j+1; k<input.length; k++) {
					if (input[i] + input[j] + input[k] == target)
						results.add("index1: " + i + ", index2: " + j + ", index3: " + k);
				}
			}
		}
		return results;
	}

	public static void main(String[] args) {
		int target = 9;
		int[] input = {2, 7, 5, 4, 8};
		int[] res = SumProblems.twoSum(input, target);
		System.out.println("index1: " + res[0] + ", index2: " + res[1]);

		target = 12;
		List<String> results = SumProblems.threeSum(input, target);
		for (String str : results)
			System.out.println(str);
	}
}