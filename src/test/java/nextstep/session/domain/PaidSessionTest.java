package nextstep.session.domain;

import nextstep.exception.StudentsException;
import nextstep.payments.domain.GeneralPayment;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PaidSessionTest {

    private Session session;

    @BeforeEach
    void setUp() {
        Resolution resolution = new Resolution(300, 200);
        FilePathInformation filePathInformation = new FilePathInformation("/home", "mapa", "jpg");

        session = new PaidSession(
                new Duration(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3)),
                new Cover(resolution, filePathInformation, 10000),
                "얼른 배우자 객체지향",
                2,
                100_000L,
                1L,
                new Tutor(NsUserTest.JAVAJIGI)
        );
    }

    @DisplayName("상태가 ON_ENROLL이면서, 신청일자가 Duration에 속하고, 신청인원이 꽉 차지 않았다면 신청 가능하다.")
    @Test
    void enrollAvailable() {
        // when
        session.toNextSessionStatus();

        // then
        assertThat(session.isEnrollAvailable(LocalDateTime.now().plusDays(1)))
                .isTrue();
    }

    @DisplayName("다른 조건이 만족하지만 상태가 ON_ENROLL이 아니라면, 신청이 불가능하다.")
    @Test
    void cannotEnrollWithoutStatusCondition() {
        // then
        assertThat(session.isEnrollAvailable(LocalDateTime.now().plusDays(1)))
                .isFalse();
    }

    @DisplayName("다른 조건이 만족하지만 신청일자가 가능일자와 다르다면, 신청이 불가능하다.")
    @Test
    void cannotEnrollWithoutDurationCondition() {
        // when
        session.toNextSessionStatus();

        // then
        assertThat(session.isEnrollAvailable(LocalDateTime.now().plusDays(8)))
                .isFalse();
    }

    @DisplayName("다른 조건이 만족하지만 수강인원이 모두 찼다면, 신청이 불가능하다.")
    @Test
    void cannotEnrollWithoutCountOfStudentsCondition() {
        // given
        Payment payment = new GeneralPayment("NORMAL", 1L, 1L, 100_000L);

        // when
        session.toNextSessionStatus();
        session.apply(NsUserTest.SANJIGI, payment, LocalDateTime.now().plusDays(1));
        session.apply(NsUserTest.JAVAJIGI, payment, LocalDateTime.now().plusDays(1));

        // then
        assertThat(session.isEnrollAvailable(LocalDateTime.now().plusDays(1)))
                .isFalse();
    }

    @DisplayName("모든 조건이 일치하지 않는경우 신청이 불가능하다.")
    @Test
    void enrollNotAvailableWithNotOnEnroll() {
        // given
        Payment payment = new GeneralPayment("NORMAL", 1L, 1L, 100_000L);

        // when
        session.toNextSessionStatus();
        session.toNextSessionStatus();
        session.apply(NsUserTest.SANJIGI, payment, LocalDateTime.now().plusDays(1));
        session.apply(NsUserTest.SANJIGI, payment, LocalDateTime.now().plusDays(1));

        // then
        assertThat(session.isEnrollAvailable(LocalDateTime.now().plusDays(8)))
                .isFalse();
    }

    @DisplayName("동일한 수강 신청은 불가능하다.")
    @Test
    void cannotApplySameStudentTwice() {
        // given
        Payment payment = new GeneralPayment("NORMAL", 1L, 1L, 100_000L);

        // when
        session.toNextSessionStatus();
        session.apply(NsUserTest.SANJIGI, payment, LocalDateTime.now().plusDays(1));

        // then
        Assertions.assertThatThrownBy(() -> session.apply(NsUserTest.SANJIGI, payment, LocalDateTime.now().plusDays(1)))
                .isInstanceOf(StudentsException.class);
    }

    //--------

//    @DisplayName("수강신청 기간이면서 모집중이라면, 수강신청이 가능하다.")
//    @Test
//    void erollAvailable() {
//        // given
//        Payment payment = new FreePayment();
//
//        // when
//        session.toNextSessionStatus();
//
//        // then
//        assertThat(session.apply(NsUserTest.JAVAJIGI, payment, LocalDateTime.now().plusDays(2)))
//                .isTrue();
//    }
//
//    @DisplayName("모집중이여도 수강신청 기간이 아니면, 수강신청이 불가능하다.")
//    @Test
//    void onEnrollNotInDurationIsUnavailable() {
//        // given
//        Payment payment = new FreePayment();
//
//        // when
//        session.toNextSessionStatus();
//
//        // then
//        assertThat(session.apply(NsUserTest.JAVAJIGI, payment, LocalDateTime.now()))
//                .isFalse();
//    }
//
//    @DisplayName("수강신청 기간이여도 모집중이 아니라면, 수강신청이 불가능하다.")
//    @Test
//    void inDurationAndNotOnEnrollIsUnavailable() {
//        // given
//        Payment payment = new FreePayment();
//
//        // then
//        assertThat(session.apply(NsUserTest.JAVAJIGI, payment, LocalDateTime.now().plusDays(2)))
//                .isFalse();
//    }
}
