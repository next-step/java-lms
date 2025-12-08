package nextstep.courses.domain.session.mapper;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.session.Enrollments;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.constant.SessionStatus;
import nextstep.courses.record.SessionRecord;

public class SessionMapper {

    public static SessionRecord toEntity(Session session) {
        return new SessionRecord(
                session.getId(),
                session.getCourse().getId(),
                session.getCoverImage().getId(),
                session.getStartDate(),
                session.getEndDate(),
                session.getMaxCapacity(),
                session.getTuition(),
                session.getSessionType(),
                session.getSessionStatus().name(),
                session.getCreatedAt(),
                session.getUpdatedAt()
        );
    }

    public static Session toDomain(SessionRecord record, Course course, CoverImage coverImage, Enrollments enrollments) {
        return new Session(
                record.getId(),
                course,
                record.createdSessionRange(),
                record.createdSessionPolicy(),
                SessionStatus.from(record.getSessionStatus()),
                coverImage,
                enrollments
        );
    }
}
