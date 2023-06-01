package nextstep.session.domain;

import nextstep.students.domain.Students;

import java.time.LocalDateTime;
import java.util.List;

public class Session {

    private final Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String image;
    private Boolean isFree;
    private SignUpInformation signUpInformation;

    public Session(Long maxNumberOfStudent, ProgressStatus progressStatus, RecruitmentStatus recruitmentStatus) {
        this(0L, maxNumberOfStudent, progressStatus, recruitmentStatus);
    }

    public Session(Long id, Long maxNumberOfStudent, ProgressStatus status, RecruitmentStatus recruitmentStatus) {
        this.id = id;
        this.signUpInformation = new SignUpInformation(status, recruitmentStatus, maxNumberOfStudent);
    }

    public Session(Long id, LocalDateTime startDate, LocalDateTime endDate, String image, String progressStatus,
                   Long maxNumberOfStudent, Boolean isFree, String recruitStatus) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.image = image;
        this.isFree = isFree;
        this.signUpInformation = new SignUpInformation(
                ProgressStatus.of(progressStatus), RecruitmentStatus.of(recruitStatus), maxNumberOfStudent);
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getImage() {
        return image;
    }

    public ProgressStatus getProgressStatus() {
        return signUpInformation.getProgressStatus();
    }

    public RecruitmentStatus getRecruitmentStatus() {
        return signUpInformation.getRecruitmentStatus();
    }

    public Long getMaxNumberOfStudent() {
        return signUpInformation.getMaxNumberOfStudent();
    }

    public Boolean getFree() {
        return isFree;
    }

    public List<Students> getStudents() {
        return signUpInformation.getStudents();
    }

    void signUp(Students students) {
        signUpInformation.signUp(students);
    }
}
