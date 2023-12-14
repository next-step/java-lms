package nextstep.courses.domain;

import nextstep.courses.domain.session.enums.SessionRegisterStatus;
import nextstep.users.domain.NsUser;

import java.util.Objects;

public class Student {

    private NsUser nsUser;
    private SessionRegisterStatus registerStatus;

    public Student(NsUser nsUser) {
        this.nsUser = nsUser;
        this.registerStatus = SessionRegisterStatus.WAITING;
    }

    public Student(NsUser nsUser, SessionRegisterStatus registerStatus) {
        this.nsUser = nsUser;
        this.registerStatus = registerStatus;
    }

    public boolean isWaitingStudent() {
        return SessionRegisterStatus.WAITING.equals(this.registerStatus);
    }

    public boolean isAcceptStudent() {
        return SessionRegisterStatus.ACCEPT.equals(this.registerStatus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(nsUser, student.nsUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nsUser);
    }

    public Long getUserId() {
        return nsUser.getId();
    }

    public SessionRegisterStatus getRegisterStatus() {
        return registerStatus;
    }

    public void acceptSession() {
        this.registerStatus = SessionRegisterStatus.ACCEPT;
    }
}
