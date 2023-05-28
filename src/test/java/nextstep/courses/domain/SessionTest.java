package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SessionTest {

    @Test
    void test_강의의_시작일과_종료일_수정() {
        // given
        Session session = new Session(1L);

        // when
        session.updatePeriod("2021-01-01", "2021-01-02");

        // then
        assertThat(session.getStartDate()).isEqualTo(LocalDate.parse("2021-01-01"));
    }
}
