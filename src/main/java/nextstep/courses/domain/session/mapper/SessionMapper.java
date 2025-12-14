package nextstep.courses.domain.session.mapper;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.session.Enrollments;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.constant.SessionRecruitmentStatus;
import nextstep.courses.domain.session.constant.SessionStatus;
import nextstep.courses.record.SessionRecord;

import java.util.List;

public class SessionMapper {

    public static SessionRecord toEntity(Session session) {
        return new SessionRecord(
                session.getId(),
                session.getCourse().getId(),
                session.getSessionCore().getStartDate(),
                session.getSessionCore().getEndDate(),
                session.getSessionCore().getMaxCapacity(),
                session.getSessionCore().getTuition(),
                session.getSessionCore().getSessionType(),
                session.getSessionCore().getSessionStatus().name(),
                session.getSessionCore().getSessionRecruitmentStatus().name(),
                session.getCreatedAt(),
                session.getUpdatedAt()
        );
    }

    public static Session toDomain(SessionRecord record, Course course, List<CoverImage> coverImages, Enrollments enrollments) {
        return new Session(
                record.getId(),
                course,
                record.createdSessionRange(),
                record.createdSessionPolicy(),
                SessionStatus.from(record.getSessionStatus()),
                coverImages,
                enrollments,
                SessionRecruitmentStatus.from(record.getRecruitmentStatus())
        );
    }
}
