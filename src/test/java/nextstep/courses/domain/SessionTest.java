package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import nextstep.courses.domain.dto.SessionEnrollment;
import nextstep.courses.domain.dto.SessionPayment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionTest {
    @Test
    @DisplayName("강의 생성")
    void create() {
        assertThat(new Session(LocalDateTime.now(), LocalDateTime.now().plusMonths(1L)))
                .isInstanceOf(Session.class);
    }

    @Test
    @DisplayName("강의 생성시 강의 시작, 강의 종료 에러 발생")
    void create_throw_exception_startDate_endDate() {
        assertThatThrownBy(()->
            new Session(LocalDateTime.now(),
                LocalDateTime.now().minusMonths(1L))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("강의 생성시 가격 타입 & 가격 매칭 실패 에러 발생")
    void create_throw_exception_sessionPaymentType_price() {
        assertThatThrownBy(()->
                new Session(0L,
                        new SessionPayment(SessionPaymentType.PAID, 0L),
                        new SessionEnrollment(1, 1),
                        LocalDateTime.now(),
                        LocalDateTime.now().plusMonths(1L))).isInstanceOf(IllegalArgumentException.class);
    }
}
