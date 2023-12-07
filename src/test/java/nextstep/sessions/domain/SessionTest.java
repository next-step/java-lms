package nextstep.sessions.domain;

import nextstep.images.domain.Image;
import nextstep.images.domain.ImageType;
import nextstep.sessions.strategy.EnrollmentStrategy;
import nextstep.sessions.strategy.FreeEnrollment;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {
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
    @DisplayName("강의가 종료되면 신청할 수 없다.")
    void test1() {
        EnrollmentStrategy freeEnrollment = new FreeEnrollment();
        Enrollment enrollment = new Enrollment(freeEnrollment);
        enrollment.changeSessionStatus(Status.CLOSED);

        assertThatThrownBy(()-> enrollment.enroll(new NsUser(), 0))
                .hasMessageContaining("강의가 모집중이 아닙니다.");
    }
}