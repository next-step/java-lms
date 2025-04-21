package nextstep.courses.factory;

import nextstep.courses.domain.session.*;
import nextstep.courses.domain.session.constraint.SessionConstraint;
import nextstep.courses.domain.session.policy.EnrollmentStatus;
import nextstep.courses.domain.session.policy.SessionEnrollPolicy;
import nextstep.courses.domain.session.policy.SessionStatus;
import nextstep.courses.domain.session.policy.SessionType;
import nextstep.courses.entity.SessionEntity;
import nextstep.courses.entity.SessionImageEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public Sessions createSessions(SessionEntityImageMap sessionEntityImageMap) throws IOException {
        List<Session> sessions = new ArrayList<>();

        for (Map.Entry<SessionEntity, List<SessionImageEntity>> entry : sessionEntityImageMap.entrySet()) {
            sessions.add(createSession(entry.getKey(), entry.getValue()));
        }

        return new Sessions(sessions);
    }
}
