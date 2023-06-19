package nextstep.session.domain.student;

class SessionStudentTest {
    public static final SessionStudent STUDENT_REQUEST = new SessionStudent(0L, 1L, 1L, SessionStudentStatus.REQUEST);
    public static final SessionStudent STUDENT_APPROVE = new SessionStudent(1L, 1L, 2L, SessionStudentStatus.APPROVE);
    public static final SessionStudent STUDENT_REFUSAL = new SessionStudent(2L, 1L, 2L, SessionStudentStatus.REFUSAL);
}
