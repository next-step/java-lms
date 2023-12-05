package nextstep.courses.domain;

import nextstep.courses.exception.NotOpenSessionException;
import nextstep.courses.exception.OutOfSessionException;
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
    @DisplayName("수강 신청 시 강의 상태가 모집중이 아니면 예외를 던진다.")
    void register_status_check() {
        Session session = new FreeSession(1L, new CoverImage(1, "gif", 300, 200), START_DATE, END_DATE);
        assertThatThrownBy(() -> session.register(Payment.ofFree(1L, NsUserTest.JAVAJIGI)))
                .isInstanceOf(NotOpenSessionException.class);
    }

    @Test
    @DisplayName("강의 상태를 모집중으로 변경 시 현재 날짜가 강의 기간에 속하지 않으면 예외를 던진다.")
    void register_open() {
        Session session = new FreeSession(1L, new CoverImage(1, "gif", 300, 200), START_DATE, END_DATE);
        assertThatThrownBy(() -> session.openSession())
                .isInstanceOf(OutOfSessionException.class);
    }

}
