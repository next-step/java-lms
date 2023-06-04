package nextstep.courses.domain.session;

import nextstep.courses.domain.AbstractCourse;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Session extends AbstractCourse {
    private final Long sessionNumber;

    private final SessionPeriod sessionPeriod;

    private final CoverImage coverImage;

    private final Students students;

    public Session(
            Long id,
            String title,
            Long sessionNumber,
            SessionPeriod sessionPeriod,
            CoverImage coverImage,
            Students students,
            Long creatorId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        Objects.requireNonNull(students);
        this.id = id;
        this.title = title;
        this.sessionNumber = sessionNumber;
        this.sessionPeriod = sessionPeriod;
        this.coverImage = coverImage;
        this.students = students;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Session(
            String title,
            LocalDate startDate,
            LocalDate endDate,
            SessionFeeType sessionFeeType,
            int capacity
    ) {
        this(
                0L,
                title,
                null,
                new SessionPeriod(startDate, endDate),
                null,
                new Students(capacity, sessionFeeType, SessionStatus.PREPARING),
                null,
                LocalDateTime.now(),
                null
        );
    }

    public Session register(NsUser nsUser) {
        students.addStudent(nsUser);
        return this;
    }

    public Session startRecruit() {
        sessionPeriod.checkRecruit();
        students.startRecruit();
        return this;
    }
}
