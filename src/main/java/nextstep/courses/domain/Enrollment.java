package nextstep.courses.domain;

import nextstep.exception.AlreadyEnrollException;
import nextstep.exception.EnrollNotAllowException;
import nextstep.users.domain.NsUser;

import java.util.List;

public class Enrollment {

    private Capability capability;
    private Students students;
    private SessionStatus status;

    public Enrollment(int capability) {
        this(new Capability(capability));
    }

    public Enrollment(Capability capability) {
        this.capability = capability;
        this.students = new Students();
        this.status = SessionStatus.READY;
    }

    public Enrollment(int capability, List<NsUser> users) {
        this(new Capability(capability), new Students(users));
    }

    public Enrollment(Capability capability, Students students) {
        this.capability = capability;
        this.students = students;
        this.status = SessionStatus.READY;
    }

    public static Enrollment createFull() {
        return new Enrollment(1, List.of(NsUser.GUEST_USER));
    }

    public boolean canEnroll() {
        if (!capability.hasLimit()) {
            return true;
        }
        return capability.isBigger(students.count());
    }

    public boolean limitExist() {
        return capability.hasLimit();
    }

    public boolean canRegister() {
        return this.status.equals(SessionStatus.RECRUITING);
    }

    public void open() {
        this.status = SessionStatus.RECRUITING;
    }

    public void close() {
        this.status = SessionStatus.DONE;
    }

    public void enroll(NsUser user) {
        if (!this.canEnroll()) {
            throw new EnrollNotAllowException();
        }

        if (!this.isDuplicate(user)) {
            throw new AlreadyEnrollException();
        }
        this.students.add(user);
    }

    private boolean isDuplicate(NsUser user) {
        return this.students.contains(user);
    }
}
