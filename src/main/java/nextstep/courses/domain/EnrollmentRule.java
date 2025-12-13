package nextstep.courses.domain;

public interface EnrollmentRule {
    void validate(int money, int enrolledCount);
    SessionType getType();
}
