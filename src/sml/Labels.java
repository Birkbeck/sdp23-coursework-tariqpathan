package sml;

import sml.instruction.JnzInstruction;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

// TODO: write a JavaDoc for the class

/**
 *
 * @author ...
 */
public final class Labels {
	private final Map<String, Integer> labels = new HashMap<>();

	/**
	 * Adds a label with the associated address to the map.
	 *
	 * @param label the label
	 * @param address the address the label refers to
	 */
	public void addLabel(String label, int address) {
		Objects.requireNonNull(label);
		// TODO: Add a check that there are no label duplicates.
		if (labels.containsKey(label)) {
			throw new IllegalArgumentException(
				label + " already exists and has an address of " + labels.get(label));
		}
		labels.put(label, address);
	}

	/**
	 * Returns the address associated with the label.
	 *
	 * @param label the label
	 * @return the address the label refers to
	 */
	public int getAddress(String label) {
		if (!labels.containsKey(label)) throw new NullPointerException("no label exists");

		// TODO: Where can NullPointerException be thrown here?
		//       A NullPointerException can be thrown when the application attempts to reference
		//		a value that doesn't exist in memory. Here it will be thrown if we try to
		//		do a labels.get(label) for a label that does not exist.
		//       Add code to deal with non-existent labels.
		return labels.get(label);
	}

	/**
	 * representation of this instance,
	 * in the form "[label -> address, label -> address, ..., label -> address]"
	 *
	 * @return the string representation of the labels map
	 */
	@Override
	public String toString() {
		// TODO: Implement the method using the Stream API (see also class Registers). Attempted
		return labels.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.map(e -> e.getKey() + " -> " + e.getValue())
				.collect(Collectors.joining(", ", "[", "]"));
	}

	// TODO: Implement equals and hashCode (needed in class Machine). Check
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		// fully compares fields
		if (o instanceof Labels other) {
			return labels.equals(other.labels);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return labels.hashCode();
	}

	/**
	 * Removes the labels
	 */
	public void reset() {
		labels.clear();
	}

	//TODO: remove main method
	public static void main(String[] args) {
		Labels l = new Labels();
		l.addLabel("one", 1);
		l.addLabel("two", 2);
//		l.getAddress("three");

		Labels m = new Labels();
		m.addLabel("two", 1);
		m.addLabel("one", 2);
//		m.addLabel("one", 3);
		Object p = new Object();
		System.out.println("with hashmap: " + l.equals(m));
		System.out.println("with o: " + l.equals(p));
		System.out.println(l.toString());
//		l.getAddress("three");
	}

}
