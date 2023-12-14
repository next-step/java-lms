package nextstep.courses.domain.session;

import nextstep.courses.domain.session.coverimage.CoverImages;
import nextstep.courses.domain.session.enums.RecruitingStatus;
import nextstep.courses.domain.session.enums.SessionStatus;
import nextstep.courses.domain.session.period.Period;
import nextstep.courses.domain.session.student.SessionStudents;
import nextstep.courses.dto.EnrolmentInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static nextstep.courses.domain.session.enums.PayType.*;
import static nextstep.courses.domain.session.enums.SessionStatus.*;
import static nextstep.courses.domain.session.enums.RecruitingStatus.RECRUITING_OFF;
import static nextstep.courses.domain.session.enums.RecruitingStatus.RECRUITING_ON;
import static nextstep.users.domain.fixture.DomainFixture.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaySessionTest {

    @DisplayName("현재 강의의 모집 상태가 모집중이 아니면 등록할 때 예외를 발생시킨다.")
    @Test
    void validateRecruitingStatus() {
        // given
        PaySession paySession = createPaySession(PROGRESS, RECRUITING_OFF);
        EnrolmentInfo enrolmentInfo = createEnrolmentInfo(paySession.id, JAVAJIGI.getId(), 10000L);

        // when & then
        assertThatThrownBy(() -> paySession.enroll(enrolmentInfo)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("해당 강의는 현재 모집중이 아닙니다.");
    }

    @DisplayName("현재 강의의 상태가 진행중이 아니면 등록할 때 예외를 발생시킨다.")
    @Test
    void validateSessionStatus() {
        // given
        PaySession paySession = createPaySession(PREPARE, RECRUITING_ON);
        EnrolmentInfo enrolmentInfo = createEnrolmentInfo(paySession.id, JAVAJIGI.getId(), 10000L);

        // when & then
        assertThatThrownBy(() -> paySession.enroll(enrolmentInfo)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("해당 강의는 현재 준비중입니다.");
    }

    @DisplayName("결제 금액이 강의 금액과 일치하지 않으면 예외를 발생시킨다.")
    @Test
    void validatePayAmount() {
        // given
        PaySession paySession = createPaySession(PROGRESS, RECRUITING_ON);
        EnrolmentInfo enrolmentInfo = createEnrolmentInfo(paySession.id, JAVAJIGI.getId(), 12000L);

        // when & then
        assertThatThrownBy(() -> paySession.enroll(enrolmentInfo)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("결제 금액이 강의 금액과 일치하지 않습니다. 강의 금액 :: 10,000원");
    }

    private PaySession createPaySession(SessionStatus sessionStatus, RecruitingStatus recruitingStatus) {
        return new PaySession(
            1L,
            PAY,
            sessionStatus,
            recruitingStatus,
            new CoverImages(),
            new SessionStudents(),
            Period.from(
                LocalDate.of(2023, 12, 5),
                LocalDate.now()
            ),
            10000L,
            2
            );
    }

    private EnrolmentInfo createEnrolmentInfo(Long sessionId, Long nsUserId, Long payAmount) {
        return new EnrolmentInfo(sessionId, nsUserId, payAmount);
    }
}