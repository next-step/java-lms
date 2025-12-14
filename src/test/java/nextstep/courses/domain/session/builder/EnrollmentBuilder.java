package nextstep.courses.domain.session.builder;

import nextstep.courses.domain.session.Enrollment;
import nextstep.courses.domain.session.constant.EnrollmentStatus;
import nextstep.courses.domain.session.constant.SelectionStatus;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class EnrollmentBuilder {

    private NsUser user = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    private Long sessionId = 23334L;
    private SelectionStatus selectionStatus = SelectionStatus.PENDING;
    private EnrollmentStatus enrollmentStatus = EnrollmentStatus.WAITING;

    public EnrollmentBuilder withUser(NsUser user) {
        this.user = user;
        return this;
    }

    public EnrollmentBuilder withSessionId(Long sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public EnrollmentBuilder withSelectionStatus(SelectionStatus selectionStatus) {
        this.selectionStatus = selectionStatus;
        return this;
    }

    public EnrollmentBuilder withEnrollmentStatus(EnrollmentStatus enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
        return this;
    }


    public Enrollment build() {
        return new Enrollment(user, sessionId, LocalDateTime.now(), null, selectionStatus, enrollmentStatus);
    }

}
