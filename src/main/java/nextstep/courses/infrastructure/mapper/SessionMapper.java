package nextstep.courses.infrastructure.mapper;

import nextstep.courses.domain.image.SessionCoverImage;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionPeriod;
import nextstep.courses.domain.session.SessionPolicy;
import nextstep.courses.domain.session.SessionState;
import nextstep.courses.domain.session.Term;
import nextstep.courses.domain.session.policy.capacity.CapacityPolicy;
import nextstep.courses.domain.session.policy.capacity.LimitedCapacity;
import nextstep.courses.domain.session.policy.capacity.UnlimitedCapacity;
import nextstep.courses.domain.session.policy.tuition.FreeTuition;
import nextstep.courses.domain.session.policy.tuition.PaidTuition;
import nextstep.courses.domain.session.policy.tuition.TuitionPolicy;
import nextstep.courses.infrastructure.entity.SessionEntity;

public class SessionMapper {

    private SessionMapper() {
    }

    public static SessionEntity toEntity(Session session) {
        SessionPolicy sessionPolicy = session.getSessionPolicy();
        TuitionPolicy tuitionPolicy = sessionPolicy.getTuitionPolicy();
        CapacityPolicy capacityPolicy = sessionPolicy.getCapacityPolicy();

        Integer maxCapacity = null;
        Long tuitionFee = null;
        String typeName = "FREE";

        if (tuitionPolicy instanceof PaidTuition) {
            PaidTuition paidTuition = (PaidTuition) tuitionPolicy;
            tuitionFee = paidTuition.getTuitionFee();
            typeName = "PAID";
        }

        if (capacityPolicy instanceof LimitedCapacity) {
            LimitedCapacity limitedCapacity = (LimitedCapacity) capacityPolicy;
            maxCapacity = limitedCapacity.getMaxCapacity();
        }

        return new SessionEntity(
            session.getId(),
            session.getCourseId(),
            session.getTerm().getValue(),
            session.getPeriod().startDay(),
            session.getPeriod().endDay(),
            session.getState().name(),
            typeName,
            maxCapacity,
            tuitionFee,
            session.getCreatedAt()
        );
    }

    public static Session toDomain(SessionEntity entity, SessionCoverImage coverImage) {
        SessionPeriod period = new SessionPeriod(entity.getStartDay(), entity.getEndDay());
        SessionState state = SessionState.valueOf(entity.getState());
        SessionPolicy sessionPolicy = createSessionPolicy(entity);

        return sessionPolicy.createSession(
            entity.getId(),
            entity.getCourseId(),
            new Term(entity.getTerm()),
            period,
            state,
            coverImage
        );
    }

    private static SessionPolicy createSessionPolicy(SessionEntity entity) {
        TuitionPolicy tuitionPolicy = createTuitionPolicy(entity);
        CapacityPolicy capacityPolicy = createCapacityPolicy(entity);
        return new SessionPolicy(tuitionPolicy, capacityPolicy);
    }

    private static TuitionPolicy createTuitionPolicy(SessionEntity entity) {
        if ("PAID".equals(entity.getType())) {
            return new PaidTuition(entity.getTuitionFee());
        }
        return new FreeTuition();
    }

    private static CapacityPolicy createCapacityPolicy(SessionEntity entity) {
        if (entity.getMaxCapacity() != null) {
            return new LimitedCapacity(entity.getMaxCapacity());
        }
        return new UnlimitedCapacity();
    }
}