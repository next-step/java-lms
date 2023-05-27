package nextstep.courses.domain.ragistration;

import nextstep.common.BaseEntity;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserCourseType;

import java.time.LocalDateTime;

public class Registration extends BaseEntity {
    private Long courseId;
    private Long sessionId;
    private Long nsUserId;
    private RegistrationType registrationType;

    public static Registration receipt(Long courseId, Long sessionId, Long nsUserId) {
        return new Registration(null, courseId, sessionId, nsUserId, RegistrationType.RECEIPT, LocalDateTime.now(), null);
    }

    public Registration(Long id, Long courseId, Long sessionId, Long nsUserId, RegistrationType registrationType, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.courseId = courseId;
        this.sessionId = sessionId;
        this.nsUserId = nsUserId;
        this.registrationType = registrationType;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public Long getNsUserId() {
        return nsUserId;
    }

    public RegistrationType getRegistrationType() {
        return registrationType;
    }

    public void approve(NsUser nsUser, UserCourseType courseType) {
        if (nsUser.checkIsValid(courseType)) {
            this.registration();
        }

        this.cancelRegistration();
    }

    public boolean isApprove() {
        return this.registrationType == RegistrationType.APPROVAL;
    }


    private void registration() {
        this.registrationType = RegistrationType.APPROVAL;
    }

    private void cancelRegistration() {
        this.registrationType = RegistrationType.REFUSE;
    }
}
