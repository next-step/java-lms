package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Enrollment {
    private final long sessionId;
    private final NsUser student;
    private String enrollDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Enrollment(long sessionId, NsUser nsUser) {
        this(sessionId, nsUser, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")),
                LocalDateTime.now(), null);
    }

    public Enrollment(long sessionId, NsUser nsUser, String enrollDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.sessionId = sessionId;
        this.student = nsUser;
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
