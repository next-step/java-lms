package nextstep.courses.domain;

public class FreeEnrollmentRule implements EnrollmentRule {
    @Override
    public void validate(int money, int enrolledCount) {
        // 무료 강의는 아무 제한 없음
    }

    @Override
    public SessionType getType() {
        return SessionType.FREE;
    }
}
