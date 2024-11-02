package nextstep.courses.tobe.domain;

import nextstep.courses.domain.session.Category;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.tobe.domain.session.TobeCoverImage;

import java.time.LocalDateTime;

public class TobeFreeSession extends TobeSession {
    public TobeFreeSession(long id,
                           long courseId,
                           DateRange dateRange,
                           TobeCoverImage coverImage,
                           ProcessStatus processStatus,
                           RecruitmentStatus recruitmentStatus,
                           long creatorId,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt) {
        super(id, courseId, Category.FREE, dateRange, coverImage, processStatus, recruitmentStatus, creatorId, createdAt, updatedAt);
    }
}
