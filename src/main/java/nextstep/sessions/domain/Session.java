package nextstep.sessions.domain;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.image.Image;
import nextstep.users.domain.NsUser;
import nextstep.utils.BaseEntity;

import java.time.LocalDateTime;
import java.util.List;

public class Session extends BaseEntity {

    private final String sessionName;

    private final List<Image> images;

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
                    List<Image> images,
                    SessionRegisterDetails sessionRegisterDetails
    ) {
        super(id, startedAt, endedAt);
        this.sessionName = sessionName;
        this.images = images;
        this.sessionRegisterDetails = sessionRegisterDetails;
    }

    public Student enroll(NsUser nsUser, List<Student> students, Payment payment) {
        Student student = new Student(nsUser.getId(), getId());
        sessionRegisterDetails.enroll(student, students, payment);
        return student;
    }

    public boolean isOutOfControl(NsUser user) {
        return this.getId() != user.getId();
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
