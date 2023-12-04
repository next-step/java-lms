package nextstep.courses.domain.session.student;

import nextstep.courses.domain.session.FreeSession;
import nextstep.courses.domain.session.Session;
import nextstep.courses.exception.NotRegisterSession;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.users.domain.fixture.DomainFixture.*;
import static org.assertj.core.api.Assertions.*;

class SessionStudentsTest {

    @DisplayName("강의-학생을 추가한다.")
    @Test
    void add() throws NotRegisterSession {
        // given
        SessionStudents sessionStudents = new SessionStudents();
        Session session = new FreeSession();
        SessionStudent sessionStudent = new SessionStudent(session, JAVAJIGI);

        // when & then
        assertThat(sessionStudents.add(sessionStudent)).isTrue();
    }

    @DisplayName("수강 인원을 추가하기 전에 해당 학생이 이미 수강신청이 된 상태이면 예외를 발생시킨다.")
    @Test
    void validateDuplicate() throws NotRegisterSession {
        // given
        SessionStudents sessionStudents = new SessionStudents();
        Session session = new FreeSession();
        SessionStudent sessionStudent = new SessionStudent(session, JAVAJIGI);
        sessionStudents.add(sessionStudent);

        // when & then
        assertThatThrownBy(() -> sessionStudents.add(sessionStudent)).isInstanceOf(NotRegisterSession.class)
            .hasMessage("이미 해당 강의를 수강 중 입니다.");
    }

    @DisplayName("강의 인원 제한 수를 인자로 받아 현재 수강인원을 초과하면 예외를 발생시킨다.")
    @Test
    void validateLimit() throws NotRegisterSession {
        // given
        SessionStudents sessionStudents = new SessionStudents();
        Session session = new FreeSession();
        
        SessionStudent sessionStudent1 = new SessionStudent(session, JAVAJIGI);
        sessionStudents.add(sessionStudent1);
        SessionStudent sessionStudent2 = new SessionStudent(session, SANJIGI);
        sessionStudents.add(sessionStudent2);

        // when & then
        assertThatThrownBy(() -> sessionStudents.validateLimit(2)).isInstanceOf(NotRegisterSession.class)
                .hasMessage("현재 수강 가능한 모든 인원수가 채워졌습니다.");
    }
}