package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {

    Session session;

    @BeforeEach
    void setSession() {
        LocalDate start = LocalDate.of(2023, 6, 15);
        LocalDate end = LocalDate.of(2023, 6, 15);

        List<Student> collect = IntStream.range(0, 15)
                .mapToObj(number -> new Student())
                .collect(Collectors.toList());

        Students students = new Students(collect);
        session = new Session("coby.jpg", start, end, students);
    }

    @Test
    void 생성자_테스트() {
        LocalDate start = LocalDate.of(2023, 6, 15);
        LocalDate end = LocalDate.of(2023, 6, 15);

        List<Student> collect = IntStream.range(0, 21)
                .mapToObj(number -> new Student())
                .collect(Collectors.toList());

        Students students = new Students(collect);

        assertThatThrownBy(() -> {
            new Session("coby.jpg", start, end, students);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최대 인원을 초과하였습니다.");

    }

    @Test
    void 강의_무료값_체크() {
        session.switchToPaidCourse();
        session.switchToFreeCourse();
        assertThat(session.getFree()).isTrue();
    }

    @Test
    void 강의_유로값_체크() {
        session.switchToFreeCourse();
        session.switchToPaidCourse();
        assertThat(session.getFree()).isFalse();
    }

    @Test
    void changeToStatus() {
        session.changeToStatus(SessionType.READY);
        assertThat(session.getStatus()).isEqualTo(SessionType.READY);

        session.changeToStatus(SessionType.RECRUITING);
        assertThat(session.getStatus()).isEqualTo(SessionType.RECRUITING);

        session.changeToStatus(SessionType.END);
        assertThat(session.getStatus()).isEqualTo(SessionType.END);
    }
}