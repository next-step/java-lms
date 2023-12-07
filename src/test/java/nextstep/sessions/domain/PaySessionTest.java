package nextstep.sessions.domain;

import nextstep.images.domain.Image;
import nextstep.images.domain.ImageType;
import nextstep.sessions.strategy.EnrollmentStrategy;
import nextstep.sessions.strategy.PayEnrollment;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaySessionTest {
    Long id;
    Subject subject;
    SessionPeriod sessionPeriod;
    LocalDateTime startedDate;
    LocalDateTime endedDate;
    Image image;
    SessionType sessionType;

    @BeforeEach
    void setUp() {
        id = 1L;
        subject = new Subject("TDD");
        long size = 1024;
        int width = 300;
        int height = 200;
        ImageType imageType = ImageType.PNG;
        image = new Image(id, size, width, height, imageType);
        sessionType = SessionType.PAY;

        startedDate = LocalDateTime.of(2023, 12, 1, 0, 0, 0);
        endedDate = LocalDateTime.of(2023, 12, 30, 0, 0, 0);
        sessionPeriod = new SessionPeriod(startedDate, endedDate);
    }

    @Test
    @DisplayName("최대 수강 인원을 초과할 수 없다.")
    void test1() {
        int fee = 800_000;
        int limit = 80;
        EnrollmentStrategy strategy = new PayEnrollment(fee, limit);
        Enrollment enrollment = new Enrollment(strategy);
        Session paySession = new Session(id, subject, enrollment, sessionPeriod, image, sessionType);
        paySession.recruiting();
        for (int i = 0; i < limit; i++) {
            paySession.enroll(new NsUser(), fee);
        }
        assertThatThrownBy(() -> paySession.enroll(new NsUser(), fee))
                .hasMessageContaining("최대 수강 인원을 초과했습니다.");
    }

    @Test
    @DisplayName("결제 금액과 일치해야 수강 신청이 가능하다.")
    void test2() {
        int fee = 800_000;
        int limit = 80;
        EnrollmentStrategy strategy = new PayEnrollment(fee, limit);
        Enrollment enrollment = new Enrollment(strategy);
        Session paySession = new Session(id, subject, enrollment, sessionPeriod, image, sessionType);
        paySession.recruiting();
        assertThatThrownBy(() -> paySession.enroll(new NsUser(), 500_000))
                .hasMessageContaining("강의 가격과 결제 가격이 다릅니다.");
    }
}