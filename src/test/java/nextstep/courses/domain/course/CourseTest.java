package nextstep.courses.domain.course;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.cover.ImageHeight;
import nextstep.courses.domain.cover.ImageSize;
import nextstep.courses.domain.cover.ImageType;
import nextstep.courses.domain.cover.ImageWidth;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionName;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.MaxRegistrationCount;
import nextstep.courses.domain.session.RegistrationCount;
import nextstep.courses.domain.session.impl.FreeSession;
import nextstep.courses.domain.session.impl.PaidSession;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

class CourseTest {

    @Test
    void 유료강의등록이_되어야_한다() {
        Payment payment = new Payment("1", 1L, 1L, new Money(5000));
        Course course = new Course("18기-자바TDD", 1L);

        Session paidCourse = new PaidSession(new SessionName("유료강의1"),
            new RegistrationCount(0),
            new MaxRegistrationCount(new RegistrationCount(999)),
            new Money(5000),
            new Image(new ImageSize(1),
                ImageType.JPG,
                new ImageWidth(300),
                new ImageHeight(200)),
            LocalDateTime.now(),
            LocalDateTime.now(),
            SessionStatus.RECRUITING);

        course.addSession(new SessionName("유료강의1"), paidCourse);

        assertThat(course.registerSession(new SessionName("유료강의1"), payment)).isTrue();
    }

    @Test
    void 무료강의등록이_되어야_한다() {
        Payment payment = new Payment("1", 1L, 1L, new Money(0));
        Course course = new Course("18기-자바TDD", 1L);

        Session freeSession = new FreeSession(new SessionName("무료강의1"),
            new RegistrationCount(0),
            new Money(0),
            new Image(new ImageSize(1),
                ImageType.JPG,
                new ImageWidth(300),
                new ImageHeight(200)),
            LocalDateTime.now(),
            LocalDateTime.now(),
            SessionStatus.RECRUITING);

        course.addSession(new SessionName("무료강의1"), freeSession);

        assertThat(course.registerSession(new SessionName("무료강의1"), payment)).isTrue();
    }
}
