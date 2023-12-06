package nextstep.courses.domain.session;

import nextstep.courses.domain.attendee.Attendee;
import nextstep.courses.domain.image.Image;

public class Session {

    private final Long id;

    private final SessionInformation information;

    private final Enrollment enrollment;

    private final Image image;

    public Session(Long id,
                   SessionInformation information,
                   Enrollment enrollment,
                   Image image) {
        this.id = id;
        this.information = information;
        this.enrollment = enrollment;
        this.image = image;
    }

    public Attendee enroll(Long amount, Long userId) {
        information.validateApply();
        return enrollment.enroll(amount, userId, this.id);
    }
}