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
