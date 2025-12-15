package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    @Test
    void 무료_강의_등록() {
        Session session = SessionBuilder.aRecuitingSession()
                .asFreeSession()
                .build();

        session.enroll(new Enrollment(1L, 1L), Money.ZERO);

        assertThat(session.countEnrollments()).isEqualTo(1);
    }

    @Test
    void 강의_등록_성공() {
        Session session = SessionBuilder.aRecuitingSession()
                .asPaidSession(50000, 10)
                .build();

        session.enroll(new Enrollment(1L, 1L), new Money(50000));

        assertThat(session.countEnrollments()).isEqualTo(1);
    }

    @Test
    void 강의_금액_불일치_예외() {
        Session session = SessionBuilder.aRecuitingSession()
                .asPaidSession(50000, 10)
                .build();

        assertThatThrownBy(
                () -> session.enroll(new Enrollment(1L, 1L), new Money(45000))
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("결제 금액이 수강료와 일치하지 않습니다.");

    }

    @Test
    void 마감된_강의_등록시_예외() {
        Session session = SessionBuilder.anEndSession()
                .asPaidSession(50000, 10)
                .build();

        assertThatThrownBy(
                () -> session.enroll(new Enrollment(1L, 1L), new Money(50000))
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("모집중인 강의만 수강 신청할 수 있습니다.");
    }

    @Test
    void 정원_초과_등록시_예외() {
        Session session = SessionBuilder.aRecuitingSession()
                .asPaidSession(50000, 1)
                .build();

        session.enroll(new Enrollment(1L, 1L), new Money(50000));

        assertThatThrownBy(
                () -> session.enroll(new Enrollment(2L, 1L), new Money(50000))
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("수강 인원이 초과되었습니다.");
    }

    @Test
    void 이미_수강_중인_강의_등록시_예외() {
        Session session = SessionBuilder.aRecuitingSession()
                .asPaidSession(50000, 2)
                .build();

        session.enroll(new Enrollment(1L, 1L), new Money(50000));

        assertThatThrownBy(
                () -> session.enroll(new Enrollment(1L, 1L), new Money(50000))
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이미 수강 신청한 강의입니다.");
    }

    @Test
    void 신청한_강의와_결제한_강의와_다를_경우_예외() {
        Session session = SessionBuilder.aRecuitingSession()
                .asPaidSession(50000, 2)
                .build();

        assertThatThrownBy(
                () -> session.enroll(new Enrollment(1L, 2L), new Money(50000))
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("신청하고자 하는 강의가 아닙니다.");
    }
}
