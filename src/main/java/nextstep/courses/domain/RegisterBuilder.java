package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

public class RegisterBuilder {

    private SessionStatus status;
    private int maxRegisterCount;
    private NsUsers students = new NsUsers();

    public RegisterBuilder withStatus(SessionStatus status) {
        this.status = status;
        return this;
    }

    public RegisterBuilder withMaxRegisterCount(int maxRegisterCount) {
        this.maxRegisterCount = maxRegisterCount;
        return this;
    }

    public RegisterBuilder withStudent(NsUser student) {
        this.students.add(student);
        return this;
    }

    public Register build() {
        return new Register(status, maxRegisterCount, students);
    }
}
