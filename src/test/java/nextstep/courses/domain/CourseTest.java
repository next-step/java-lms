package nextstep.courses.domain;

import nextstep.fixture.TestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class CourseTest {

    @BeforeEach
    public void setUp() {
        TestFixture.fixtureInit();
    }

    @DisplayName("과정(Course)은 기수 단위로 여러 개의 강의(Session)를 가질 수 있다")
    @Test
    public void courseHasSessions() {
        //given
        Course course = TestFixture.K8S_COURSE;
        Session session1 = TestFixture.LEMON_SESSION;
        Session session2 = TestFixture.MINT_SESSION;
        Session session3 = TestFixture.LIME_SESSION;
        //when
        course.addSessions(session1, session2);
        //then
        assertAll("과정(Course) 여러 개의 강의(Session)를 갖는다",
                () -> assertThat(course.isIncludeSession(session1))
                        .as("")
                        .isTrue(),
                () -> assertThat(course.isIncludeSession(session2))
                        .as("")
                        .isTrue(),
                () -> assertThat(course.isIncludeSession(session3))
                        .as("")
                        .isFalse()
        );
    }
}