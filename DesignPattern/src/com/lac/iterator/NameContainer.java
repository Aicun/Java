package com.lac.iterator;

public class NameContainer implements Container {

	public String names[] = { "abc", "def", "ghi", "jkl", "mno" };

	@Override
	public Iterator getIterator() {
		return new NameIterator();
	}

	private class NameIterator implements Iterator {

		int index;

		@Override
		public boolean hasNext() {
			if (index < names.length) {
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			if(this.hasNext())
				return names[index++];
			return null;
		}

	}
}
