package nextstep.lms.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

class PaidSessionTest {
    PaidSession recruitingPaidSession;
    PaidSession completedPaidSession;

    @BeforeEach
    void setting() {
        recruitingPaidSession = new PaidSession(
                "유료강의(모집중)",
                LocalDateTime.of(2023, 10, 30, 0, 0),
                LocalDateTime.of(2023, 12, 14, 23, 59),
                new CoverImage("next.png", 0.8, 300, 200),
                SessionStatus.RECRUITING,
                new ArrayList<>(),
                1,
                800000);
        completedPaidSession = new PaidSession(
                "유료강의(모집마감)",
                LocalDateTime.of(2023, 10, 30, 0, 0),
                LocalDateTime.of(2023, 12, 14, 23, 59),
                new CoverImage("next.png", 0.8, 300, 200),
                SessionStatus.COMPLETED,
                new ArrayList<>(),
                1,
                800000);
    }
    @DisplayName("수강신청은 강의 상태가 모집중일 때만 가능")
    @Test
    void 중복_신청_불가능() throws IllegalArgumentException {
        recruitingPaidSession.enroll(NsUserTest.JAVAJIGI, 800000);
        assertThatThrownBy(() -> recruitingPaidSession.enroll(NsUserTest.JAVAJIGI, 800000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 수강중인 강의입니다.");
    }
    @DisplayName("수강신청은 강의 상태가 모집중일 때만 가능")
    @Test
    void 강의_마감() throws IllegalArgumentException {
        assertThatThrownBy(() -> completedPaidSession.enroll(NsUserTest.JAVAJIGI, 800000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수강신청 기간이 아닙니다.");
    }
    @DisplayName("수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능")
    @Test
    void 수강료_일치() throws IllegalArgumentException {
        assertThat(recruitingPaidSession.enroll(NsUserTest.JAVAJIGI, 800000)).isTrue();
    }

    @DisplayName("수강생이 결제한 금액과 수강료가 다를때 때 예외발생")
    @Test
    void 수강료_불일치() throws IllegalArgumentException {
        assertThatThrownBy(() -> recruitingPaidSession.enroll(NsUserTest.JAVAJIGI, 400000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("결제금액이 수강료와 다릅니다.");
    }

    @DisplayName("강의 최대 수강 인원을 초과할 수 없다.")
    @Test
    void 최대_수강인원_초과() throws IllegalArgumentException {
        recruitingPaidSession.enroll(NsUserTest.JAVAJIGI, 800000);
        assertThatThrownBy(() -> recruitingPaidSession.enroll(NsUserTest.SANJIGI, 800000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("강의 최대 수강 인원을 초과할 수 없습니다.");
    }
}