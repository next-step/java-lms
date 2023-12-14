package nextstep.sessions.domain;

public interface SessionType {

    boolean isPossibleToRegister(Long paidAmount, int enrolledStudents);
}
