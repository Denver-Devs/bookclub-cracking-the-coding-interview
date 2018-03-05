public class Test {
	public static void main(String[] args) {

		System.out.println("Question 1.1: isUnique");
		isTrue(Q1_1.isUnique("abc"), "abc");
		isFalse(Q1_1.isUnique("aba"), "aba");
		isFalse(Q1_1.isUnique("aab"), "aab");

		System.out.println("Question 1.2: isPermutation");
		isTrue(Q1_2.isPermutation("abc", "abc"), "abc abc");
		isTrue(Q1_2.isPermutation("abc", "cab"), "abc cab");
		isFalse(Q1_2.isPermutation("abc", "abcd"), "abc abcd");
		isFalse(Q1_2.isPermutation("abc", "abd"), "abc, abd");

		System.out.println("Question 1.3: URLify");
		isEqual("Mr%20John%20Smith", Q1_3.urlify("Mr John Smith    ", 13));
		isEqual("a%20b", Q1_3.urlify("a b  ", 3));

		System.out.println("Question 1.3: palindrome");
		isTrue(Q1_4.palindrome("aabccdd"), "aabccdd");
		isTrue(Q1_4.palindrome("aabbccdd"), "aabbccdd");
		isFalse(Q1_4.palindrome("aabccd"), "aabccd");
		isTrue(Q1_4.palindrome("taco cat"), "taco cat");
		isFalse(Q1_4.palindrome("taco caz"), "taco caz");
		
	}

	private static void isTrue(boolean val, String description) {
		System.out.println((val ? "PASS" : "* FAIL") + ": " + description );
	}

	private static void isFalse(boolean val, String description) {
		isTrue(!val, description);
	}

	private static void isEqual(String expected, String actual) {
		System.out.println(actual.equals(expected) ? "PASS: " + actual : "FAIL: Expected = " + expected + ", Actual = " + actual);
	}
}