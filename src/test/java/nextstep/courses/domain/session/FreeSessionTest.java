package nextstep.courses.domain.session;

import nextstep.courses.domain.session.coverimage.CoverImage;
import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.courses.exception.NotRecruitingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static nextstep.courses.domain.session.PayType.PAY;
import static nextstep.courses.domain.session.Status.PREPARE;
import static nextstep.users.domain.fixture.DomainFixture.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FreeSessionTest {

    @DisplayName("현재 강의의 상태가 모집중이 아니면 예외를 발생시킨다.")
    @Test
    void validateStatus() {
        // given
        FreeSession paySession = createFreeSession(PREPARE);

        // when & then
        assertThatThrownBy(() -> paySession.enroll(new SessionStudent(paySession, JAVAJIGI))).isInstanceOf(NotRecruitingException.class)
            .hasMessage("해당 강의의 현재 준비중입니다.");
    }

    private FreeSession createFreeSession(Status status) {
        return new FreeSession(
            1L,
            PAY,
            status,
            new CoverImage(),
            LocalDate.of(2023, 12, 5),
            LocalDate.now()
            );
    }
}