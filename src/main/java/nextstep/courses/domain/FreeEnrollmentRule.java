package nextstep.courses.domain;

public class FreeEnrollmentRule implements EnrollmentRule {

    public FreeEnrollmentRule() {

    }

    @Override
    public void validate(Money money, int enrolledCount) {
        // 무료 강의는 아무 제한 없음
    }

    @Override
    public void validateMoney(Money money) {

    }

    @Override
    public void validateCapacity(int enrolledCount) {

    }

    @Override
    public SessionType getType() {
        return SessionType.FREE;
    }
}
