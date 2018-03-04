public class Test {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		list.add("foo");
		list.add("bar");
		list.add("baz");
		System.out.println(list.size());

		StringBuilder sb = new StringBuilder();
		sb.append("foo");
		sb.append("bar");
		sb.append("baz");
		System.out.println(sb.toString());
	}
}