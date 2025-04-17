package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {

    @Test
    @DisplayName("강의는 기수, 시작일, 종료일, 커버 이미지, 수강료, 강의상태, 최대 수강 인원을 가진다.")
    void createSession() {
        LocalDateTime start = LocalDateTime.now().plusMonths(1);
        LocalDateTime end = LocalDateTime.now().plusMonths(3);
        assertThatCode(() -> new Session(1, start, end, "image.jpg", 800_000, 100)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("무료 강의는 최대 수강 인원 제한이 없다.")
    void createFreeSession() {
        LocalDateTime start = LocalDateTime.now().plusMonths(1);
        LocalDateTime end = LocalDateTime.now().plusMonths(3);
        Session session = new Session(1, start, end, "image.jpg", 0, 0);
        assertThatCode(() -> session.addStudent(new Student())).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("유료 강의는 강의 최대 수강 인원을 초과할 수 없다.")
    void createPaidSession() {
        LocalDateTime start = LocalDateTime.now().plusMonths(1);
        LocalDateTime end = LocalDateTime.now().plusMonths(3);
        Session session = new Session(1, start, end, "image.jpg", 800_000, 1);
        session.addStudent(new Student());

        assertThatThrownBy(() -> session.addStudent(new Student()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("student limit exceeded");
    }

}
