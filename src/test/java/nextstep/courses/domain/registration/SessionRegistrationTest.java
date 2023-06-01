package nextstep.courses.domain.registration;

import nextstep.courses.DuplicateStudentRegisterException;
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

public class SessionRegistrationTest {

    private Students students;

    @BeforeEach
    void setUp() {
        students = new Students();
        students.addStudent(new Student(NsUserTest.JAVAJIGI.getId(), SessionTest.recruitStartSession.getId()));
    }
    @Test
    @DisplayName("수강 신청 가능 체크")
    void registerSession() {
        SessionRegistration sessionRegistration = new SessionRegistration(SessionState.RECRUIT_START, RegistrationOpenType.OPEN, 30);

        assertThatNoException().isThrownBy(() -> {
            sessionRegistration.register(new Student(NsUserTest.SANJIGI.getId(), SessionTest.recruitStartSession.getId()));
        });
    }

    @Test
    @DisplayName("수강 신청 인원 초과")
    void excessMaxUser() {
        SessionRegistration sessionRegistration = new SessionRegistration(SessionState.RECRUIT_START, RegistrationOpenType.OPEN, 1, students);
        assertThatThrownBy(() -> {
            sessionRegistration.register(new Student(NsUserTest.SANJIGI.getId(), SessionTest.recruitStartSession.getId()));
        }).isInstanceOf(StudentMaxException.class).hasMessageContaining("정원 초과하여 신청할 수 없습니다.");
    }

    @Test
    @DisplayName("중복 수강 신청 시도")
    void duplicateRegister() {
        Session session = createSession(2L, SessionCostType.FREE, RegistrationOpenType.OPEN, SessionState.RECRUIT_START,30);
        session.getSessionRegistration().register(new Student(NsUserTest.SANJIGI.getId(), session.getId()));

        assertThatThrownBy(() -> {
            session.getSessionRegistration().register(new Student(NsUserTest.SANJIGI.getId(), session.getId()));
        }).isInstanceOf(DuplicateStudentRegisterException.class).hasMessageContaining("중복 강의 수강은 불가합니다.");
    }

    @Test
    @DisplayName("수강 신청 불가 - 모집중 강의가 아닐 경우")
    void checkSessionState() {
        SessionRegistration sessionRegistration2 = new SessionRegistration(SessionState.RECRUIT_END, RegistrationOpenType.CLOSE,30);
        SessionRegistration sessionRegistration4 = new SessionRegistration(SessionState.SESSION_END, RegistrationOpenType.CLOSE,30);

        assertThatThrownBy(() -> {
            sessionRegistration2.register(new Student(NsUserTest.JAVAJIGI.getId(), SessionTest.recruitEndSession.getId()));
        }).isInstanceOf(SessionStateNotRecruitStartException.class).hasMessageContaining("비모집중인 강의입니다.");

        assertThatThrownBy(() -> {
            sessionRegistration4.register(new Student(NsUserTest.JAVAJIGI.getId(), SessionTest.sessionEndSession.getId()));
        }).isInstanceOf(SessionStateNotRecruitStartException.class).hasMessageContaining("비모집중인 강의입니다.");
    }

}
