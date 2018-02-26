package map;

import java.lang.reflect.ParameterizedType;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private Entry<K, V>[] table;
	private double loadFactor = 0.75;
	private int capacity;
	private int size;

	public SimpleHashMap() {
		table = (Entry<K, V>[]) new Entry[16];
		size = 0;
		capacity = 16;
	}

	public SimpleHashMap(int capacity) {
		table = (Entry<K, V>[]) new Entry[capacity];
		size = 0;
		this.capacity = capacity;
	}

	public String show() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < table.length; i++){
			sb.append(i);
			if(table[i] != null){
				Entry<K, V> current = table[i];
				while(current != null){
					sb.append(" ");
					sb.append(current.toString());
					sb.append(" ");
					current = current.next;
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	private int index(K key) {
	//	System.out.println(capacity);
		return Math.abs(key.hashCode() % capacity);
	}

	private Entry<K, V> find(int index, K key){
		if(index >= table.length) {
			return null;
		} else {
			Entry<K, V> temp = table[index];
			//Kanske bugg att använda != istället för compare eller compareTo i temp.key != key
			if(temp != null){
				while(temp != null && temp.key.equals(key)) {
					temp = temp.next;
				}
			}
			return temp;
		}
	}
	
	private void rehash() {
		Entry<K, V>[] oldTable = table;
		Entry<K, V>[] newTable = (Entry<K, V>[]) new Entry[(capacity*2)];
		capacity = capacity*2;
		for(int i = 0; i < oldTable.length; i++){
			if(oldTable[i] != null) {
				Entry<K, V> current = oldTable[i];
				while(current != null) {
					System.out.println("While 1");
					Entry<K, V> temp = current.next;
					int indexCurrent = index(current.getKey());  // Denna metod måste användas då metoden hittar index via capacity, som nu är dubblad.  
					if(newTable[indexCurrent] == null){
						current.next = null;
						newTable[indexCurrent] = current;
					} else {
						Entry<K, V> prevCurrent = newTable[indexCurrent];
						while(prevCurrent.next != null){
							System.out.println("While 2");
							prevCurrent = prevCurrent.next;
						}
						prevCurrent.next = current;
					}
					current = temp;
				}

			}
		}
		table = newTable;
	}
	
//	public static <K> MyObject<K> createMyObject(Class<K> type) {
//		return new MyObject<K>(type);
//	}


	@Override
	public V get(Object arg0) {
		int index = Math.abs(arg0.hashCode() % capacity);
		Entry<K, V> current = table[index];
		while(current != null) {
			if (arg0.equals(current.getKey())) {
				return current.getValue();
			}
			current = current.next;
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public V put(K arg0, V arg1) {
		if(size()%capacity >= capacity*3/4) {
//			System.out.println("prerehash");
//			System.out.print(show());
			rehash();
//			System.out.println("after rehash");
//			System.out.print(show());
		}

		int indexCurrent = index(arg0);
		Entry<K, V> old = find(indexCurrent, arg0);
		Entry<K, V> firstOnIndex = table[indexCurrent];

		if(old == null && firstOnIndex == null){
			table[indexCurrent] = new Entry<K, V>(arg0, arg1);
			size++;
			return null;
		} else if (old == null && firstOnIndex != null) {
			while(firstOnIndex.next != null){
				firstOnIndex = firstOnIndex.next;
			}
			firstOnIndex.next = new Entry<K, V>(arg0, arg1);
			size++;
			return null;
		} else{
			V temp = old.getValue();
			old.setValue(arg1);
			return temp;
		}
	}

	@Override
	public V remove(Object arg0) {
		int index = Math.abs(arg0.hashCode() % capacity);
		Entry<K, V> current = table[index];
		if(current == null) {
			return null;
		}else if(arg0.equals(current.getKey())) {
			V temp = current.getValue();
			table[index] = current.next;
			size = size - 1;
			return temp;
		} else {
			Entry<K, V> prev = current;
			current = current.next;
			while(current != null) {
				if (arg0.equals(current.getKey())) {
					V temp = current.getValue();
					prev.next = current.next;	
					size = size - 1;
					return temp;
				}
				current = current.next;
				prev = prev.next;
			}
			return null;
		}		
	}

	@Override
	public int size() {
		return size;
	}



	private static class Entry<K, V> implements Map.Entry<K, V> {
		private K key;
		private V value;
		private Entry<K, V> next;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
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
			V oldValue = this.value;
			this.value = value;
			return oldValue;
		}

		@Override
		public String toString() {
			return getKey().toString() + " = " + getValue().toString();
		}

	}

//	public class GenericClass<K> {
//
//		private final Class<K> type;
//
//		@SuppressWarnings("unchecked")
//		public GenericClass() {
//			this.type = (Class<K>) ((ParameterizedType) getClass()
//						.getGenericSuperclass()).getActualTypeArguments()[0];
//		}
//
//		public Class<K> getMyType() {
//			return this.type;
//		}
//
//	}



	public static void main(String[] args) {
		SimpleHashMap<Integer, Integer> test = new SimpleHashMap<Integer, Integer>(4);
		//System.out.println("\n" + test.size());
		test.put(1, 1);
		test.put(2, 2);
		test.put(3, 3);
		test.put(4, 4);
		System.out.println("put after rehash \n" + test.show());

	}
}
