package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CourseTest {

    private Course course;

    @BeforeEach
    void setUp() {
        //given
        course = new Course("객체지향 프로그래밍", 1L);
    }

    @Test
    void test_과정을_만들면_강의리스트가_생성된다(){
        // when
        Sessions sessions = course.getSessions();

        // then
        assertThat(sessions).isNotNull();
    }

    @Test
    void test_과정을_만들면_강의리스트가_비어있다(){
        // when
        Sessions sessions = course.getSessions();

        // then
        assertThat(sessions.isEmpty()).isTrue();
    }

    @Test
    void test_과정에_강의를_등록할_수_있다() {
        // given
        Session session = new Session(1L);

        // when
        course.addSession(session);

        // then
        assertThat(course.getSessions().isEmpty()).isFalse();
    }
}
