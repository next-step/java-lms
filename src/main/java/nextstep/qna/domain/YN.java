package nextstep.qna.domain;

public enum YN {
    Y, N;

    public boolean toBoolean() {
        return this == Y;
    }

    public static YN of(boolean yn) {
        return yn ? Y : N;
    }

    public static YN of(Boolean yn, YN defaultValue) {
        return yn != null ? of(yn) : defaultValue;
    }
}
