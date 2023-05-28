package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CourseTest {

    @Test
    void test_과정을_만들면_강의리스트가_생성된다(){
        // given
        Course course = new Course("객체지향 프로그래밍", 1L);

        // when
        Sessions sessions = course.getSessions();

        // then
        assertThat(sessions).isNotNull();
    }

    @Test
    void test_과정을_만들면_강의리스트가_비어있다(){
        // given
        Course course = new Course("객체지향 프로그래밍", 1L);

        // when
        Sessions sessions = course.getSessions();

        // then
        assertThat(sessions.isEmpty()).isTrue();
    }
}
