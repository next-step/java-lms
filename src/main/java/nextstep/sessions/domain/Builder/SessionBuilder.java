package nextstep.sessions.domain.Builder;

import nextstep.common.Builder.PeriodBuilder;
import nextstep.common.Period;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionCharge;
import nextstep.sessions.domain.SessionImages;
import nextstep.sessions.domain.SessionStatus;
import nextstep.sessions.domain.SessionStudents;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.builder.NsUserBuilder;

import java.time.LocalDateTime;

public class SessionBuilder {
    private Long id = 1L;
    private String name = "테스트강의";
    private Period date = new PeriodBuilder().build();
    private SessionImages images = new SessionImagesBuilder().build();
    private SessionCharge charge = new SessionChargeBuilder().build();
    private SessionStatus status = new SessionStatusBuilder().build();
    private SessionStudents students = new SessionStudentsBuilder().build();
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    private NsUser instructor = new NsUserBuilder().build();

    public SessionBuilder withId(Long sessionId) {
        this.id = sessionId;
        return this;
    }

    public SessionBuilder withName(String sessionName) {
        this.name = sessionName;
        return this;
    }

    public SessionBuilder withPeriod(Period sessionPeriod) {
        this.date = sessionPeriod;
        return this;
    }

    public SessionBuilder withSessionImages(SessionImages sessionImages) {
        this.images = sessionImages;
        return this;
    }

    public SessionBuilder withSessionCharge(SessionCharge sessionCharge) {
        this.charge = sessionCharge;
        return this;
    }

    public SessionBuilder withSessionStatus(SessionStatus sessionStatus) {
        this.status = sessionStatus;
        return this;
    }

    public SessionBuilder withSessionStudents(SessionStudents sessionStudents) {
        this.students = sessionStudents;
        return this;
    }

    public SessionBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public SessionBuilder withUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public SessionBuilder withInstructor(NsUser instructor) {
        this.instructor = instructor;
        return this;
    }

    public Session build() {
        return new Session(id, name, date, images, charge, status, students, createdAt, updatedAt, instructor);
    }
}
