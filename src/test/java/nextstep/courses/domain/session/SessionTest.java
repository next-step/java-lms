package nextstep.courses.domain.session;

import nextstep.courses.SessionStateNotRecruitStartException;
import nextstep.courses.StudentMaxException;
import nextstep.courses.domain.registration.EnrollmentOpenType;
import nextstep.users.domain.StudentTest;
import nextstep.users.domain.StudentsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class SessionTest {
    public static Session readySession = createSession(1L, SessionCostType.FREE, EnrollmentOpenType.CLOSE, SessionState.READY, 30);
    public static Session recruitStartSession = createSession(2L, SessionCostType.FREE, EnrollmentOpenType.OPEN, SessionState.RECRUIT_START, 1);
    public static Session recruitEndSession = createSession(3L, SessionCostType.FREE, EnrollmentOpenType.CLOSE, SessionState.RECRUIT_END, 30);
    public static Session sessionStartSession = createSession(4L, SessionCostType.FREE, EnrollmentOpenType.OPEN, SessionState.SESSION_START, 30);
    public static Session sessionEndSession = createSession(5L, SessionCostType.FREE, EnrollmentOpenType.CLOSE, SessionState.SESSION_END, 30);

    @Test
    @DisplayName("학생 등록")
    void addStudent() {
        Session session = createSession(6L, SessionCostType.FREE, EnrollmentOpenType.OPEN, SessionState.RECRUIT_START, 30);
        assertThat(session.enroll(StudentTest.student1)).isEqualTo(StudentsTest.students);
    }

    @Test
    @DisplayName("학생 정원 초과")
    void addStudent_maxUserException() {
        recruitStartSession.enroll(StudentTest.student2);
        assertThatThrownBy(() -> {
            recruitStartSession.enroll(StudentTest.student1);
        }).isInstanceOf(StudentMaxException.class).hasMessageContaining("정원 초과하여 신청할 수 없습니다.");
    }

    @Test
    @DisplayName("모집중이 아닌 경우 - 준비중")
    void addStudent_stateREADYException() {
        assertThatThrownBy(() -> {
            readySession.enroll(StudentTest.student1);
        }).isInstanceOf(SessionStateNotRecruitStartException.class).hasMessageContaining("비모집중인 강의입니다.");
    }

    @Test
    @DisplayName("모집중이 아닌 경우 - 종료")
    void addStudent_stateRECRUIT_ENDException() {
        assertThatThrownBy(() -> {
            recruitEndSession.enroll(StudentTest.student1);
        }).isInstanceOf(SessionStateNotRecruitStartException.class).hasMessageContaining("비모집중인 강의입니다.");
    }

    @Test
    @DisplayName("모집중이 아닌 경우 - 종료")
    void addStudent_stateSESSION_ENDException() {
        assertThatThrownBy(() -> {
            sessionEndSession.enroll(StudentTest.student1);
        }).isInstanceOf(SessionStateNotRecruitStartException.class).hasMessageContaining("비모집중인 강의입니다.");
    }

    public static Session createSession(Long id, SessionCostType sessionCostType, EnrollmentOpenType enrollmentOpenType, SessionState sessionState, int maxUser) {
        return Session.of(id, 1L, "title", "cover", 1, sessionCostType, enrollmentOpenType, sessionState, maxUser
                , LocalDateTime.of(2023, 6, 1, 14, 0, 0)
                , LocalDateTime.of(2023, 6, 30, 14, 0, 0));
    }
}
