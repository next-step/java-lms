package nextstep.courses.infrastructure.mapper;

import nextstep.courses.domain.image.SessionCoverImage;
import nextstep.courses.domain.registration.Registrations;
import nextstep.courses.domain.session.Enrollment;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionPeriod;
import nextstep.courses.domain.session.SessionState;
import nextstep.courses.domain.session.Term;
import nextstep.courses.domain.session.type.FreeType;
import nextstep.courses.domain.session.type.PaidType;
import nextstep.courses.domain.session.type.SessionType;
import nextstep.courses.infrastructure.entity.SessionEntity;

public class SessionMapper {

    private SessionMapper() {
    }

    public static SessionEntity toEntity(Session session) {
        Enrollment enrollment = session.getEnrollment();
        SessionType type = enrollment.getType();

        Integer maxCapacity = null;
        Long tuitionFee = null;
        String typeName = "FREE";

        if (type instanceof PaidType) {
            PaidType paidType = (PaidType) type;
            maxCapacity = paidType.getRegistrations().getMaxCapacity();
            tuitionFee = paidType.getTuitionFee();
            typeName = "PAID";
        }

        return new SessionEntity(
            session.getId(),
            session.getCourseId(),
            session.getTerm().getValue(),
            session.getPeriod().startDay(),
            session.getPeriod().endDay(),
            enrollment.getState().name(),
            typeName,
            maxCapacity,
            tuitionFee,
            session.getCreatedAt()
        );
    }

    public static Session toDomain(SessionEntity entity, Registrations registrations, SessionCoverImage coverImage) {
        SessionPeriod period = new SessionPeriod(entity.getStartDay(), entity.getEndDay());
        SessionState state = SessionState.valueOf(entity.getState());
        SessionType type = createSessionType(entity, registrations);
        Enrollment enrollment = new Enrollment(state, type);

        return new Session(
            entity.getId(),
            entity.getCourseId(),
            new Term(entity.getTerm()),
            period,
            enrollment,
            coverImage
        );
    }

    private static SessionType createSessionType(SessionEntity entity, Registrations registrations) {
        if ("PAID".equals(entity.getType())) {
            return new PaidType(entity.getTuitionFee(), registrations);
        }
        return new FreeType(registrations);
    }
}