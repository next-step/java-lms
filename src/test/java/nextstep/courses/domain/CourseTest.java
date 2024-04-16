package nextstep.courses.domain;

import nextstep.courses.enums.SessionStatus;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CourseTest {

    private Long sessionId;
    private SessionPeriod sessionPeriod;
    private Payment zeroPayment;
    private Payment payment;
    private int maxCapacity;
    private SessionFee sessionFee;
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
        zeroPayment = new Payment("1", 123L, 1L, 0L);
        payment = new Payment("1", 123L, 1L, 800_000L);
        maxCapacity = 1;
        sessionFee = new SessionFee(800_000);
        coverImage = CoverImage.of(imageFileSize, imageType, imageWidth, imageHeight);
    }

    @Test
    @DisplayName("Course(과정) 생성 후 Session(무료, 유료) 추가")
    void courseTest() {
        List<Session> sessions = List.of(
                new FreeSession(sessionId, sessionPeriod, SessionStatus.PREPARE, coverImage),
                new PaidSession(sessionId, sessionPeriod, SessionStatus.PREPARE, maxCapacity, sessionFee, coverImage));
        Course course = Course.of(1L
                , "TDD, 클린 코드 with Java"
                , 1L
                , LocalDateTime.now()
                , LocalDateTime.now()
                , sessions);
        assertEquals(course.getNumberOfSessions(), 2);

    }

}