package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public abstract class Session {

    private Long id;

    private Image coverImage;

    private Period sessionPeriod;

    private SessionType type;

    private List<NsUser> students = new ArrayList<>();

    private SessionStatus status;

    public Session() {
    }

    public Session(Image image, Period period, SessionType type) {
        this.coverImage = image;
        this.sessionPeriod = period;
        this.type = type;
        this.status = SessionStatus.PREPARING;
    }

    public void setCoverImage(Image image, Period sessionPeriod) {
        this.coverImage = image;
        this.sessionPeriod = sessionPeriod;
    }

    public void enroll(NsUser student) {
        if (this.status != SessionStatus.RECRUITING) {
            throw new IllegalArgumentException("강의 수강신청은 강의 상태가 모집중일 때만 가능합니다.");
        }
        this.students.add(student);
    }

    public void open() {
        this.status = SessionStatus.RECRUITING;
    }

    public Long getId() {
        return this.id;
    }
    public Image getImage() {
        return this.coverImage;
    }

    public Period getPeriod() {
        return this.sessionPeriod;
    }

    public SessionType getType() {
        return this.type;
    }

    public List<NsUser> getStudents() {
        return this.students;
    }
}
