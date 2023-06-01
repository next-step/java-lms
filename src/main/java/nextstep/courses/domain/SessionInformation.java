package nextstep.courses.domain;

public class SessionInformation {

    private static final String TERM_SUFFIX = "기";
    private static final String BLANK = " ";

    private final String name;
    private final int term;

    public SessionInformation(String name, int term) {
        validateTerm(term);
        this.name = name;
        this.term = term;
    }

    public String fetchTitle() {
        return name + BLANK + term + TERM_SUFFIX;
    }

    private void validateTerm(Integer term) {
        if (term <= 0) {
            throw new IllegalArgumentException("the term is must be greater than zero");
        }
    }


}
