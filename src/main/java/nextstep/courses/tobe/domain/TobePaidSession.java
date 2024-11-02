package nextstep.courses.tobe.domain;

import nextstep.courses.domain.session.Category;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.tobe.domain.session.TobeCoverImage;

import java.time.LocalDateTime;

public class TobePaidSession extends TobeSession {

    private final int maxRegisterCount;
    private final long sessionAmount;

    public TobePaidSession(long id,
                           long courseId,
                           DateRange dateRange,
                           TobeCoverImage coverImage,
                           ProcessStatus processStatus,
                           RecruitmentStatus recruitmentStatus,
                           int maxRegisterCount,
                           long sessionAmount,
                           long creatorId,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt) {
        super(id,
                courseId,
                Category.PAID,
                dateRange,
                coverImage,
                processStatus,
                recruitmentStatus,
                creatorId,
                createdAt,
                updatedAt
        );
        this.maxRegisterCount = maxRegisterCount;
        this.sessionAmount = sessionAmount;
    }


}
