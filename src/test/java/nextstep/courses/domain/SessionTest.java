package nextstep.courses.domain;

import nextstep.courses.exception.ExceedingMaximumStudentException;
import nextstep.courses.exception.NotEligibleRegistrationStatusException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {

    private SessionDuration duration;
    private SessionCoverImage coverImage;

    @BeforeEach
    void setUp() {
        LocalDateTime startedAt = LocalDateTime.of(2023, 4, 3, 0, 0);
        LocalDateTime endedAt = LocalDateTime.of(2023, 6, 1, 0, 0);
        duration = SessionDuration.create(startedAt, endedAt);
        coverImage = SessionCoverImage.create("http://test.com/image01");

    }

    @Test
    @DisplayName("강의 최대 수강 인원을 초과할 수 없다.")
    void test01() {
        Session session = new Session(duration, coverImage, SessionPaymentType.FREE, SessionStatus.RECRUITING, 1);

        assertThatNoException().isThrownBy(() -> session.register(NsUserTest.JAVAJIGI));
        assertThatThrownBy(() -> session.register(NsUserTest.SANJIGI))
                .isInstanceOf(ExceedingMaximumStudentException.class);
    }

    @Test
    @DisplayName("수강신청은 강의 상태가 모집중일 때만 가능하다.")
    void test02() {
        Session preparingSession = new Session(duration, coverImage, SessionPaymentType.FREE, SessionStatus.PREPARING, 1);
        Session recruitingSession = new Session(duration, coverImage, SessionPaymentType.FREE, SessionStatus.RECRUITING, 1);
        Session endedSession = new Session(duration, coverImage, SessionPaymentType.FREE, SessionStatus.ENDED, 1);

        assertThatNoException()
                .isThrownBy(() -> recruitingSession.register(NsUserTest.JAVAJIGI));
        assertThatThrownBy(() -> preparingSession.register(NsUserTest.SANJIGI))
                .isInstanceOf(NotEligibleRegistrationStatusException.class);
        assertThatThrownBy(() -> endedSession.register(NsUserTest.SANJIGI))
                .isInstanceOf(NotEligibleRegistrationStatusException.class);
    }

}
