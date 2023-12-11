package nextstep.courses.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.users.domain.NsUser;

public class Enrollment {

    private Period sessionPeriod;

    private SessionStatus status;

    private final List<NsUser> students = new ArrayList<>();

    public Enrollment(Period sessionPeriod, SessionStatus status) {
        this.sessionPeriod = sessionPeriod;
        this.status = status;
    }

    public void enroll(NsUser student) {
        if (status != SessionStatus.RECRUITING) {
            throw new IllegalArgumentException("강의 수강신청은 강의 상태가 모집중일 때만 가능합니다.");
        }
        this.students.add(student);
    }

    public List<NsUser> getStudents() {
        return this.students;
    }

    public SessionStatus getStatus() {
        return this.status;
    }
}
