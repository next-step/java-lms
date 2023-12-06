package nextstep.lms.domain;

import nextstep.lms.dto.EnrollApplicationDTO;

import java.time.LocalDateTime;

public class Session {
    private final Long id;
    private final SessionInfo sessionInfo;
    private final Students students;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Session(Long id, SessionInfo sessionInfo, Students students) {
        this(id, sessionInfo, students, LocalDateTime.now());
    }

    public Session(Long id, SessionInfo sessionInfo, Students students, LocalDateTime createdAt) {
        this.id = id;
        this.sessionInfo = sessionInfo;
        this.students = students;
        this.createdAt = createdAt;
    }

    public void enroll(EnrollApplicationDTO enrollApplicationDTO) {
        sessionInfo.enroll(this.students, enrollApplicationDTO);
    }
}
