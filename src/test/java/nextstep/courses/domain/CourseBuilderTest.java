package nextstep.courses.domain;

import static nextstep.courses.domain.CourseBuilder.aCourse;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CourseBuilderTest {
    @Test
    @DisplayName("id를 지정하여 Course를 생성")
    void withId() {
        CourseBuilder builder = aCourse().withId(1L);
        Course course = builder.build();
        assertThat(course).isInstanceOf(Course.class);
    }

    @Test
    @DisplayName("title을 지정하여 Course를 생성")
    void withTitle() {
        CourseBuilder builder = aCourse().withTitle("Java, TDD with Clean code");
        Course course = builder.build();
        assertThat(course).isInstanceOf(Course.class);
    }

    @Test
    @DisplayName("creatorId를 지정하여 Course를 생성")
    void withCreatorId() {
        CourseBuilder builder = aCourse().withCreatorId(1L);
        Course course = builder.build();
        assertThat(course).isInstanceOf(Course.class);
    }

    @Test
    @DisplayName("sessions를 지정하여 Course를 생성")
    void withSessions() {
        CourseBuilder builder = aCourse().withSessions(new Sessions(new LinkedHashSet<>(Set.of(new Session()))));
        Course course = builder.build();
        assertThat(course).isInstanceOf(Course.class);
    }

    @Test
    @DisplayName("status를 지정하여 Session를 생성")
    void withCreateAt() {
        CourseBuilder builder = aCourse().withCreatedAt(LocalDateTime.now());
        Course course = builder.build();
        assertThat(course).isInstanceOf(Course.class);
    }

    @Test
    @DisplayName("status를 지정하여 Session를 생성")
    void withUpdateAt() {
        CourseBuilder builder = aCourse().withUpdatedAt(LocalDateTime.now());
        Course course = builder.build();
        assertThat(course).isInstanceOf(Course.class);
    }
}
