package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class RecruitmentCountTest {

    private static final RecruitmentCount RECRUITMENTCOUNT = new RecruitmentCount(10);

    @DisplayName("강의모집인원이 음수일경우 에러를 던진다.")
    @Test
    void 강의_모집인원_음수_에러() {
        assertThatThrownBy(() -> {
            new RecruitmentCount(-1);
        }).isInstanceOf(IllegalArgumentException.class);
    }


    @DisplayName("강의모집인원 마감시 true를 리턴한다.")
    @Test
    void 강의_모집마감_검증() {
        assertAll(
                () -> assertThat(RECRUITMENTCOUNT.isClosed(10)).isTrue(),
                () -> assertThat(RECRUITMENTCOUNT.isClosed(15)).isTrue(),
                () -> assertThat(RECRUITMENTCOUNT.isClosed(5)).isFalse()
        );
    }

}