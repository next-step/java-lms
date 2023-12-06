package nextstep.courses.domain.session;

import nextstep.courses.domain.session.coverimage.CoverImage;
import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.courses.exception.NotRecruitingException;
import nextstep.courses.exception.NotRegisterSession;
import nextstep.courses.exception.SessionEnrollException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static nextstep.courses.domain.session.PayType.*;
import static nextstep.courses.domain.session.Status.*;
import static nextstep.users.domain.fixture.DomainFixture.JAVAJIGI;
import static nextstep.users.domain.fixture.DomainFixture.SANJIGI;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaySessionTest {

    @DisplayName("현재 강의의 상태가 모집중이 아니면 예외를 발생시킨다.")
    @Test
    void validateStatus() {
        // given
        PaySession paySession = createPaySession(PREPARE);

        // when & then
        assertThatThrownBy(() -> paySession.enroll(new SessionStudent(paySession, JAVAJIGI))).isInstanceOf(NotRecruitingException.class)
            .hasMessage("해당 강의의 현재 준비중입니다.");
    }

    @DisplayName("현재 수강인원이 제한 인원을 초과하면 예외를 발생시킨다.")
    @Test
    void validateCapacity() throws SessionEnrollException {
        // given
        Session paySession = createPaySession(RECRUIT);
        SessionStudent sessionStudent1 = new SessionStudent(paySession, JAVAJIGI);
        SessionStudent sessionStudent2 = new SessionStudent(paySession, SANJIGI);

        paySession.enroll(sessionStudent1);
        paySession.enroll(sessionStudent2);

        // when & then
        SessionStudent sessionStudent3 = new SessionStudent(paySession, new NsUser());

        assertThatThrownBy(() -> paySession.enroll(sessionStudent3)).isInstanceOf(NotRegisterSession.class)
            .hasMessage("현재 수강 가능한 모든 인원수가 채워졌습니다.");
    }

    private PaySession createPaySession(Status status) {
        return new PaySession(
            1L,
            PAY,
            status,
            new CoverImage(),
            LocalDate.of(2023, 12, 5),
            LocalDate.now(),
            new Amount(10000L),
            2);
    }

}