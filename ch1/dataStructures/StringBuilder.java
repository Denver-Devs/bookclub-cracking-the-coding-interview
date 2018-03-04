public class StringBuilder {

	private Element head;
	private Element tail;
	private int stringLength = 0;
	
	public void append(String str) {
		for (char ch : str.toCharArray()) {
			if (head == null) {
				head = new Element(ch);
				tail = head;
			} else {
				tail = tail.append(ch);
			}
			stringLength++;
		}
	}

	@Override
	public String toString() {
		char[] result = new char[stringLength];
		Element pointer = head;
		for (int i = 0; i < stringLength; i++) {
			result[i] = pointer.getValue();
			pointer = pointer.getNext();
		}
		return new String(result);
	}

	// Using a linked list for this
	private class Element {
		private final char val;
		private Element next;

		public Element(char val) {
			this.val = val;
		}

		public char getValue() {
			return val;
		}

		public Element getNext() {
			return next;
		}

		public Element append(char val) {
			if (this.next == null) {
				this.next = new Element(val);
				return this.next;
			} else {
				return this.next.append(val);
			}
		}
	}
}