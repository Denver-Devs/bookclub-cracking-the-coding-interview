import java.util.*;

/**
 * Check Permutation
 */
public class Q1_2 {
	public static boolean isPermutation(String str1, String str2) {
		// low hanging fruit
		if (str1.length() != str2.length()) return false;
		if (str1.equals(str2)) return true;

		char[] chars1 = str1.toCharArray();
		char[] chars2 = str2.toCharArray();
		Arrays.sort(chars1);
		Arrays.sort(chars2);

		for (int i = 0; i < chars1.length; i++) {
			if (chars1[i] != chars2[i]) return false;
		}

		return true;
	}
}