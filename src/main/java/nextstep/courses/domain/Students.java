package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Students {

    private List<NsUser> userList;

    public Students(List<NsUser> userList) {
        this.userList = userList;
    }

    public Students() {
        this.userList = new ArrayList<>();
    }

    public Students registerSessionStudent(NsUser nsUser) {
        this.userList.add(nsUser);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Students students = (Students) o;
        return Objects.equals(userList, students.userList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userList);
    }
}
