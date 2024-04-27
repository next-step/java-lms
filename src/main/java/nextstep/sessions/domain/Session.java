package nextstep.sessions.domain;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.image.Image;
import nextstep.users.domain.NsUser;
import nextstep.utils.BaseEntity;

import java.time.LocalDateTime;
import java.util.List;

public class Session extends BaseEntity {

    private final String sessionName;

    private final Image image;

    private final SessionRegisterDetails sessionRegisterDetails;

    public Session(long id, LocalDateTime startedAt, LocalDateTime endedAt, String sessionName, SessionRegisterDetails sessionRegisterDetails) {
        this(id, startedAt, endedAt, sessionName, null, sessionRegisterDetails);
    }

    public Session(long id, LocalDateTime startedAt, LocalDateTime endedAt, String sessionName) {
        this(id, startedAt, endedAt, sessionName, null, null);
    }

    private Session(long id,
                   LocalDateTime startedAt,
                   LocalDateTime endedAt,
                   String sessionName,
                   Image image,
                   SessionRegisterDetails sessionRegisterDetails
    ) {
        super(id, startedAt, endedAt);
        this.sessionName = sessionName;
        this.image = image;
        this.sessionRegisterDetails = sessionRegisterDetails;
    }

    public void register(NsUser listener, Payment payment) {
        sessionRegisterDetails.register(listener, payment);
    }

    public Student enroll(NsUser nsUser, List<Student> students, Payment payment) {
        Student student = new Student(nsUser.getId(), getId());
        sessionRegisterDetails.enroll(student, students, payment);
        return student;
    }

    public boolean isContainListener(NsUser listener) {
        return sessionRegisterDetails.isContainsListener(listener);
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
