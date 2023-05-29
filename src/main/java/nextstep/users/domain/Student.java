package nextstep.users.domain;

import nextstep.courses.domain.Session;

import java.util.Objects;

public class Student {
    private NsUser nsUser;
    private Session session;

    public Student(NsUser nsUser, Session session) {
        this.nsUser = nsUser;
        this.session = session;
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
        return Objects.hash(nsUser, session);
    }
}
