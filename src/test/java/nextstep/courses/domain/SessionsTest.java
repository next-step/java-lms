package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionsTest {


    @Test
    void 강의_중복_등록() {
        Sessions sessions = new Sessions();
        SessionUser sessionUser = new SessionUser(1L, 1L, 2L, LocalDateTime.now());
        sessions.putEntity(sessionUser);

        assertThatThrownBy(() -> {
            sessions.putEntity(new SessionUser(1L, 1L, 2L, LocalDateTime.now()));
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
        SessionUser sessionUser = new SessionUser(1L, 1L, 2L, LocalDateTime.now());

        Sessions sessions = new Sessions();
        assertThatThrownBy(() -> {
            sessions.removeEntity(sessionUser);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("등록된 정보가 없습니다.");
    }
}