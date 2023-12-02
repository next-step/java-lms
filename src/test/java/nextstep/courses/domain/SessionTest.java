package nextstep.courses.domain;

import nextstep.courses.exception.NotOpenSessionException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {

    private static final LocalDate START_DATE = LocalDate.of(2023, 12, 01);
    private static final LocalDate END_DATE = LocalDate.of(2023, 12, 03);

    @Test
    @DisplayName("수강 신청 시 강의 상태가 모집중이 아니면 예외를 반환한다.")
    void register_status_check() {
        Session session = new Session(1L, new CoverImage(1, "gif", 300, 200), Status.NOT_OPEN, START_DATE, END_DATE);
        assertThatThrownBy(() -> session.register(Payment.ofFree(1L, NsUserTest.JAVAJIGI)))
                .isInstanceOf(NotOpenSessionException.class);
    }
}
