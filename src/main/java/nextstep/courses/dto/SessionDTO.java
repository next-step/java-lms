package nextstep.courses.dto;

import java.time.LocalDateTime;
import nextstep.courses.domain.AuditInfo;
import nextstep.courses.domain.SessionStatus;

public class SessionDTO extends AuditInfo {
    private final Long id;
    private final Long courseId;
    private final CoverImageDTO coverImageDTO;
    private final DurationDTO durationDTO;
    private final SessionPaymentDTO sessionPaymentDTO;
    private final SessionStatus sessionStatus;
    private final EnrollmentDTO enrollmentDTO;

    public SessionDTO(Long id, Long courseId, CoverImageDTO coverImageDTO, DurationDTO durationDTO,
                      SessionPaymentDTO sessionPaymentDTO, SessionStatus sessionStatus,
                      EnrollmentDTO enrollmentDTO, LocalDateTime createAt, LocalDateTime updateAt) {
        super(createAt, updateAt);
        this.id = id;
        this.coverImageDTO = coverImageDTO;
        this.durationDTO = durationDTO;
        this.sessionPaymentDTO = sessionPaymentDTO;
        this.sessionStatus = sessionStatus;
        this.enrollmentDTO = enrollmentDTO;
        this.courseId = courseId;
    }

    public Long getId() {
        return id;
    }

    public Long getCourseId(){
        return courseId;
    }

    public CoverImageDTO getCoverImageDTO() {
        return coverImageDTO;
    }

    public DurationDTO getDurationDTO() {
        return durationDTO;
    }

    public SessionPaymentDTO getSessionPaymentDTO() {
        return sessionPaymentDTO;
    }

    public SessionStatus getSessionStatus(){
        return sessionStatus;
    }

    public EnrollmentDTO getEnrollmentDTO() {
        return enrollmentDTO;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
