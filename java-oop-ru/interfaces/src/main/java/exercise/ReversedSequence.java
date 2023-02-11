package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {
    private final String s;

    public ReversedSequence(String s) {
        this.s = new StringBuilder(s).reverse().toString();
    }

    @Override
    public int length() {
        return s.length();
    }

    @Override
    public char charAt(int index) {
        return s.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return s.subSequence(start, end);
    }

    @Override
    public String toString() {
        return s;
    }
}
// END
