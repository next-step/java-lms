package nextstep.courses.domain;

import nextstep.courses.enums.SessionStatus;
import nextstep.courses.enums.SessionType;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
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
    List<NsUser> students;

    @BeforeEach
    void setUp() {
        sessionId = 1L;
        sessionPeriod = SessionPeriod.of(
                LocalDateTime.of(2024, 1, 1, 0, 0, 0),
                LocalDateTime.of(2024, 4, 1, 0, 0, 0));
        payment = new Payment("1", 123L, 1L, 0L);
        coverImage = CoverImage.of(imageFileSize, imageType, imageWidth, imageHeight);
        students = List.of(SANJIGI);
    }

    @Test
    @DisplayName("FreeSession의 SessionType확인")
    void sessionTypeCheckTest() {
        Session freeSession = new FreeSession(sessionId, sessionPeriod, SessionStatus.PREPARE, coverImage, students);
        assertThat(freeSession.sessionType()).isEqualTo(SessionType.FREE);
    }

    @Test
    @DisplayName("SessionStatus RECRUIT가 아니면 IllegalStateException")
    void sessionStatusCheckTest() {
        Session freeSession = new FreeSession(sessionId, sessionPeriod, SessionStatus.PREPARE, coverImage, students);

        assertThatThrownBy(() -> {
            freeSession.enroll(NsUserTest.JAVAJIGI, payment);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessage("강의 모집중이 아닙니다.");
    }

    @Test
    @DisplayName("수강 신청 후 수강 인원 inscrese")
    void enrollInscreseTest() {
        Session freeSession = new FreeSession(sessionId, sessionPeriod, SessionStatus.PREPARE, coverImage, students);
        freeSession.start();
        freeSession.enroll(NsUserTest.JAVAJIGI, payment);
        assertThat(freeSession.countOfStudents()).isEqualTo(2);
    }

    @Test
    @DisplayName("이미 수강 신청한 학생이면 IllegalStateException")
    void hasAlreadyEnrolled() {
        Session freeSession = new FreeSession(sessionId, sessionPeriod, SessionStatus.PREPARE, coverImage, students);
        freeSession.start();

        assertThatThrownBy(() -> {
            freeSession.enroll(SANJIGI, payment);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 수강 신청을 완료하신 강의입니다.");
    }
}