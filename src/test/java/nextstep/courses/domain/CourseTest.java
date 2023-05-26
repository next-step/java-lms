package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CourseTest {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private Course course;

    @BeforeEach
    void setUp() {
        course = new Course("TDD, 클린 코드 with Java", 1L);
    }

    @Test
    void add_session() {
        LocalDateTime startedAt = LocalDateTime.parse("2022-01-01 11:11:11", formatter);
        LocalDateTime endedAt = LocalDateTime.parse("2022-01-01 11:11:11", formatter);
        boolean isFree = true;
        Status status = Status.preparing;
        int currentStudents = 0;
        int maxStudents = 10;

        course.addSession(startedAt, endedAt, isFree, status, currentStudents, maxStudents);

        int expected = 1;
        int actual = course.sessionList().size();

        assertThat(expected).isEqualTo(actual);
    }

}