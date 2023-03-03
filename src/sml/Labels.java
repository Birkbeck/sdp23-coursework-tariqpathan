package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Used to lookup the program index for a label.
 * The address corresponds to the index for that instruction, stored in the program field of a machine instance.
 * This is required for jumping instructions.
 *
 * Notes: Labels are checked for uniqueness and cannot be null.
 * Labels cannot be checked if they refer to a valid program index - this is done at execution time.
 *
 * @author Tariq Pathan
 */
public final class Labels {
	private final Map<String, Integer> labels = new HashMap<>();

	/**
	 * Adds a label with the associated address to the map.
	 *
	 * @param label the label
	 * @param address the address the label refers to in the program ArrayList of Instructions.
	 * @throws IllegalArgumentException thrown if label is not unique
	 */
	public void addLabel(String label, int address) throws IllegalArgumentException{
		Objects.requireNonNull(label);
		if (labels.containsKey(label)) {
			throw new IllegalArgumentException(label);
		}
		labels.put(label, address);
	}

	/**
	 * Returns the address associated with the label.
	 *
	 * @param label the label
	 * @return the address the label refers to, to be looked up in program ArrayList
	 * @throws NullPointerException if a matching key is not found.
	 */
	public int getAddress(String label) {
		if (!labels.containsKey(label)) throw new NullPointerException("no label exists");

		// TODO: Where can NullPointerException be thrown here?
		//       A NullPointerException can be thrown when the application attempts to reference
		//		a value that doesn't exist in memory. Here it will be thrown if we try to
		//		do a labels.get(label) for a label that does not exist.
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
		return labels.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.map(e -> e.getKey() + " -> " + e.getValue())
				.collect(Collectors.joining(", ", "[", "]"));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
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
}
