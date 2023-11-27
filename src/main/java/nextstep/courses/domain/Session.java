package nextstep.courses.domain;

import nextstep.courses.domain.type.SessionStatus;
import nextstep.courses.exception.NotRecruitingSessionException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Session {

    private final Duration duration;
    private final Image image;
    private SessionStatus status;
    protected final List<NsUser> students = new ArrayList<>();

    public Session(Duration duration, Image image) {
        this.duration = duration;
        this.image = image;
        this.status = duration.sessionStatus(LocalDate.now());
    }

    public Session(Duration duration, Image image, SessionStatus status) {
        this.duration = duration;
        this.image = image;
        this.status = status;
    }

    public abstract void apply(Payment payment, NsUser nsUser);

    protected void validateStatus() {
        if (!this.status.equals(SessionStatus.RECRUITING)) {
            throw new NotRecruitingSessionException("모집중인 강의가 아닙니다.");
        }
    }

    protected void addStudent(NsUser nsUser) {
        this.students.add(nsUser);
    }

    public List<NsUser> students() {
        return Collections.unmodifiableList(this.students);
    }

    public Image image() {
        return this.image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(duration, session.duration) && Objects.equals(image, session.image) && status == session.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(duration, image, status);
    }
}
