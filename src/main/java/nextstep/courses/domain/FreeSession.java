package nextstep.courses.domain;

public class FreeSession extends SessionType{

    public FreeSession() {
        super();
    }

    @Override
    public boolean isWithinCapacity(Integer size) {
        return true;
    }

    @Override
    public boolean checkSessionFeeEquality(Integer sessionFee) {
        return true;
    }
}
