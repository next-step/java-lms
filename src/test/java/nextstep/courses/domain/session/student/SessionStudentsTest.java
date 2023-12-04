package nextstep.courses.domain.session.student;

import nextstep.courses.domain.session.FreeSession;
import nextstep.courses.domain.session.Session;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static nextstep.users.domain.fixture.DomainFixture.*;
import static org.assertj.core.api.Assertions.*;

class SessionStudentsTest {

    @DisplayName("강의-학생을 추가한다.")
    @Test
    void add() {
        // given
        SessionStudents sessionStudents = new SessionStudents();
        Session session = new FreeSession();

        // when & then
        assertThat(sessionStudents.add(session, JAVAJIGI)).isTrue();
    }

    @DisplayName("강의 인원 제한 수를 인자로 받아 현재 수강인원을 초과하는 지 확인한다.")
    @ParameterizedTest
    @CsvSource({"3,false", "2,true"})
    void isOver(int limit, boolean expectedResult) {
        // given
        SessionStudents sessionStudents = new SessionStudents();
        Session session = new FreeSession();
        sessionStudents.add(session, JAVAJIGI);
        sessionStudents.add(session, SANJIGI);

        // when & then
        assertThat(sessionStudents.isOver(limit)).isEqualTo(expectedResult);
    }
}