package nextstep.courses.domain;

import nextstep.courses.domain.type.SessionStatus;
import nextstep.courses.exception.DifferentSessionAmountException;
import nextstep.courses.exception.ExceedMaxStudentException;
import nextstep.courses.exception.NotRecruitingSessionException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ChargedSessionTest {

    @Test
    @DisplayName("강의 상태가 모집중이 아닐 때 수강신청 시 에러 발생한다")
    public void not_recruiting_status_apply() {
        Session session = new ChargedSession(duration(), image(), SessionStatus.READY, 5, BigDecimal.valueOf(10_000));
        NsUser user = NsUserTest.JAVAJIGI;
        Payment payment = new Payment(0L, 0L, user.getId(), BigDecimal.valueOf(10_000));

        assertThatExceptionOfType(NotRecruitingSessionException.class)
            .isThrownBy(() -> session.apply(payment, user))
            .withMessageMatching("모집중인 강의가 아닙니다.");
    }

    @Test
    @DisplayName("최대 수강 인원 초과 시 에러 발생한다")
    public void exceed_max_number_of_student() {
        Session chargedSession = new ChargedSession(duration(), image(), SessionStatus.RECRUITING, 0, BigDecimal.valueOf(10_000));
        NsUser user = NsUserTest.JAVAJIGI;
        Payment payment = new Payment(0L, 0L, user.getId(), BigDecimal.valueOf(10_000));

        assertThatExceptionOfType(ExceedMaxStudentException.class)
            .isThrownBy(() -> chargedSession.apply(payment, user))
            .withMessageMatching("수강 인원을 초과했습니다.");
    }

    @Test
    @DisplayName("수강생이 결제한 금액과 수강료가 일치하지 않으면 수강 신청 시 에러 발생한다")
    public void validate_session_price() {
        Session chargedSession = new ChargedSession(duration(), image(), SessionStatus.RECRUITING, 5, BigDecimal.valueOf(10_000));
        NsUser user = NsUserTest.JAVAJIGI;
        Payment payment = new Payment(0L, 0L, user.getId(), BigDecimal.valueOf(8_000));

        assertThatExceptionOfType(DifferentSessionAmountException.class)
            .isThrownBy(() -> chargedSession.apply(payment, user))
            .withMessageMatching("수강료와 결제 금액이 일치하지 않습니다.");
    }

    @Test
    @DisplayName("수강 신청할 수 있다")
    public void apply_session() {
        Session chargedSession = new ChargedSession(duration(), image(), SessionStatus.RECRUITING, 5, BigDecimal.valueOf(10_000));
        NsUser user = NsUserTest.JAVAJIGI;
        Payment payment = new Payment(0L, 0L, user.getId(), BigDecimal.valueOf(10_000));

        chargedSession.apply(payment, user);
        assertThat(chargedSession.applys()).isEqualTo(Arrays.asList(new Apply(chargedSession, user)));
    }

    private Duration duration() {
        return new Duration(LocalDate.now(), LocalDate.now());
    }

    private Image image() {
        return new Image(1, "JPG", 300, 200);
    }
}
