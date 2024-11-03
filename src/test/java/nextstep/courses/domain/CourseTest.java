package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.courses.domain.SessionTest.FREE_SESSION1;
import static nextstep.courses.domain.SessionTest.PAID_SESSION1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class CourseTest {

    public static final Course C1 = new Course(1L, "title1", 1L, LocalDateTime.now(), LocalDateTime.now());
    private Course course;

    @BeforeEach
    void setUp() {
        course = new Course(1L, "title1", 1L, LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    void create() {
        assertThatNoException().isThrownBy(() -> {
            new Course(1L, "title1", 1L, LocalDateTime.now(), LocalDateTime.now());
        });
    }

    @Test
    void addSession() {
        course.addSession(FREE_SESSION1);
        course.addSession(PAID_SESSION1);

        Course actual = new Course(1L, "title1", 1L, LocalDateTime.now(), LocalDateTime.now());
        actual.addSession(FREE_SESSION1);
        assertThat(actual).isNotEqualTo(course);

        actual.addSession(PAID_SESSION1);
        assertThat(actual).isEqualTo(course);
    }

}