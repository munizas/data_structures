import java.util.ArrayList;

public class SudokuVerifier {
	public static boolean verifySolution(int[][] grid) {
		boolean valid = true;
		for (int i=0; i<9; i++) {
			int[] row = grid[i].clone();
			int[] col = new int[9];
			int[] square = new int[9];
			for (int j=0; j<9; j++) {
				col[j] = grid[j][i];
				square[j] = grid[(i/3) * 3 + j/3][i * 3 % 9 + j%3];
			}
			if (!(verifyArray(row) && verifyArray(col) && verifyArray(square))) {
				valid = false;
				break;
			}
		}
		return valid;
	}

	private static boolean verifyArray(int[] array) {
		boolean valid = true;
		ArrayList<Integer> seen = new ArrayList<>();
		for (int i=0; i<array.length; i++) {
			int element = array[i];
			if (seen.contains(element)) {
				valid = false;
				break;
			}
			else
				seen.add(element);
		}
		return valid;
	}

	public static void main(String[] args) {
		int[][] grid = {
			{5, 3, 4, 6, 7, 8, 9, 1, 2},
			{6, 7, 2, 1, 9, 5, 3, 4, 8},
			{1, 9, 8, 3, 4, 2, 5, 6, 7},
			{8, 5, 9, 7, 6, 1, 4, 2, 3},
			{4, 2, 6, 8, 5, 3, 7, 9, 1},
			{7, 1, 3, 9, 2, 4, 8, 5, 6},
			{9, 6, 1, 5, 3, 7, 2, 8, 4},
			{2, 8, 7, 4, 1, 9, 6, 3, 5},
			{3, 4, 5, 2, 8, 6, 1, 7, 9}
		};
		System.out.println("solution = " + SudokuVerifier.verifySolution(grid));
	}
}