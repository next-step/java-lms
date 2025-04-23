package nextstep.courses.factory;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionDescriptor;
import nextstep.courses.domain.session.SessionPeriod;
import nextstep.courses.domain.session.constraint.SessionConstraint;
import nextstep.courses.domain.session.policy.EnrollmentStatus;
import nextstep.courses.domain.session.policy.SessionEnrollPolicy;
import nextstep.courses.domain.session.policy.SessionStatus;
import nextstep.courses.domain.session.policy.SessionType;
import nextstep.courses.entity.SessionEntity;
import nextstep.courses.entity.SessionImageEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class SessionFactory {

    private final SessionImageFactory sessionImageFactory;

    public SessionFactory(SessionImageFactory sessionImageFactory) {
        this.sessionImageFactory = sessionImageFactory;
    }

    public Session createSession(SessionEntity sessionEntity, List<SessionImageEntity> sessionImageEntities) throws IOException {
        SessionConstraint sessionConstraint = new SessionConstraint(sessionEntity.getFee(), sessionEntity.getCapacity());
        SessionDescriptor sessionDescriptor = new SessionDescriptor(
            new SessionPeriod(sessionEntity.getStartDate(), sessionEntity.getEndDate()),
            new SessionEnrollPolicy(
                EnrollmentStatus.fromString(sessionEntity.getEnrollStatus()),
                SessionStatus.fromString(sessionEntity.getStatus()),
                SessionType.fromString(sessionEntity.getType())
            ),
            sessionImageFactory.createSessionImages(sessionImageEntities)
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
