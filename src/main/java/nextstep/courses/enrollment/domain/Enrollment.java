package nextstep.courses.enrollment.domain;

import java.time.LocalDateTime;
import nextstep.common.domain.BaseEntity;

public class Enrollment extends BaseEntity {

    private Long studentId;
    private Long cohortId;

    public Enrollment(
            Long studentId,
            Long cohortId
    ) {
        this(0L, LocalDateTime.now(), LocalDateTime.now(), studentId, cohortId);
    }

    public Enrollment(
            Long id,
            LocalDateTime createdDate,
            LocalDateTime updatedDate,
            Long studentId,
            Long cohortId
    ) {
        super(id, createdDate, updatedDate);
        this.studentId = studentId;
        this.cohortId = cohortId;
    }
}
