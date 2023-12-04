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

        // when & then
        assertThat(sessionStudents.add(session, JAVAJIGI)).isTrue();
    }

    @DisplayName("수강 인원을 추가하기 전에 해당 학생이 이미 수강신청이 된 상태이면 예외를 발생시킨다.")
    @Test
    void validateDuplicate() throws NotRegisterSession {
        // given
        SessionStudents sessionStudents = new SessionStudents();
        Session session = new FreeSession();
        sessionStudents.add(session, JAVAJIGI);

        // when & then
        assertThatThrownBy(() -> sessionStudents.add(session, JAVAJIGI)).isInstanceOf(NotRegisterSession.class)
            .hasMessage("name 학생은 이미 해당 강의 수강 중 입니다.");
    }

    @DisplayName("강의 인원 제한 수를 인자로 받아 현재 수강인원을 초과하면 예외를 발생시킨다.")
    @Test
    void validateLimit() throws NotRegisterSession {
        // given
        SessionStudents sessionStudents = new SessionStudents();
        Session session = new FreeSession();
        sessionStudents.add(session, JAVAJIGI);
        sessionStudents.add(session, SANJIGI);

        // when & then
        assertThatThrownBy(() -> sessionStudents.validateLimit(2)).isInstanceOf(NotRegisterSession.class)
                .hasMessage("현재 수강 가능한 모든 인원수가 채워졌습니다.");
    }
}