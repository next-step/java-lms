package nextstep.courses.domain;

import nextstep.courses.SessionStateNotRecruitStartException;
import nextstep.courses.StudentMaxException;
import nextstep.users.domain.StudentTest;
import nextstep.users.domain.StudentsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SessionTest {
    public static Session s1 = createSession(Cost.FREE, State.READY, 30);
    public static Session s2 = createSession(Cost.FREE, State.RECRUIT_START, 1);
    public static Session s3 = createSession(Cost.FREE, State.RECRUIT_END, 30);
    public static Session s4 = createSession(Cost.FREE, State.SESSION_START, 30);
    public static Session s5 = createSession(Cost.FREE, State.SESSION_END, 30);

    @Test
    @DisplayName("학생 등록")
    void addStudent() {
        Session session = createSession(Cost.FREE, State.RECRUIT_START, 30);
        assertThat(session.enroll(StudentTest.student1)).isEqualTo(StudentsTest.students);
    }

    @Test
    @DisplayName("학생 정원 초과")
    void addStudent_maxUserException() {
        s2.enroll(StudentTest.student2);
        assertThatThrownBy(() -> {
            s2.enroll(StudentTest.student1);
        }).isInstanceOf(StudentMaxException.class).hasMessageContaining("정원 초과하여 신청할 수 없습니다.");
    }

    @Test
    @DisplayName("모집중이 아닌 경우 - 준비중")
    void addStudent_stateREADYException() {
        assertThatThrownBy(() -> {
            s1.enroll(StudentTest.student1);
        }).isInstanceOf(SessionStateNotRecruitStartException.class).hasMessageContaining("준비중인 강의입니다.");
    }

    @Test
    @DisplayName("모집중이 아닌 경우 - 종료")
    void addStudent_stateRECRUIT_ENDException() {
        assertThatThrownBy(() -> {
            s3.enroll(StudentTest.student1);
        }).isInstanceOf(SessionStateNotRecruitStartException.class).hasMessageContaining("모집종료인 강의입니다.");
    }

    @Test
    @DisplayName("모집중이 아닌 경우 - 강의 시작")
    void addStudent_stateSESSION_STARTException() {
        assertThatThrownBy(() -> {
            s4.enroll(StudentTest.student1);
        }).isInstanceOf(SessionStateNotRecruitStartException.class).hasMessageContaining("강의중인 강의입니다.");
    }

    @Test
    @DisplayName("모집중이 아닌 경우 - 종료")
    void addStudent_stateSESSION_ENDException() {
        assertThatThrownBy(() -> {
            s5.enroll(StudentTest.student1);
        }).isInstanceOf(SessionStateNotRecruitStartException.class).hasMessageContaining("강의종료인 강의입니다.");
    }

    static Session createSession(Cost cost, State state, int maxUser) {
        return Session.of("title", "cover", 1, cost, state, maxUser);
    }
}
