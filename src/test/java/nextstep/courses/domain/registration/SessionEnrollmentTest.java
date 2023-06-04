package nextstep.courses.domain.registration;

import nextstep.courses.DuplicateStudentEnrollException;
import nextstep.courses.SessionStateNotRecruitStartException;
import nextstep.courses.StudentMaxException;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionCostType;
import nextstep.courses.domain.session.SessionState;
import nextstep.courses.domain.session.SessionTest;
import nextstep.users.domain.NsUserTest;
import nextstep.users.domain.Student;
import nextstep.users.domain.Students;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.session.SessionTest.createSession;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionEnrollmentTest {

    private Students students;

    @BeforeEach
    void setUp() {
        students = new Students();
        students.addStudent(new Student(NsUserTest.JAVAJIGI.getId(), SessionTest.recruitStartSession.getId()));
    }
    @Test
    @DisplayName("수강 신청 가능 체크")
    void registerSession() {
        SessionEnrollment sessionEnrollment = new SessionEnrollment(SessionState.RECRUIT_START, EnrollmentOpenType.OPEN, 30);

        assertThatNoException().isThrownBy(() -> {
            sessionEnrollment.enroll(new Student(NsUserTest.SANJIGI.getId(), SessionTest.recruitStartSession.getId()));
        });
    }

    @Test
    @DisplayName("수강 신청 인원 초과")
    void excessMaxUser() {
        SessionEnrollment sessionEnrollment = new SessionEnrollment(SessionState.RECRUIT_START, EnrollmentOpenType.OPEN, 1, students);
        assertThatThrownBy(() -> {
            sessionEnrollment.enroll(new Student(NsUserTest.SANJIGI.getId(), SessionTest.recruitStartSession.getId()));
        }).isInstanceOf(StudentMaxException.class).hasMessageContaining("정원 초과하여 신청할 수 없습니다.");
    }

    @Test
    @DisplayName("중복 수강 신청 시도")
    void duplicateenroll() {
        Session session = createSession(2L, SessionCostType.FREE, EnrollmentOpenType.OPEN, SessionState.RECRUIT_START,30);
        session.getSessionEnrollment().enroll(new Student(NsUserTest.SANJIGI.getId(), session.getId()));

        assertThatThrownBy(() -> {
            session.getSessionEnrollment().enroll(new Student(NsUserTest.SANJIGI.getId(), session.getId()));
        }).isInstanceOf(DuplicateStudentEnrollException.class).hasMessageContaining("중복 강의 수강은 불가합니다.");
    }

    @Test
    @DisplayName("수강 신청 불가 - 모집중 강의가 아닐 경우")
    void checkSessionState() {
        SessionEnrollment sessionEnrollment2 = new SessionEnrollment(SessionState.RECRUIT_END, EnrollmentOpenType.CLOSE,30);
        SessionEnrollment sessionEnrollment4 = new SessionEnrollment(SessionState.SESSION_END, EnrollmentOpenType.CLOSE,30);

        assertThatThrownBy(() -> {
            sessionEnrollment2.enroll(new Student(NsUserTest.JAVAJIGI.getId(), SessionTest.recruitEndSession.getId()));
        }).isInstanceOf(SessionStateNotRecruitStartException.class).hasMessageContaining("비모집중인 강의입니다.");

        assertThatThrownBy(() -> {
            sessionEnrollment4.enroll(new Student(NsUserTest.JAVAJIGI.getId(), SessionTest.sessionEndSession.getId()));
        }).isInstanceOf(SessionStateNotRecruitStartException.class).hasMessageContaining("비모집중인 강의입니다.");
    }

}
