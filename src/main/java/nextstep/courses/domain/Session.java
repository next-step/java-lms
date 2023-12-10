package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import nextstep.users.domain.NsUser;

public abstract class Session {

    private Long id;

    private Image coverImage;

    private Period sessionPeriod;

    private SessionType type;

    private List<NsUser> students = new ArrayList<>();

    public Session() {
    }

    public Session(Image image, Period period, SessionType type) {
        this.coverImage = image;
        this.sessionPeriod = period;
        this.type = type;
    }

    public void setCoverImage(Image image, Period sessionPeriod) {
        this.coverImage = image;
        this.sessionPeriod = sessionPeriod;
    }

    public void enroll(NsUser student) {
        this.students.add(student);
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
