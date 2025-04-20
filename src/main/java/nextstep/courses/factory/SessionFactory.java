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

    private final SessionImagesFactory sessionImagesFactory;

    public SessionFactory(SessionImagesFactory sessionImagesFactory) {
        this.sessionImagesFactory = sessionImagesFactory;
    }

    public Session create(SessionEntity sessionEntity, List<SessionImageEntity> sessionImageEntities) throws IOException {
        SessionConstraint sessionConstraint = new SessionConstraint(sessionEntity.getFee(), sessionEntity.getCapacity());
        SessionDescriptor sessionDescriptor = new SessionDescriptor(
            new SessionPeriod(sessionEntity.getStartDate(), sessionEntity.getEndDate()),
            new SessionEnrollPolicy(
                EnrollmentStatus.fromString(sessionEntity.getEnrollStatus()),
                SessionStatus.fromString(sessionEntity.getStatus()),
                SessionType.fromString(sessionEntity.getType())
            ),
            sessionImagesFactory.create(sessionImageEntities)
        );
        return new Session(sessionEntity.getId(), sessionConstraint, sessionDescriptor);
    }
}
