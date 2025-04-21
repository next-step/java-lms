package nextstep.courses.domain.session.policy;

import lombok.Getter;
import nextstep.courses.domain.session.constraint.SessionConstraint;

import java.util.stream.Stream;

@Getter
public enum SessionType {
    FREE((sessionConstraint, payments, payment) -> true, "free"),
    PAID(
        (sessionConstraint, enrollmentCount, amount) ->
            sessionConstraint.isGreaterThenCapacity(enrollmentCount) && sessionConstraint.isSameFee(amount),
        "paid"
    );

    private final EnrollStrategy enrollStrategy;
    private final String type;

    SessionType(EnrollStrategy enrollStrategy, String type) {
        this.enrollStrategy = enrollStrategy;
        this.type = type;
    }

    public static SessionType fromString(String type) {
        return Stream.of(SessionType.values())
            .filter(value -> value.type.equalsIgnoreCase(type))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(String.format("'%s'은(는) 유효한 세션 유형이 아닙니다.", type)));
    }

    public boolean canEnroll(SessionConstraint sessionConstraint, int enrollmentCount, long amount) {
        return enrollStrategy.canEnroll(sessionConstraint, enrollmentCount, amount);
    }
}
