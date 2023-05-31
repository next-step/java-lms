package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Enrollment {
    private final NsUser student;
    private final long sessionId;
    private String enrollDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Enrollment(NsUser nsUser, long sessionId) {
        this(nsUser, sessionId, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")),
                LocalDateTime.now(), null);
    }

    public Enrollment(NsUser nsUser, long sessionId, String enrollDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.student = nsUser;
        this.sessionId = sessionId;
        this.enrollDate = enrollDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object other) {
        return this.student.equals(((Enrollment) other).student) &&
                this.sessionId == ((Enrollment) other).sessionId;
    }

    public NsUser getStudent() {
        return student;
    }

    public long getSessionId() {
        return sessionId;
    }

    public String getEnrollDate() {
        return enrollDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
