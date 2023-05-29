package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionDateTest {

    @DisplayName("강의시작일자가 Null일경우 에러를 던진다.")
    @Test
    void 강의시작일자_Null_에러() {
        assertThatThrownBy(() -> {
            new SessionDate(null, LocalDate.now());
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("강의종료일자가 Null일경우 에러를 던진다.")
    @Test
    void 강의종료일자_Null_에러() {
        assertThatThrownBy(() -> {
            new SessionDate(LocalDate.now(), null);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}