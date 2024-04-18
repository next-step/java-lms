package nextstep.courses.domain;

import nextstep.courses.domain.sessionPolicy.SessionPolicyStrategy;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.User;

public class Enrollment {
    private static final String NOT_FOUND_SESSION_ERROR_MESSAGE =
            "수강 신청이 허용되지 않습니다. 결제 금액이 세션 가격과 일치하지 않습니다.";
    public static final String MAX_CAPACITY_ERROR_MESSAGE = "강의 최대 수강 인원을 초과하였습니다.";
    public static final String ENROLLMENT_ERROR_MESSAGE = "수강 신청 상태가 아닙니다.";
    private final SessionStatus sessionStatus;
    private final Students students;
    private final SessionPolicyStrategy sessionTypeStrategy;

    public Enrollment(SessionStatus sessionStatus, SessionPolicyStrategy sessionTypeStrategy) {
        this(sessionStatus, new Students(), sessionTypeStrategy);
    }

    public Enrollment(SessionStatus sessionStatus, Students students, SessionPolicyStrategy sessionTypeStrategy) {
        if (isOverStudents(sessionTypeStrategy.getCapacity(), students)) {
            throw new IllegalArgumentException(MAX_CAPACITY_ERROR_MESSAGE);
        }
        this.sessionStatus = sessionStatus;
        this.students = students;
        this.sessionTypeStrategy = sessionTypeStrategy;
    }

    private static boolean isOverStudents(int capacity, Students students) {
        return capacity < students.count();
    }

    public void enroll(User student, Payment payment) {
        if (!sessionStatus.isEnrolling()) {
            throw new IllegalArgumentException(ENROLLMENT_ERROR_MESSAGE);
        }

        if (isFullStudent()) {
            throw new IllegalArgumentException(MAX_CAPACITY_ERROR_MESSAGE);
        }
        if (!sessionTypeStrategy.isCompletePay(payment)) {
            throw new IllegalArgumentException(NOT_FOUND_SESSION_ERROR_MESSAGE);
        }
        students.enroll(student);
    }

    private boolean isFullStudent() {
        return sessionTypeStrategy.getCapacity() == countEnrolledStudent();
    }

    public int countEnrolledStudent() {
        return students.count();
    }
}
