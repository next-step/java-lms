package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import nextstep.courses.domain.value.Money;
import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.policy.FreeSessionPolicy;
import nextstep.courses.domain.policy.PaidSessionPolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class SessionTest {

    private SessionPeriod sessionPeriod;
    private CoverImage coverImage;

    @BeforeEach
    void setup() {
        sessionPeriod = new SessionPeriod(LocalDate.now(), LocalDate.now().plusMonths(3));
        coverImage = new CoverImage("파일이름.png", 1024, 1500, 1000);
    }

    @Test
    void 무료_강의_수강신청에_성공한다() {
        Session session = new Session(sessionPeriod, coverImage, new FreeSessionPolicy(), SessionStatus.RECRUITING);

        session.enroll(1L, new Money(0));

        assertThat(session.currentEnrollmentCount()).isEqualTo(1);
    }

    @Test
    void 유료_강의_수강신청에_성공한다() {
        PaidSessionPolicy paidSessionPolicy = new PaidSessionPolicy(500_000, 30);
        Session session = new Session(sessionPeriod, coverImage, paidSessionPolicy, SessionStatus.RECRUITING);

        session.enroll(1L, new Money(500_000));

        assertThat(session.currentEnrollmentCount()).isEqualTo(1);
    }

    @Test
    void 유료_강의_수강신청_시_등록_인원을_초과하면_수강신청에_실패한다() {
        PaidSessionPolicy paidSessionPolicy = new PaidSessionPolicy(500_000, 0);
        Session session = new Session(sessionPeriod, coverImage, paidSessionPolicy, SessionStatus.RECRUITING);

        assertThatThrownBy(() -> session.enroll(1L, new Money(500_000)))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void 유료_강의_수강신청_시_수강료와_일치하지_않으면_수강신청에_실패한다() {
        PaidSessionPolicy paidSessionPolicy = new PaidSessionPolicy(500_000, 10);
        Session session = new Session(sessionPeriod, coverImage, paidSessionPolicy, SessionStatus.RECRUITING);

        assertThatThrownBy(() -> session.enroll(1L, new Money(300_000)))
                .isInstanceOf(RuntimeException.class);
    }

    @ParameterizedTest(name = "수강신청 시 상태가 {0}일 때 수강신청에 실패한다")
    @EnumSource(value = SessionStatus.class, names = {"PREPARING", "END"})
    void 강의_신청_시_모집중이_아니라면_수강신청에_실패한다(SessionStatus sessionStatus) {
        Session session = new Session(sessionPeriod, coverImage, new FreeSessionPolicy(), sessionStatus);

        assertThatThrownBy(() -> session.enroll(1L, new Money(0)))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("현재 모집중인 강의가 아닙니다.");
    }
}
