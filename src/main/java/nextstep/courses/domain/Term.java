package nextstep.courses.domain;

public class Term {

    public static final String TERM_SUFFIX = "기";

    private final int  number;

    public Term(int number) {
        isPositive(number);
        this.number = number;
    }

    private void isPositive(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("the term is must be greater than zero");
        }
    }

    public String ternValue() {
        return String.format("%d%s", number, TERM_SUFFIX);
    }
}
