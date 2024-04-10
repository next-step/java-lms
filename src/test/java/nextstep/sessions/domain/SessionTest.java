package nextstep.sessions.domain;

import nextstep.payments.domain.Payment;
import nextstep.sessions.exception.CannotEnrollException;
import nextstep.users.domain.NsUserTest;
import nextstep.users.domain.NsUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class SessionTest {
    private Session freeSession;
    private Session paidSession;
    private Session preparingSession;
    private Session enrolledSession;

    @BeforeEach
    void setup() {
        freeSession = Session.freeSession(1L, "무료강의", PeriodTest.APRIL, CoverImageTest.TEST_IMAGE, SessionStatus.RECRUITING, new NsUsers(), LocalDateTime.now(), LocalDateTime.now());
        paidSession = Session.paidSession(2L, "유료강의", PeriodTest.APRIL, CoverImageTest.TEST_IMAGE, SessionStatus.RECRUITING, 1, 800_000L, new NsUsers(), LocalDateTime.now(), LocalDateTime.now());
        preparingSession = Session.freeSession(1L, "무료강의", PeriodTest.APRIL, CoverImageTest.TEST_IMAGE, SessionStatus.PREPARING, new NsUsers(), LocalDateTime.now(), LocalDateTime.now());
        enrolledSession = Session.freeSession(1L, "무료강의", PeriodTest.APRIL, CoverImageTest.TEST_IMAGE, SessionStatus.RECRUITING, new NsUsers(List.of(NsUserTest.JAVAJIGI)), LocalDateTime.now(), LocalDateTime.now());
    }

    @DisplayName("수강신청 가능여부 검증 메서드는")
    @Nested
    class Describe_assertCanEnroll {

        @DisplayName("모집중이고, 신청한 적이 없고, 자리가 있을 경우 통과한다.")
        @Test
        void can_enroll() {
            assertThatCode(() -> freeSession.assertCanEnroll(NsUserTest.JAVAJIGI, LocalDate.of(2024, 3, 15).atStartOfDay()))
                    .doesNotThrowAnyException();
        }

        @DisplayName("모집중이지 않은 강의는 신청할 수 없다.")
        @Test
        void not_recruiting_session() {
            assertThatThrownBy(() -> preparingSession.assertCanEnroll(NsUserTest.JAVAJIGI, LocalDate.of(2024, 3, 15).atStartOfDay()))
                    .isInstanceOf(CannotEnrollException.class)
                    .hasMessage("현재 모집중인 강의가 아닙니다.");
        }

        @DisplayName("진행중인 강의는 신청할 수 없다.")
        @Test
        void during_session_period() {
            assertThatThrownBy(() -> preparingSession.assertCanEnroll(NsUserTest.JAVAJIGI, LocalDate.of(2024, 4, 15).atStartOfDay()))
                    .isInstanceOf(CannotEnrollException.class)
                    .hasMessage("현재 모집중인 강의가 아닙니다.");
        }

        @DisplayName("이미 신청한 강의는 신청할 수 없다.")
        @Test
        void already_enrolled() {
            assertThatThrownBy(() -> enrolledSession.assertCanEnroll(NsUserTest.JAVAJIGI, LocalDate.of(2024, 3, 15).atStartOfDay()))
                    .isInstanceOf(CannotEnrollException.class)
                    .hasMessage("이미 신청한 강의입니다.");
        }
    }

    @DisplayName("수강신청 메서드는")
    @Nested
    class Describe_enroll {

        @DisplayName("결제 정보와 강의 정보가 일치할 경우, 최종 수강신청 처리된다.")
        @Test
        void can_enroll() {
            assertThatCode(() -> paidSession.enroll(NsUserTest.JAVAJIGI, new Payment("", 2L, 1L, 800_000L)))
                    .doesNotThrowAnyException();
        }

        @DisplayName("결제한 강의 ID와 수강신청하는 ID가 다를 경우 예외를 던진다.")
        @Test
        void different_sessionId() {
            assertThatThrownBy(() -> paidSession.enroll(NsUserTest.JAVAJIGI, new Payment("", 1L, 1L, 800_000L)))
                    .isInstanceOf(CannotEnrollException.class)
                    .hasMessage("결제 정보와 강의 정보가 일치하지 않습니다.");
        }

        @DisplayName("결제 금액과 강의 가격이 다를 경우 예외를 던진다.")
        @Test
        void different_price() {
            assertThatThrownBy(() -> paidSession.enroll(NsUserTest.JAVAJIGI, new Payment("", 2L, 1L, 100L)))
                    .isInstanceOf(CannotEnrollException.class)
                    .hasMessage("결제 금액과 강의 금액이 일치하지 않습니다.");
        }

    }
}
