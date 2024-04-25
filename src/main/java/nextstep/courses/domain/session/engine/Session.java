package nextstep.courses.domain.session.engine;

import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.session.enrollment.Enrollment;
import nextstep.courses.domain.session.name.SessionName;
import nextstep.courses.domain.session.period.Period;
import nextstep.courses.entity.BaseEntity;

public abstract class Session extends BaseEntity {

    private final Long id;

    private final SessionName sessionName;

    private Enrollment enrollment;

    private final Image image;

    private final Period period;

    public Session(Session session, Enrollment enrollment) {
        this(session.getId(), new SessionName(session.getSessionName()), enrollment,
            session.getImage(), session.getPeriod());
    }

    public Session(Long id, SessionName SessionName, Enrollment enrollment, Image image,
        Period period) {
        this.id = id;
        this.sessionName = SessionName;
        this.enrollment = enrollment;
        this.image = image;
        this.period = period;
    }

    public Long getId() {
        return id;
    }

    public String getSessionName() {
        return sessionName.getValue();
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public Image getImage() {
        return image;
    }

    public Period getPeriod() {
        return period;
    }
}
