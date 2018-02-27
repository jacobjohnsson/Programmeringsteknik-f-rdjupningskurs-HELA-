package map;
import static org.junit.Assert.*;

import java.util.HashSet;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private Entry<K,V>[] table;
	private double loadFactor = 0.75;
	private int size;
	private int capacity;

	public SimpleHashMap() {
		table = (Entry<K,V>[]) new Entry[16];
		capacity = 16;
		size = 0;
	}

	public SimpleHashMap(int x) {
		if (x == 0) {
			throw new IllegalArgumentException();
		}
		table = (Entry<K,V>[]) new Entry[x];
		capacity = x;
		size = 0;
	}

	public String show() {
		if (size > 0) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < table.length; i++) {
				sb.append(i);
				if (table[i] != null) {
					Entry<K,V> currentEntry = table[i];
					while (currentEntry != null) {
						sb.append('\t' + currentEntry.toString());
						currentEntry = currentEntry.next;
					}
				}
				sb.append('\n');
			}
			return sb.toString();
		} else {
			return "";
		}
	}

	@Override
	public int size() {
		return size;
}

	@Override
	public boolean isEmpty(){
		return size == 0;
	}

	// ska returnera det index som ska användas för nyckeln key
	private int index(K key){
		return Math.abs(key.hashCode() % capacity);
	}

	private Entry<K,V> find(int index, K key){
		if (index < capacity && index >= 0) {
			Entry<K,V> e = table[index];
			if (e != null) {
				while (e != null && e.key != key) {
					e = e.next;
				}
				return e;
			}
		}
		return null;
	}

	@Override
	public V get(Object arg0) {
		K key = (K) arg0;
		int index = index(key);

		Entry<K,V> current = table[index];

		while (current != null) {
			if (key.equals(current.getKey())) {
				return current.getValue();
			}
			current = current.next;
		}
		return null;
	}

	@Override
	public V put(K arg0, V arg1) {
		if (size / capacity > loadFactor) {
			reHash();
		}

		int index = index(arg0);
		Entry<K,V> foundEntry = find(index, arg0);
		Entry<K,V> topEntry = table[index];

		if (foundEntry == null && topEntry == null) {
			table[index] = new Entry<K,V>(arg0, arg1);
			size++;
			return null;
		} else if (foundEntry == null && topEntry != null) {
			while (topEntry.next != null) {
				topEntry = topEntry.next;
			}
			topEntry.next = new Entry<K,V>(arg0, arg1);
			size++;
			return null;
		} else {
			V previousValue = foundEntry.value;
			foundEntry.value = arg1;
			return previousValue;
		}
	}

	private void reHash(){
		int oldCap = capacity;
		Entry<K,V>[] oldTable = table;

		int newCap = oldCap * 2;
		Entry<K,V>[] newTable = (Entry<K,V>[]) new Entry[newCap];

		table = newTable;

		System.out.println("oldCap= " + oldCap + ", newCap= " + newCap);

		capacity = newCap;

		for (int i = oldCap; i-- > 0;) {
			Entry<K,V> old = oldTable[i];
			while (old != null) {
				Entry<K,V> e = old;
				old = old.next;

				int index = index(e.getKey());
				e.next = newTable[index];
				newTable[index] = e;
			}
		}
	}

	@Override
	public V remove(Object arg0) {
		if (arg0 != null) {
			K key = (K) arg0;
			int index = index(key);
			Entry<K,V> topEntry = table[index];

			if (topEntry == null) {															// Tom lista
				return null;
			} else if (key.equals(topEntry.getKey())) {					// Första elementet i listan
				table[index] = topEntry.next;
				size--;
				return topEntry.getValue();
			} else {
				Entry<K,V> current = table[index].next;
				Entry<K,V> previous = table[index];
				while (current != null) {
					if (key.equals(current.getKey())) {
						previous.next = current.next;
						size--;
						return current.getValue();
					}
					current = current.next;
					previous = previous.next;
				}
				return null;
			}
		} else {
			return null;
		}
	}

	public static class Entry<K, V> implements Map.Entry<K,V>{
		private K key;
		private V value;
		private Entry<K,V> next;

		public Entry(K k, V v){
			this.key = k;
			this.value = v;
			next = null;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			this.value = value;
			return value;
		}

		@Override
		public String toString(){
			return key.toString() + "=" + value.toString();
		}

	}

	public static void main(String[] args) {
		SimpleHashMap<Integer, Integer> m16 = new SimpleHashMap<Integer, Integer>();

		for (int i = 0; i < 1000; i++) {
			m16.put(i, 0);
		}
		for (int i = 0; i < 1000; i++) {
			m16.remove(i);
		}
		for (int i = 0; i < 1000; i++) {
			m16.remove(i);
		}
		assertEquals("wrong size():", 0, m16.size());
		System.out.println(m16.size());
	}
}
