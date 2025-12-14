package nextstep.courses.domain;

public interface EnrollmentRule {
    void validate(int money, int enrolledCount);

    void validateMoney(Money money);

    void validateCapacity(int enrolledCount);

    SessionType getType();
}
