package nextstep.courses.domain;

public class SessionEnrollment {

    private SessionStatus status;

    private SessionType type;

    private Fee fee;

    private Integer maxStudents;

    public SessionEnrollment() {
    }

    public SessionEnrollment(SessionStatus status, SessionType type, Fee fee, Integer maxStudents) {
        validateSession(type, fee, maxStudents);
        this.status = status;
        this.type = type;
        this.fee = fee;
        this.maxStudents = maxStudents;
    }

    private void validateSession(SessionType type, Fee fee, Integer maxStudents) {
        if (type == SessionType.PAID && fee.getAmount() <= 0) {
            throw new IllegalArgumentException("유료 강의는 강의 금액이 0원 이상입니다.");
        }

        if (type == SessionType.PAID && maxStudents <= 0) {
            throw new IllegalArgumentException("수강 가능한 인원은 0명 이상이어야 합니다.");
        }

        if (type == SessionType.FREE && fee.getAmount() > 0) {
            throw new IllegalArgumentException("무료 강의는 강의 금액이 있을 수 없습니다.");
        }

        if (type == SessionType.FREE && maxStudents != 0) {
            throw new IllegalArgumentException("무료 강의는 최대 수강 인원 제한이 없습니다.");
        }
    }
}
