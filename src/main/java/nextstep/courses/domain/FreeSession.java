package nextstep.courses.domain;

public class FreeSession implements SessionType{

    private final Long sessionTypeId;

    public FreeSession(Long sessionTypeId) {
        this.sessionTypeId = sessionTypeId;
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
