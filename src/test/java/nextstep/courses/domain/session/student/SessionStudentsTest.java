package nextstep.courses.domain.session.student;

import nextstep.courses.domain.session.FreeSession;
import nextstep.courses.domain.session.Session;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.users.domain.fixture.DomainFixture.*;

class SessionStudentsTest {

    @DisplayName("강의-학생을 추가한다.")
    @Test
    void add() {
        // given
        SessionStudents sessionStudents = new SessionStudents();
        Session session = new FreeSession();

        // when & then
        Assertions.assertThat(sessionStudents.add(session, JAVAJIGI)).isTrue();
    }
}