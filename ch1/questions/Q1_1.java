/**
 * Is Unique
 */
public class Q1_1 {

	public static boolean isUnique(String input) {
		for (int i = 0; i < input.length(); i++) {
			for (int j = i + 1; j < input.length(); j++) {
				if (input.charAt(i) == input.charAt(j)) {
					return false;
				}
			}
		}
		return true;
	}
}