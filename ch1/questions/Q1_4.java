import java.util.*;

public class Q1_4 {
	public static boolean palindrome(String input) {
		
		char[] chars = input.toLowerCase().toCharArray();
		Arrays.sort(chars);
		
		boolean acceptOdd = true;

		for (int i = 0;  i < chars.length;) {
			if (chars.length == i + 1) {
				return acceptOdd;
			}

			if (chars[i] == chars[i + 1]) {
				i += 2; // Move on to the next pair
			} 
			else if (chars[i] == ' ') {
				i++; // We're skipping spaces
			} else if (acceptOdd) {
				acceptOdd = false;
				i++;
			} else {
				return false;
			}
		}
		return true;
	}
}