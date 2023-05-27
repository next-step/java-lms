package nextstep.courses.domain;

import nextstep.courses.fixtures.CourseFixtureBuilder;
import nextstep.courses.fixtures.SessionFixtureBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CourseTest {
    @Test
    @DisplayName("코스는 여러 개의 강의를 가진다.")
    void sessions() {
        // given
        Course course = new CourseFixtureBuilder().build();

        // when
        Session session1 = new SessionFixtureBuilder().withId(0L).build();
        Session session2 = new SessionFixtureBuilder().withId(1L).build();
        course.addSession(session1);
        course.addSession(session2);

        // then
        assertThat(course.getSessions()).containsExactlyInAnyOrder(session1, session2);
    }

    @Test
    @DisplayName("코스에 강의를 삭제할 수 있다.")
    void sessionDelete() {
        // given
        Course course = new CourseFixtureBuilder().build();

        // when
        Session session1 = new SessionFixtureBuilder().withId(0L).build();
        Session session2 = new SessionFixtureBuilder().withId(1L).build();
        course.addSession(session1);
        course.addSession(session2);
        course.deleteSession(session1);

        // then
        assertThat(course.getSessions()).containsExactly(session2);
    }

    @Test
    @DisplayName("코스에 동일한 강의를 중복 추가할 수 없다.")
    void sessionDuplicate() {
        // given
        Course course = new CourseFixtureBuilder().build();

        // when
        Session session1 = new SessionFixtureBuilder().withId(0L).build();
        Session session2 = new SessionFixtureBuilder().withId(0L).build();
        course.addSession(session1);

        // then
        assertThatThrownBy(() -> course.addSession(session2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 등록된 강의입니다.");
    }

}