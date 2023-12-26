package nextstep.session.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FreeSession extends Session {
    private static final SessionType SESSION_TYPE = SessionType.FREE;

    public FreeSession(Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage) {
        this(null, null, null, creatorId, startDate, endDate, sessionImage, SessionStatus.PREPARING, SessionRecruitStatus.CLOSED, SESSION_TYPE, null, null);
    }

    public FreeSession(Long id, LocalDateTime createdAt, LocalDateTime updatedAt, Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage, SessionStatus sessionStatus, SessionRecruitStatus sessionRecruitStatus, SessionType sessionType, List<Enrollment> enrollments, List<Admission> admissions) {
        super(id, createdAt, updatedAt, creatorId, startDate, endDate, sessionImage, sessionStatus, sessionRecruitStatus, sessionType, enrollments, admissions);
    }

    public static FreeSession create(Long creatorId, LocalDate startDate, LocalDate endDate, SessionImage sessionImage) {
        return new FreeSession(creatorId, startDate, endDate, sessionImage);
    }

    @Override
    protected void validateCommonEnroll(NsUser nsUser) {
    }
}
