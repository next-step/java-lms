package nextstep.courses.factory;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionDescriptor;
import nextstep.courses.domain.session.SessionPeriod;
import nextstep.courses.domain.session.constraint.SessionConstraint;
import nextstep.courses.domain.session.image.ImageHandler;
import nextstep.courses.domain.session.image.SessionImage;
import nextstep.courses.domain.session.image.SessionImageType;
import nextstep.courses.domain.session.policy.SessionEnrollPolicy;
import nextstep.courses.domain.session.policy.SessionStatus;
import nextstep.courses.domain.session.policy.SessionType;
import nextstep.courses.entity.SessionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SessionFactory {

    public Session create(SessionEntity sessionEntity) {
        SessionConstraint sessionConstraint = new SessionConstraint(sessionEntity.getFee(), sessionEntity.getCapacity());
        SessionDescriptor sessionDescriptor = new SessionDescriptor(
            new SessionPeriod(sessionEntity.getStartDate(), sessionEntity.getEndDate()),
            new SessionEnrollPolicy(SessionStatus.fromString(sessionEntity.getStatus()), SessionType.fromString(sessionEntity.getType()))
        );
        return new Session(sessionEntity.getId(), sessionConstraint, sessionDescriptor);
    }
}
