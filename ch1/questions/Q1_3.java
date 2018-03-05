public class Q1_3 {

	public static String urlify(String unescaped, int length) { // Assumptions: input has space at the end for encoding, and length is correct.
		char[] charArray = unescaped.toCharArray();
		int pointer = charArray.length - 1;
		for (int i = length - 1; i >= 0; i--) {
			if (charArray[i] == ' ') {
				charArray[pointer--] = '0';
				charArray[pointer--] = '2';
				charArray[pointer--] = '%';
			}
			else charArray[pointer--] = charArray[i];
		}
		return new String(charArray);
	}
}