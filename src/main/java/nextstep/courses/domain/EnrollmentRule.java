package nextstep.courses.domain;

public interface EnrollmentRule {
    void validate(Money money, int enrolledCount);

    void validateMoney(Money money);

    void validateCapacity(int enrolledCount);

    SessionType getType();
}
