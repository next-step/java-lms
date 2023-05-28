package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    @DisplayName("세션 추가 시 정상 확인")
    void add_session_normal() {
        LocalDateTime startedAt = LocalDateTime.parse("2022-01-01 11:11:11", formatter);
        LocalDateTime endedAt = LocalDateTime.parse("2022-01-01 11:11:11", formatter);
        boolean isFree = true;
        Status status = Status.PREPARING;
        int currentStudents = 10;
        int maxStudents = 10;
        Session session = new Session(startedAt, endedAt, isFree, status, currentStudents, maxStudents);

        course.addSession(session);

        int expected = 1;
        int actual = course.sessionList().size();

        assertThat(expected).isEqualTo(actual);
    }

    @Test
    @DisplayName("시작 시간이 늦을 시 에러 발생")
    void add_session_late_start() {
        LocalDateTime startedAt = LocalDateTime.parse("2022-01-01 12:11:11", formatter);
        LocalDateTime endedAt = LocalDateTime.parse("2022-01-01 11:11:11", formatter);
        boolean isFree = true;
        Status status = Status.PREPARING;
        int currentStudents = 0;
        int maxStudents = 10;

        assertThatThrownBy(() -> course.addSession(new Session(startedAt, endedAt, isFree, status, currentStudents, maxStudents)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("최대 수강 인원보다 현재 수강 인원이 더 많을 시 에러 발생")
    void add_session_more_current_student() {
        LocalDateTime startedAt = LocalDateTime.parse("2022-01-01 11:11:11", formatter);
        LocalDateTime endedAt = LocalDateTime.parse("2022-01-01 11:11:11", formatter);
        boolean isFree = true;
        Status status = Status.PREPARING;
        int currentStudents = 11;
        int maxStudents = 10;

        assertThatThrownBy(() -> course.addSession(new Session(startedAt, endedAt, isFree, status, currentStudents, maxStudents)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}