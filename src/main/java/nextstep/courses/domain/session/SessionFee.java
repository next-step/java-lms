package nextstep.courses.domain.session;

public class SessionFee {

    private final long value;

    public SessionFee(long value) {
        this.value = value;

    }

    public boolean isFree() {
        return value == 0;
    }

    public long value() {
        return value;
    }
}
