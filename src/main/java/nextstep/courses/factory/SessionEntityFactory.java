package nextstep.courses.factory;

import nextstep.courses.domain.session.Session;
import nextstep.courses.entity.SessionEntity;
import org.springframework.stereotype.Component;

@Component
public class SessionEntityFactory {
    public SessionEntity create(Session session, Long courseId) {
        return SessionEntity.builder()
            .id(session.id())
            .createdAt(session.getCreatedAt())
            .updatedAt(session.getUpdatedAt())
            .deleted(session.isDeleted())
            .courseId(courseId)
            .fee(session.getConstraint().fee())
            .capacity(session.getConstraint().capacity())
            .imageUrl(null)
            .imageType(null)
            .startDate(session.getDescriptor().startDate())
            .endDate(session.getDescriptor().endDate())
            .type(session.getDescriptor().type())
            .status(session.getDescriptor().status())
            .enrollStatus(session.getDescriptor().enrollStatus())
            .build();
    }
}
