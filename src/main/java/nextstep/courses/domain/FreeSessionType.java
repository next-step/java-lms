package nextstep.courses.domain;

public class FreeSessionType implements SessionType {

    private static final int ZERO = 0;

    private final int pirce;

    public FreeSessionType() {
        this.pirce = ZERO;
    }

    public FreeSessionType registered() {
        return new FreeSessionType();
    }

}
