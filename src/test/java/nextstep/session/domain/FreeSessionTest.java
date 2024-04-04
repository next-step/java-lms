package nextstep.session.domain;

import nextstep.payments.domain.FreePayment;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class FreeSessionTest {

    private Session session;

    @BeforeEach
    void setUp() {
        Resolution resolution = new Resolution(300, 200);
        FilePathInformation filePathInformation = new FilePathInformation("/home", "mapa", "jpg");

        session = new FreeSession(
                new Duration(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3)),
                new Cover(resolution, filePathInformation, 10000),
                "얼른 배우자 객체지향",
                1L,
                new Tutor(NsUserTest.JAVAJIGI)
        );
    }

    @DisplayName("상태가 ON_ENROLL이면서, 신청일자가 Duration에 속한다면, 신청 가능하다.")
    @Test
    void enrollAvailable() {
        // given
        session.toNextSessionStatus();

        // then
        assertThat(session.isEnrollAvailable(LocalDateTime.now().plusDays(1)))
                .isTrue();
    }

    @DisplayName("상태가 ON_ENROLL이면서 신청일자가 Duration이 아니면, 신청이 불가능하다.")
    @Test
    void enrollNotAvailableWithNotInDuration() {
        // given
        session.toNextSessionStatus();

        // then
        assertThat(session.isEnrollAvailable(LocalDateTime.now().plusDays(8)))
                .isFalse();
    }

    @DisplayName("상태가 ON_ENROLL이 아니고 신청일자가 Duration이 아니면, 신청이 불가능하다.")
    @Test
    void enrollNotAvailableWithNotOnEnroll() {
        // given
        session.toNextSessionStatus();
        session.toNextSessionStatus();

        // then
        assertThat(session.isEnrollAvailable(LocalDateTime.now().plusDays(2)))
                .isFalse();
    }

    @DisplayName("수강신청 기간이면서 모집중이라면, 수강신청이 가능하다.")
    @Test
    void erollAvailable() {
        // given
        Payment payment = new FreePayment();

        // when
        session.toNextSessionStatus();

        // then
        assertThat(session.apply(NsUserTest.JAVAJIGI, payment, LocalDateTime.now().plusDays(2)))
                .isTrue();
    }

    @DisplayName("모집중이여도 수강신청 기간이 아니면, 수강신청이 불가능하다.")
    @Test
    void onEnrollNotInDurationIsUnavailable() {
        // given
        Payment payment = new FreePayment();

        // when
        session.toNextSessionStatus();

        // then
        assertThat(session.apply(NsUserTest.JAVAJIGI, payment, LocalDateTime.now()))
                .isFalse();
    }

    @DisplayName("수강신청 기간이여도 모집중이 아니라면, 수강신청이 불가능하다.")
    @Test
    void inDurationAndNotOnEnrollIsUnavailable() {
        // given
        Payment payment = new FreePayment();

        // then
        assertThat(session.apply(NsUserTest.JAVAJIGI, payment, LocalDateTime.now().plusDays(2)))
                .isFalse();
    }
}
