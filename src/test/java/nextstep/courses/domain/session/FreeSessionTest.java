package nextstep.courses.domain.session;

import nextstep.courses.domain.session.image.CoverImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;

import static nextstep.courses.domain.session.FreeSession.FREE_FEE;
import static nextstep.courses.domain.session.Session.ENROLLMENT_ALREADY_DONE;
import static nextstep.courses.domain.session.Session.SESSION_NOT_OPENED;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

class FreeSessionTest {

    @Test
    @DisplayName("무료 수강 신청 가능")
    void testFreeSession_isOpened() {
        // given
        FreeSession freeSession = getFreeSession(SessionStatusEnum.OPEN);

        // when
        freeSession.enrollStudent(new NsUser());

        // then
        assertTrue(freeSession.isSessionOpened());
        assertEquals(freeSession.users.getNumberOfUsers(), 1);
    }

    @ParameterizedTest(name = "무료 수강 신청 불가능 - 강의 상태가 모집중이 아님")
    @ValueSource(strings = { "PENDING", "CLOSED" })
    void testFreeSession_isClosed_ShouldThrowException(SessionStatusEnum sessionStatus) {
        // given
        FreeSession freeSession = getFreeSession(sessionStatus);

        // when, then
        assertFalse(freeSession.isSessionOpened());
        assertThatIllegalArgumentException().isThrownBy(() -> {
            freeSession.enrollStudent(new NsUser());
        }).withMessageContaining(SESSION_NOT_OPENED);
    }

    @Test
    @DisplayName("무료 수강 신청 불가능 - 이미 신청 완료된 강의")
    void testFreeSession_isAlreadyEnrolled_ShouldThrowException() {
        // given
        FreeSession freeSession = getFreeSession(SessionStatusEnum.OPEN);
        NsUser user = new NsUser();
        user.addPayment(new Payment("TEST", 1L, 1L, FREE_FEE));

        // when, then
        assertTrue(freeSession.isSessionOpened());
        assertThatIllegalArgumentException().isThrownBy(() -> {
            freeSession.enrollStudent(user);
        }).withMessageContaining(ENROLLMENT_ALREADY_DONE);
    }

    private FreeSession getFreeSession(SessionStatusEnum sessionStatusEnum) {
        Long sessionId = 1L;
        SessionPeriod sessionPeriod = SessionPeriod.of(
                LocalDateTime.of(2024,1,1,0,0,0),
                LocalDateTime.of(2024,4,1,0,0,0));
        CoverImage coverImage = CoverImage.of("jpg", 1024, 300,200);
        SessionStatusEnum sessionStatus = sessionStatusEnum;
        return new FreeSession(sessionId, sessionPeriod, coverImage, sessionStatus);
    }

}
