package nextstep.session.domain;

import nextstep.session.NotRecruitException;
import nextstep.session.StudentNumberExceededException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;

public class Session {

    private final Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String image;
    private Boolean isFree;
    private SignUpInformation signUpInformation;

    public Session(Long maxNumberOfStudent, SessionStatus status) {
        this(0L, maxNumberOfStudent, status);
    }

    public Session(Long id, Long maxNumberOfStudent, SessionStatus status) {
        this.id = id;
        this.signUpInformation = new SignUpInformation(status, maxNumberOfStudent);
    }

    public Session(Long id, LocalDateTime startDate, LocalDateTime endDate, String image, String status, Long maxNumberOfStudent, Boolean isFree) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.image = image;
        this.isFree = isFree;
        this.signUpInformation = new SignUpInformation(SessionStatus.of(status), maxNumberOfStudent);
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

    public SessionStatus getStatus() {
        return signUpInformation.getStatus();
    }

    public Long getMaxNumberOfStudent() {
        return signUpInformation.getMaxNumberOfStudent();
    }

    public Boolean getFree() {
        return isFree;
    }

    public List<NsUser> getStudents() {
        return signUpInformation.getStudents();
    }

    void signUp(NsUser user) throws StudentNumberExceededException, NotRecruitException {
        signUpInformation.signUp(user);
    }
}
