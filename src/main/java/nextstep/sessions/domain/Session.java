package nextstep.sessions.domain;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.image.Image;
import nextstep.users.domain.NsUser;
import nextstep.utils.BaseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.sessions.domain.SessionSelectionStatus.*;

public class Session extends BaseEntity {

    private final String sessionName;

    private final List<Image> images;

    private final SessionRegisterDetails sessionRegisterDetails;

    private SessionSelectionStatus sessionSelectionStatus;

    public Session(long id, LocalDateTime startedAt, LocalDateTime endedAt, String sessionName, SessionRegisterDetails sessionRegisterDetails, SessionSelectionStatus sessionSelectionStatus) {
        this(id, startedAt, endedAt, sessionName, null, sessionRegisterDetails, sessionSelectionStatus);
    }

    public Session(long id, LocalDateTime startedAt, LocalDateTime endedAt, String sessionName) {
        this(id, startedAt, endedAt, sessionName, null, null, NOT_SELECTED);
    }

    private Session(long id,
                    LocalDateTime startedAt,
                    LocalDateTime endedAt,
                    String sessionName,
                    List<Image> images,
                    SessionRegisterDetails sessionRegisterDetails
    ) {
        this(id, startedAt, endedAt, sessionName, images, sessionRegisterDetails, NOT_SELECTED);
    }

    private Session(long id,
                   LocalDateTime startedAt,
                   LocalDateTime endedAt,
                   String sessionName,
                   List<Image> images,
                   SessionRegisterDetails sessionRegisterDetails,
                   SessionSelectionStatus sessionSelectionStatus

    ) {
        super(id, startedAt, endedAt);
        this.sessionName = sessionName;
        this.images = images;
        this.sessionRegisterDetails = sessionRegisterDetails;
        this.sessionSelectionStatus = sessionSelectionStatus;
    }

    public Student enroll(NsUser nsUser, List<Student> students, Payment payment) {
        Student student = new Student(nsUser.getId(), getId());
        sessionRegisterDetails.enroll(student, students, payment);
        return student;
    }

    public boolean isOutOfControl(NsUser user) {
        return this.getId() != user.getId();
    }

    public boolean isNotSelected() {
        return sessionSelectionStatus.isNotSelectable();
    }

    public long getId() {
        return super.getId();
    }

    public LocalDateTime getStartedAt() {
        return super.getStartedAt();
    }

    public LocalDateTime getEndedAt() {
        return super.getEndedAt();
    }

    public String getSessionName() {
        return sessionName;
    }
}
