package nextstep.courses.domain.session.student;

import nextstep.courses.domain.session.FreeSession;
import nextstep.courses.domain.session.Session;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.users.domain.fixture.DomainFixture.*;
import static org.assertj.core.api.Assertions.*;

class StudentsTest {

    @DisplayName("강의-학생을 추가한다.")
    @Test
    void add() {
        // given
        Students students = new Students();

        // when & then
        assertThat(students.add(JAVAJIGI)).isTrue();
    }

    @DisplayName("수강 인원을 추가하기 전에 해당 학생이 이미 수강신청이 된 상태이면 예외를 발생시킨다.")
    @Test
    void validateDuplicate() {
        // given
        Students students = new Students();
        Session session = new FreeSession();
        students.add(JAVAJIGI);

        // when & then
        assertThatThrownBy(() -> students.add(JAVAJIGI)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이미 해당 강의를 수강 중 입니다.");
    }
}