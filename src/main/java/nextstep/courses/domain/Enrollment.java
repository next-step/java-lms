package nextstep.courses.domain;

import java.util.Objects;

public class Enrollment {
    private final Long studentId;
    private final Long sessionId;
    private final Money money;

    public Enrollment(Long studentId, Long sessionId, int money) {
        this(studentId, sessionId, new Money(money));
    }

    public Enrollment(Long studentId, Long sessionId, Money money) {
        this.studentId = studentId;
        this.sessionId = sessionId;
        this.money = money;
    }

    public Money getMoney() {
        return money;
    }

    public boolean sameSession(Long id) {
        return this.sessionId.equals(id);
    }

    public void validateBelongsTo(Session session) {
        if (!sameSession(session.getId())) {
            throw new IllegalArgumentException("신청하고자 하는 강의가 아닙니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(studentId, that.studentId) && Objects.equals(sessionId, that.sessionId) && Objects.equals(money, that.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, sessionId, money);
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "studentId=" + studentId +
                ", sessionId=" + sessionId +
                ", money=" + money +
                '}';
    }
}
