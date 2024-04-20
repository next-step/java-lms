package nextstep.courses.infrastructure.session;

import java.time.LocalDate;

import nextstep.courses.domain.session.EnrollmentCount;
import nextstep.courses.domain.session.Name;
import nextstep.courses.domain.session.Schedule;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.strategy.StrategyType;

public class SessionEntity {

    private final Long id;
    private final String name;
    private final String status;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String strategy;
    private final int fee;
    private final int enrollmentLimit;
    private final int enrollmentCount;
    private Long courseId;

    public SessionEntity(
            final Long id,
            final String name,
            final String status,
            final LocalDate startDate,
            final LocalDate endDate,
            final String strategy,
            final int fee,
            final int enrollmentLimit,
            final int enrollmentCount
    ) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.strategy = strategy;
        this.fee = fee;
        this.enrollmentLimit = enrollmentLimit;
        this.enrollmentCount = enrollmentCount;
    }

    public Long id() {
        return this.id;
    }

    public String name() {
        return this.name;
    }

    public String status() {
        return this.status;
    }

    public LocalDate startDate() {
        return this.startDate;
    }

    public LocalDate endDate() {
        return this.endDate;
    }

    public String strategy() {
        return this.strategy;
    }

    public int fee() {
        return this.fee;
    }

    public int enrollmentLimit() {
        return this.enrollmentLimit;
    }

    public int enrollmentCount() {
        return this.enrollmentCount;
    }

    public Long courseId() {
        return this.courseId;
    }

    public void updateCourseId(final Long courseId) {
        this.courseId = courseId;
    }

    public Session toDomain() {
        return new Session(
                this.id,
                new Name(this.name),
                SessionStatus.from(this.status),
                new Schedule(startDate, endDate),
                StrategyType.buildStrategy(this.strategy, this.fee, this.enrollmentLimit),
                new EnrollmentCount(this.enrollmentCount)
        );
    }

    public static SessionEntity fromDomain(final Session session) {
        return new SessionEntity(
                session.id(),
                session.name(),
                session.statusName(),
                session.startDate(),
                session.endDate(),
                session.strategyName(),
                session.fee(),
                session.enrollmentLimit(),
                session.currentEnrollmentCount()
        );
    }
}
