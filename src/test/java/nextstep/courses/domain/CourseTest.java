package nextstep.courses.domain;

import nextstep.fixture.TestFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class CourseTest {

    @DisplayName("과정(Course)은 기수 단위로 여러 개의 강의(Session)를 가질 수 있다")
    @Test
    public void courseHasSessions() {
        //given
        Course course = TestFixture.K8S_COURSE;
        Term term = TestFixture.TERM16;
        Session session1 = TestFixture.LEMON_SESSION;
        Session session2 = TestFixture.MINT_SESSION;
        Session session3 = TestFixture.LIME_SESSION;
        //when
        term.addSessions(session1, session2);
        course.establishTerm(term);
        //then
        assertAll("과정(Course) 여러 개의 강의(Session)를 갖는다",
                () -> assertThat(course.includeSession(session1))
                        .as("")
                        .isTrue(),
                () -> assertThat(course.includeSession(session2))
                        .as("")
                        .isTrue(),
                () -> assertThat(course.includeSession(session3))
                        .as("")
                        .isFalse()
        );
    }
}