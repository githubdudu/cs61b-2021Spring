package A2;

public interface NullSafeStringComparator {
    /** Returns a negative number if s1 is "less than" s2, 0 if "equal",
     * and a positive number otherwise. Null is considered less than
     * any String. If both inputs are null, return 0.
     */
    public int compare(String s1, String s2);
}
