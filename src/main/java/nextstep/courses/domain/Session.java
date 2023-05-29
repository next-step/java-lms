package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Session extends BaseEntity {

    private SessionPeriod sessionPeriod;

    private CoverImage coverImage;

    private SessionFeeType sessionFeeType;

    private SessionStatus sessionStatus;

    private final int maxNumberOfStudent;

    private final List<NsUser> users = new ArrayList<>();

    public Session(
            LocalDate startDate,
            LocalDate endDate,
            int maxNumberOfStudent
    ) {
        this(
                null,
                new SessionPeriod(startDate, endDate),
                null,
                SessionFeeType.FREE,
                SessionStatus.PREPARING,
                maxNumberOfStudent,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public Session(
            LocalDate startDate,
            LocalDate endDate,
            String coverImageUrl,
            SessionFeeType sessionFeeType,
            int maxNumberOfStudent
    ) {
        this(
            null,
            new SessionPeriod(startDate, endDate),
            new CoverImage(coverImageUrl),
            sessionFeeType,
            SessionStatus.PREPARING,
            maxNumberOfStudent,
            LocalDateTime.now(),
            LocalDateTime.now()
        );
    }

    public Session(
            Long id,
            SessionPeriod sessionPeriod,
            CoverImage coverImage,
            SessionFeeType sessionFeeType,
            SessionStatus sessionStatus,
            int maxNumberOfStudent,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.sessionPeriod = sessionPeriod;
        this.coverImage = coverImage;
        this.sessionFeeType = sessionFeeType;
        this.sessionStatus = sessionStatus;
        this.maxNumberOfStudent = maxNumberOfStudent;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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
