package nextstep.courses.domain.course;

import nextstep.courses.DuplicateSessionException;
import nextstep.courses.domain.session.SessionTest;
import nextstep.courses.domain.session.Sessions;
import nextstep.courses.domain.session.SessionsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CourseTest {
    private Course c1;

    @BeforeEach
    void setUp() {
        c1 = new Course(1L, "clean code", 1L
                , LocalDateTime.of(2023, 5, 6, 12, 0, 0)
                , LocalDateTime.of(2023, 12, 6, 12, 0, 0));
    }

    @Test
    @DisplayName("강의 등록")
    void addSession() {
        c1.addSession(SessionTest.readySession);
        Sessions sessions = SessionsTest.ss1;

        assertThat(c1.getSessions()).isEqualTo(sessions);
    }

    @Test
    @DisplayName("동일 강의 등록 불가")
    void addSession_exception() {
        c1.addSession(SessionTest.readySession);
        assertThatThrownBy(() -> {
            c1.addSession(SessionTest.readySession);
        }).isInstanceOf(DuplicateSessionException.class).hasMessageContaining("동일 강의 등록 불가합니다.");
    }
}
