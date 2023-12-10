package nextstep.courses.mock;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.image.ImageType;
import nextstep.courses.domain.session.SessionPeriod;

import java.time.LocalDateTime;

public class MockCourse {
    public static CoverImage getMockCoverImage() {
        return new CoverImage(300, 200, 500L, ImageType.GIF);
    }

    public static SessionPeriod getMockSessionPeriod() {
        return new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(1));
    }

    public static Course getMockCourse() {
        return new Course("courseTitle", 1L);
    }
}
