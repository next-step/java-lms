package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Session extends AbstractCourse {
    private Long sessionNumber;

    private SessionPeriod sessionPeriod;

    private CoverImage coverImage;

    private SessionFeeType sessionFeeType;

    private SessionStatus sessionStatus;

    private final int maxNumberOfStudent;

    private final List<NsUser> users = new ArrayList<>();

    public Session(
            Long id,
            String title,
            Long sessionNumber,
            SessionPeriod sessionPeriod,
            CoverImage coverImage,
            SessionFeeType sessionFeeType,
            SessionStatus sessionStatus,
            int maxNumberOfStudent,
            Long creatorId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        Objects.requireNonNull(sessionFeeType);
        Objects.requireNonNull(sessionStatus);
        validateNumberOfStudent(maxNumberOfStudent);
        this.id = id;
        this.title = title;
        this.sessionNumber = sessionNumber;
        this.sessionPeriod = sessionPeriod;
        this.coverImage = coverImage;
        this.sessionFeeType = sessionFeeType;
        this.sessionStatus = sessionStatus;
        this.maxNumberOfStudent = maxNumberOfStudent;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Session(
            String title,
            LocalDate startDate,
            LocalDate endDate,
            SessionFeeType sessionFeeType,
            int maxNumberOfStudent
    ) {
        this(
                null,
                title,
                null,
                new SessionPeriod(startDate, endDate),
                null,
                sessionFeeType,
                SessionStatus.PREPARING,
                maxNumberOfStudent,
                null,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    private void validateNumberOfStudent(int maxNumberOfStudent) {
        if (maxNumberOfStudent < 1) {
            throw new IllegalArgumentException("강의 최대 수강인원은 1보다 커야 합니다.");
        }
    }

    public void register(NsUser nsUser) {
        if (!sessionStatus.equals(SessionStatus.RECRUITING)) {
            throw new IllegalStateException("강의 수강신청은 강의 상태가 모집중일 때만 가능합니다.");
        }
        if (users.size() >= maxNumberOfStudent) {
            throw new IllegalStateException("강의는 강의 최대 수강 인원을 초과할 수 없습니다.");
        }
        users.add(nsUser);
    }

    public Session startRecruit() {
        if (sessionPeriod.isEndDateBeforeNow()) {
            throw new IllegalStateException("종료된 강의는 모집할 수 없습니다.");
        }
        this.sessionStatus = SessionStatus.RECRUITING;
        return this;
    }

    public Session changeSessionFeeType(SessionFeeType toFeeType) {
        this.sessionFeeType = toFeeType;
        return this;
    }
}
