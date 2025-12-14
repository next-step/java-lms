package nextstep.courses.domain.session.mapper;

import nextstep.courses.domain.session.Enrollment;
import nextstep.courses.domain.session.constant.EnrollmentStatus;
import nextstep.courses.domain.session.constant.SelectionStatus;
import nextstep.courses.record.EnrollmentRecord;
import nextstep.users.domain.NsUser;

public class EnrollmentMapper {

    public static EnrollmentRecord toEntity(Enrollment enrollment) {
        return new EnrollmentRecord(
                enrollment.getId(),
                enrollment.getUser().getId(),
                enrollment.getSessionId(),
                enrollment.getSelectionStatus().name(),
                enrollment.getEnrollmentStatus().name(),
                enrollment.getCreatedAt(),
                enrollment.getUpdatedAt()
        );
    }

    public static Enrollment toDomain(EnrollmentRecord record, NsUser user) {
        return new Enrollment(
                record.getId(),
                user,
                record.getSessionId(),
                record.getCreatedAt(),
                record.getUpdatedAt(),
                SelectionStatus.from(record.getEnrollmentStatus()),
                EnrollmentStatus.from(record.getEnrollmentStatus())
        );
    }
}
