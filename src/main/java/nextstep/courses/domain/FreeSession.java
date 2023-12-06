package nextstep.courses.domain;

public class FreeSession implements SessionType{

    public FreeSession() {
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
