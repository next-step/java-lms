package nextstep.courses.domain;

import nextstep.courses.exception.*;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {

    private static final LocalDate START_DATE = LocalDate.of(2023, 12, 1);
    private static final LocalDate END_DATE = LocalDate.of(2023, 12, 31);

    @Test
    @DisplayName("무료 강의 수강 신청 시 강의 상태가 모집중이 아니면 예외를 던진다.")
    void register_status_check() {
        Session freeSession = Session.ofFree(1L, 2L, coverImages(), START_DATE, END_DATE);
        assertThatThrownBy(() -> freeSession.register(Payment.ofFree(1L, NsUserTest.JAVAJIGI)))
                .isInstanceOf(NotOpenSessionException.class);
    }

    @Test
    @DisplayName("강의 상태를 모집중으로 변경 시 현재 날짜가 강의 기간에 속하지 않으면 예외를 던진다.")
    void register_open() {
        Session session = Session.ofFree(1L, 2L, coverImages(), START_DATE, LocalDate.of(2023, 12, 2));
        assertThatThrownBy(() -> session.openSession())
                .isInstanceOf(OutOfSessionException.class);
    }

    @Test
    @DisplayName("유료 강의 수강 신청 시 최대 수강 인원을 초과하면 예외를 던진다.")
    void register_over_students() {
        Session paidSession = Session.ofPaid(1L, 2L, coverImages(), START_DATE, END_DATE, 1, 10_000L);
        paidSession.openSession();

        paidSession.register(Payment.ofPaid(1L, 1L, NsUserTest.JAVAJIGI, 10_000L));

        assertThatThrownBy(() -> paidSession.register(Payment.ofPaid(2L, 1L, NsUserTest.SANJIGI, 8_000L)))
                .isInstanceOf(OverMaxStudentsException.class);
    }

    @Test
    @DisplayName("유료 강의 수강 신청 시 결제금액과 수강료가 일치하는지 확인하지 않으면 예외를 던진다.")
    void session_fee_test() {
        Session paidSession = Session.ofPaid(1L, 2L, coverImages(), START_DATE, END_DATE, 1, 10_000L);
        paidSession.openSession();

        assertThatThrownBy(() -> paidSession.register(Payment.ofPaid(2L, 1L, NsUserTest.SANJIGI, 8_000L)))
                .isInstanceOf(PaymentMismatchException.class);
    }

    @Test
    @DisplayName("강의 생성 시 결제금액과 수강료가 음수면 예외를 던진다.")
    void session_paid_condition_null() {
        assertThatThrownBy(() -> Session.ofPaid(1L, 2L, coverImages(), START_DATE, END_DATE, -1, -1L))
                .isInstanceOf(NegativePaidConditionException.class);
    }

    @Test
    @DisplayName("중복 수강 신청 시 예외를 던진다.")
    void duplicate_register() {
        Session paidSession = Session.ofPaid(1L, 2L, coverImages(), START_DATE, END_DATE, 2, 10_000L);
        paidSession.openSession();

        paidSession.register(Payment.ofPaid(1L, 1L, NsUserTest.SANJIGI, 10_000L));

        assertThatThrownBy(() -> paidSession.register(Payment.ofPaid(2L, 1L, NsUserTest.SANJIGI, 10_000L)))
                .isInstanceOf(DuplicateStudentsException.class);
    }

    @Test
    @DisplayName("강의 생성 시 이미지가 없으면 예외를 던진다.")
    void empty_images() {
        assertThatThrownBy(() -> Session.ofPaid(1L, 2L, new CoverImages(null), START_DATE, END_DATE, 1, 10_000L))
                .isInstanceOf(EmptyCoverImageException.class);
    }

    private static CoverImages coverImages() {
        return new CoverImages(List.of(new CoverImage(1, "gif", 300, 200)));
    }
}
