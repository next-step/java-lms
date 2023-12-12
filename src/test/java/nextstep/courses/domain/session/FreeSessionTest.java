package nextstep.courses.domain.session;

import nextstep.courses.domain.session.coverimage.CoverImage;
import nextstep.courses.domain.session.student.Students;
import nextstep.courses.dto.EnrolmentInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static nextstep.courses.domain.session.PayType.PAY;
import static nextstep.courses.domain.session.SessionStatus.PREPARE;
import static nextstep.users.domain.fixture.DomainFixture.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FreeSessionTest {

    @DisplayName("현재 강의의 상태가 모집중이 아니면 예외를 발생시킨다.")
    @Test
    void validateStatus() {
        // given
        FreeSession freeSession = createFreeSession(PREPARE);
        EnrolmentInfo enrolmentInfo = createEnrolment(freeSession.id, JAVAJIGI.getId(), 10000L);

        // when & then
        assertThatThrownBy(() -> freeSession.enroll(enrolmentInfo)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("해당 강의의 현재 준비중입니다.");
    }

    private FreeSession createFreeSession(SessionStatus sessionStatus) {
        return new FreeSession(
            1L,
            PAY,
            sessionStatus,
            new CoverImage(),
            LocalDate.of(2023, 12, 5),
            LocalDate.now(),
            new Students()
            );
    }

    private EnrolmentInfo createEnrolment(Long sessionId, Long nsUserId, Long payAmount) {
        return new EnrolmentInfo(sessionId, nsUserId, payAmount);
    }
}