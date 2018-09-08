package com.mybatis.page.xmlop;

import java.util.HashMap;
import java.util.Map;

public class StrictMap<T> extends HashMap<String, T> {

	private static final long serialVersionUID = -4950446264854982944L;
	private final String name;

	public StrictMap(String name, int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
		this.name = name;
	}

	public StrictMap(String name, int initialCapacity) {
		super(initialCapacity);
		this.name = name;
	}

	public StrictMap(String name) {
		super();
		this.name = name;
	}

	public StrictMap(String name, Map<String, ? extends T> m) {
		super(m);
		this.name = name;
	}

	@SuppressWarnings("unchecked")
	public T put(String key, T value) {
		if (containsKey(key)) {
			remove(key);
			// throw new IllegalArgumentException(name + " already contains
			// value for " + key);
		}
		if (key.contains(".")) {
			final String shortKey = getShortName(key);
			if (super.get(shortKey) == null) {
				super.put(shortKey, value);
			} else {
				super.put(shortKey, (T) new Ambiguity(shortKey));
			}
		}
		return super.put(key, value);
	}

	public T get(Object key) {
		T value = super.get(key);
		if (value == null) {
			throw new IllegalArgumentException(name + " does not contain value for " + key);
		}
		if (value instanceof Ambiguity) {
			throw new IllegalArgumentException(((Ambiguity) value).getSubject() + " is ambiguous in " + name
					+ " (try using the full name including the namespace, or rename one of the entries)");
		}
		return value;
	}

	private String getShortName(String key) {
		final String[] keyParts = key.split("\\.");
		return keyParts[keyParts.length - 1];
	}

	protected static class Ambiguity {
		final private String subject;

		public Ambiguity(String subject) {
			this.subject = subject;
		}

		public String getSubject() {
			return subject;
		}
	}

}
