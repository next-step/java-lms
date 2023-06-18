package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionsTest {


    @Test
    void 강의_중복_등록() {
        LocalDateTime start = LocalDateTime.of(2023, 6, 28,0, 0);
        LocalDateTime end = LocalDateTime.of(2023, 7, 5,0,0);

        Sessions sessions = new Sessions();
        sessions.putEntity(new Session(1L, "제목", "coby.jpg", start, end));

        assertThatThrownBy(() -> {
            sessions.putEntity(new Session(1L, "제목", "coby.jpg", start, end));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 등록된 강의 입니다.");
    }

    @Test
    void 강의_null_등록() {
        Sessions sessions = new Sessions();

        assertThatThrownBy(() -> {
            sessions.putEntity(null);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("강의가 잘못 생성되었습니다.");
    }

    @Test
    void 강의_없는값_삭제() {
        LocalDateTime start = LocalDateTime.of(2023, 6, 28,0,0);
        LocalDateTime end = LocalDateTime.of(2023, 7, 5,0,0);

        Session session = new Session(1L,"제목", "coby.jpg", start, end);

        Sessions sessions = new Sessions();
        assertThatThrownBy(() -> {
            sessions.removeEntity(session);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("등록된 정보가 없습니다.");
    }
}