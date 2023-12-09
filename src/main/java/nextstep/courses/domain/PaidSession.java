package nextstep.courses.domain;

public class PaidSession implements SessionType{

    private final Long sessionTypeId;
    private final Integer maxStudents;
    private final Integer sessionFee;

    public PaidSession(Long sessionTypeId, Integer maxStudents, Integer sessionFee) {
        validatePaidValue(maxStudents, sessionFee);
        this.sessionTypeId = sessionTypeId;
        this.maxStudents = maxStudents;
        this.sessionFee = sessionFee;
    }

    private void validatePaidValue(Integer maxStudents, Integer sessionFee) {
        validateMaxStudents(maxStudents);
        validateSessionFee(sessionFee);
    }

    private void validateMaxStudents(Integer maxStudents) {
        if (maxStudents < 0) {
            throw new IllegalArgumentException("최대 수강 인원은 0명 이상이어야 합니다");
        }
    }

    private void validateSessionFee(Integer sessionFee) {
        if (sessionFee < 0) {
            throw new IllegalArgumentException("수강료는 0원 이상이어야 합니다");
        }
    }

    @Override
    public boolean isWithinCapacity(Integer size) {
        return size < maxStudents;
    }

    @Override
    public boolean checkSessionFeeEquality(Integer sessionFee) {
        return this.sessionFee.equals(sessionFee);
    }
}
