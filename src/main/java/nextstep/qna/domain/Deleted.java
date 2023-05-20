package nextstep.qna.domain;

public enum Deleted {
    TRUE(true), FALSE(false);

    private final boolean value;

    Deleted(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}
