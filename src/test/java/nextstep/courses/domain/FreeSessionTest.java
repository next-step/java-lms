package nextstep.courses.domain;

import nextstep.courses.enums.SessionStatus;
import nextstep.courses.enums.SessionType;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FreeSessionTest {

    private Long sessionId;
    private SessionPeriod sessionPeriod;
    private Payment payment;
    private CoverImage coverImage;
    String imageType = "JPG";
    int imageWidth = 300;
    int imageHeight = 200;
    int imageFileSize = 1024;

    @BeforeEach
    void setUp() {
        sessionId = 1L;
        sessionPeriod = SessionPeriod.of(
                LocalDateTime.of(2024, 1, 1, 0, 0, 0),
                LocalDateTime.of(2024, 4, 1, 0, 0, 0));
        payment = new Payment("1", 123L, 1L, 0L);
        coverImage = CoverImage.of(imageFileSize, imageType, imageWidth, imageHeight);
    }

    @Test
    @DisplayName("FreeSession의 SessionType확인")
    void sessionTypeCheckTest() {
        Session freeSession = new FreeSession(sessionId, sessionPeriod, SessionStatus.PREPARE, coverImage);
        assertThat(freeSession.sessionType()).isEqualTo(SessionType.FREE);
    }

    @Test
    @DisplayName("SessionStatus RECRUIT가 아니면 IllegalStateException")
    void sessionStatusCheckTest() {
        Session freeSession = new FreeSession(sessionId, sessionPeriod, SessionStatus.PREPARE, coverImage);

        assertThatThrownBy(() -> {
            freeSession.enroll(NsUserTest.JAVAJIGI, payment);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage("강의 모집중이 아닙니다.");
    }

    @Test
    @DisplayName("수강 신청 후 수강 인원 inscrese")
    void enrollInscreseTest() {
        Session freeSession = new FreeSession(sessionId, sessionPeriod, SessionStatus.PREPARE, coverImage);
        freeSession.start();
        freeSession.enroll(NsUserTest.JAVAJIGI, payment);
        assertThat(freeSession.countOfStudents()).isEqualTo(1);
    }
}