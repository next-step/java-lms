package nextstep.courses.domain.builder;

import nextstep.courses.domain.CoverImage;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionPeriod;
import nextstep.courses.domain.enrollment.Enrollment;

import java.time.LocalDateTime;

public class SessionBuilder {
    private Long id;
    private Long courseId;
    private String title;
    private CoverImage coverImage;
    private SessionPeriod period;
    private Enrollment enrollment;

    public SessionBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public SessionBuilder withCourseId(Long courseId) {
        this.courseId = courseId;
        return this;
    }

    public SessionBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public SessionBuilder withCoverImage(CoverImage coverImage) {
        this.coverImage = coverImage;
        return this;
    }

    public SessionBuilder withPeriod(SessionPeriod period) {
        this.period = period;
        return this;
    }

    public SessionBuilder withEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
        return this;
    }

    public Session build() {
        return new Session(id, courseId, title, coverImage, period, enrollment, LocalDateTime.now(), null);
    }

}
