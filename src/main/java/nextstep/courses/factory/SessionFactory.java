package nextstep.courses.factory;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionDescriptor;
import nextstep.courses.domain.session.SessionPeriod;
import nextstep.courses.domain.session.constraint.SessionConstraint;
import nextstep.courses.domain.session.image.SessionImages;
import nextstep.courses.domain.session.policy.EnrollmentStatus;
import nextstep.courses.domain.session.policy.SessionEnrollPolicy;
import nextstep.courses.domain.session.policy.SessionStatus;
import nextstep.courses.domain.session.policy.SessionType;
import nextstep.courses.entity.SessionEntity;
import org.springframework.stereotype.Component;

@Component
public class SessionFactory {

    public Session createSession(SessionEntity sessionEntity, SessionImages sessionImages) {
        SessionConstraint sessionConstraint = new SessionConstraint(sessionEntity.getFee(), sessionEntity.getCapacity());
        SessionDescriptor sessionDescriptor = new SessionDescriptor(
            new SessionPeriod(sessionEntity.getStartDate(), sessionEntity.getEndDate()),
            new SessionEnrollPolicy(
                EnrollmentStatus.fromString(sessionEntity.getEnrollStatus()),
                SessionStatus.fromString(sessionEntity.getStatus()),
                SessionType.fromString(sessionEntity.getType())
            ),
            sessionImages
        );
        return new Session(sessionEntity.getId(), sessionConstraint, sessionDescriptor);
    }

    public SessionEntity createSessionEntity(Session session, Long courseId) {
        return SessionEntity.builder()
            .id(session.id())
            .createdAt(session.getCreatedAt())
            .updatedAt(session.getUpdatedAt())
            .deleted(session.isDeleted())
            .courseId(courseId)
            .fee(session.getConstraint().getFee().getValue())
            .capacity(session.getConstraint().getCapacity().getValue())
            .imageUrl(null)
            .imageType(null)
            .startDate(session.getDescriptor().getPeriod().getStartDate())
            .endDate(session.getDescriptor().getPeriod().getEndDate())
            .type(session.getDescriptor().getPolicy().getType().getType())
            .status(session.getDescriptor().getPolicy().getStatus().getStatus())
            .enrollStatus(session.getDescriptor().getPolicy().getEnrollmentStatus().getStatus())
            .build();
    }
}
